package com.community.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.community.entity.User;
import com.community.mapper.UserMapper;
import com.community.service.CommunityService;
import com.community.service.RoomService;
import com.community.service.UserService;
import com.community.util.Assert;
import com.community.util.DataUtils;
import com.community.util.IdMaker;
import com.community.util.Poi;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private RoomService roomService;

	@Override
	public User getById(String userId) {		
		return userMapper.selectById(userId);
	}

	@Override
	public List<User> list() {		
		return userMapper.selectList(null);
	}

	@Override
	public void delete(String userId) {		
		userMapper.deleteById(userId);
	}

	@Override
	public void updateById(User user) {
		userMapper.updateById(user);
	}

	@Override
	public void insert(User user) {
		userMapper.insert(user);		
	}
	
	public String insertByPhone(String phone,String userName) {
		User user = new User();
		user.setPhone(phone);		
		User dbUser = userMapper.selectOne(user);
		
		user.setName(userName);		
		if(dbUser == null) {
			user.setId(IdMaker.get());
			user.setIsDelete(0);
			user.setCreatedUser("system");
			user.setCreatedTime(new Date());
			userMapper.insert(user);					
		}else {
			user.setId(dbUser.getId());
			userMapper.updateById(user);
		}
		
		return user.getId();
	}

	@Override
	public Integer importUserData(MultipartFile file) {
		//获取sheet
		Sheet sheet = Poi.read(file);
		// 得到Excel的行数
		int totalRows = sheet.getPhysicalNumberOfRows();		
		Integer num = 0;
		// 统计错误信息
		StringBuilder error = new StringBuilder();		
		for (int i = 1; i < totalRows; i++) {
			try {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}	
				//excel 格式 0:序号，1:楼盘名称，2:房间类型，3:楼栋名称,4:单元，5:房号，6:面积，7:姓名，8:电话
				String communityName = Poi.getStringValueFromCell(row.getCell(1));
				String typeVo= Poi.getStringValueFromCell(row.getCell(2));
				String buildName = Poi.getStringValueFromCell(row.getCell(3));
				String unitName = Poi.getStringValueFromCell(row.getCell(4));
				String roomName = Poi.getStringValueFromCell(row.getCell(5));
				String areaVo = Poi.getStringValueFromCell(row.getCell(6));				
				String name = Poi.getStringValueFromCell(row.getCell(7));
				String phone = Poi.getStringValueFromCell(row.getCell(8));
				
				//检查数据是否为空
				if(StringUtils.isEmpty(communityName)) {
					error.append("第" + i + "行,楼盘不能为空");
				}
				
				Integer type = null;
				if(StringUtils.isEmpty(typeVo)) {
					error.append("第" + i + "行,房间类型不能为空");
				}else if("住宅".equals(typeVo)) {
					type = 1;
				}else if("商铺".equals(typeVo)) {
					type = 2;
				}else if("车库".equals(typeVo)) {
					type = 3;
				}else {
					error.append("第" + i + "行,房间类型填写错误");
				}
				
				if(type != null && type < 3) {
					if(StringUtils.isEmpty(buildName)) {
						error.append("第" + i + "行,楼栋不能为空");
					}
				}
				
				if(type != null && type == 1) {
					if(StringUtils.isEmpty(unitName)) {
						error.append("第" + i + "行,单元不能为空");
					}
				}

				if(StringUtils.isEmpty(roomName)) {
					error.append("第" + i + "行,房号不能为空");
				}
				
				BigDecimal area = new BigDecimal(0);
				if(StringUtils.isEmpty(areaVo)) {
					error.append("第" + i + "行,面积不能为空");
				}if(!DataUtils.isIntegerAndDecimal(areaVo)) {
					error.append("第" + i + "行,面积请填写数字");
				}else {
					area = new BigDecimal(areaVo);
				}
				
				if(StringUtils.isEmpty(name)) {
					error.append("第" + i + "行,姓名不能为空");
				}
				if(StringUtils.isEmpty(phone)) {
					error.append("第" + i + "行,电话不能为空");
				}							
				
				//根据名称查询数据是否存在，不存在则新增 (1:楼盘2:楼栋3:单元)				  
				String communityId = communityService.insertByName(communityName,1,null);
				//新增楼栋信息
				if(type != null && type < 3) {
					communityId = communityService.insertByName(buildName,2,communityId);
				}
				//新增单元信息
				if(type != null && type == 1) {
					communityId = communityService.insertByName(unitName,3,communityId);
				}
				//新增用户信息
				String userId = this.insertByPhone(phone,name);
				roomService.insertByName(communityId,roomName,area,type,userId);
				num++;	
			} catch (Exception e) {
				
			}
						
		}
		Assert.isTrue(StringUtils.isEmpty(error), error.toString());
		return num;		
		
	}

	
	
}
