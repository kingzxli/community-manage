package com.community.service.community.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.community.Room;
import com.community.mapper.community.RoomMapper;
import com.community.service.community.CommunityService;
import com.community.service.community.RoomService;
import com.community.util.Assert;
import com.community.util.DataUtils;
import com.community.util.IdMaker;
import com.community.util.Poi;


@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomMapper roomMapper;
	@Autowired
	private CommunityService communityService;

	@Override
	public Room getById(String roomId) {		
		return roomMapper.selectById(roomId);
	}

	@Override
	public List<Room> list(Room room) {				
		return roomMapper.roomList(room);	
	}

	@Override
	public void delete(String roomId) {		
		roomMapper.deleteById(roomId);
	}

	@Override
	public void updateById(Room room) {
		roomMapper.updateById(room);
	}

	@Override
	public void insert(Room room) {
		roomMapper.insert(room);
		
	}

	@Override
	public Integer importRoomData(MultipartFile file) {
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
				//excel 格式 0:序号，1:楼盘名称，2:房间类型，3:楼栋名称,4:单元，5:房号，6:面积，7:姓名
				String communityName = Poi.getStringValueFromCell(row.getCell(1));
				String typeVo= Poi.getStringValueFromCell(row.getCell(2));
				String buildName = Poi.getStringValueFromCell(row.getCell(3));
				String unitName = Poi.getStringValueFromCell(row.getCell(4));
				String roomName = Poi.getStringValueFromCell(row.getCell(5));
				String areaVo = Poi.getStringValueFromCell(row.getCell(6));				
				String userName = Poi.getStringValueFromCell(row.getCell(7));

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
				//新增房间信息
				this.insertByName(communityId,roomName,area,type,userName);
				num++;	
			} catch (Exception e) {

			}
		}
		Assert.isTrue(StringUtils.isEmpty(error), error.toString());
		return num;		
	}

	public void insertByName(String communityId, String roomName,BigDecimal area,Integer type,String userName) {
		Room room = new Room();
		room.setCommunityId(communityId);
		room.setRoomName(roomName);
		room.setType(type);
		QueryWrapper<Room> wrapper = new QueryWrapper<>(room);
		List<Room> list = roomMapper.selectList(wrapper);
				
		room.setArea(area);
		room.setUserName(userName);
		if(list == null || list.isEmpty()) {			
			room.setId(IdMaker.get());
			room.setIsDelete(0);
			room.setCreatedUser("system");
			room.setCreatedTime(new Date());
			roomMapper.insert(room);			
		}else {
			room.setId(list.get(0).getId());	
			room.setModifiedTime(new Date());
			roomMapper.updateById(room);			
		}

	}
}
