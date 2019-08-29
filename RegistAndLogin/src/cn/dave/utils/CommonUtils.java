package cn.dave.utils;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;

public class CommonUtils {
	/*
	 * 生成不重复的32位长大写字符串
	 * */
	public static String uuid(){
		
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	/**
	 * 把Map转换为指定类型的javaBean对象
	 * 
	 */
	public static <T> T toBean(Map map,Class<T> clazz){
		
		try{
			
			//创建指定类型的javabean对象
			T bean = clazz.newInstance();
			//把数据封装到javabean中
			BeanUtils.populate(bean,map);
			//返回Javabean对象
			return bean;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
