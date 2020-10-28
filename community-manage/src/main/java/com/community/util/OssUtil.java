package com.community.util;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

/**
 * 对象存储
 * @author king
 */
public class OssUtil {
	//外网访问节点。
	private static String ENDPOINT = "oss-cn-guangzhou.aliyuncs.com";
	private static String ACCESSKEYID = "LTAI4FixggGWvEDqPsDeFXcF";
	private static String ACCESSKEYSECRET = "QrAK3hz2AnuDZj4NStpnROhXcd8ZC7";
	private static String BUCKETNAME = "cswyimg";
	//上传成功后返回的url
	private static String SUFFER_URL = "http://cswyimg.oss-cn-guangzhou.aliyuncs.com/";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static String upload(MultipartFile file,String businessType) {
		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
		//获取文件后缀名称
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String date = sdf.format(new Date());
		if(StringUtils.isEmpty(businessType)) {
			businessType = "default";
		}
		
		String fileName = businessType +"/" + IdMaker.get() +date + ext;		
		// 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
		String url = null;
		try {
			ossClient.putObject(BUCKETNAME, fileName, new ByteArrayInputStream(file.getBytes()));
			url = SUFFER_URL + fileName;
		} catch (Exception e) {
			e.printStackTrace();
		} 

		// 关闭OSSClient。
		ossClient.shutdown(); 
		return url;
		 
	}

}
