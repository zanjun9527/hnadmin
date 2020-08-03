package com.xiaoyuer.hn.admin.Util;


import com.xiaoyuer.hn.admin.constants.ConstantsRedis;

import java.util.Map;
/**
 * 初始化数据字典
 * @author huangyi
 *
 */
public class DictUtil {

	private static Map<String, String> dictMap=null; // 数据字典map

	/**
	 * 根据key值查询对应value
	 * @param key
	 * @return 返回null或字符串(已trim处理)
	 */
	public static String findDictMap(String key){
		RedisUtil redisUtil = (RedisUtil)SpringContextUtil.getBean("redisUtil");

		String obj = redisUtil.get(ConstantsRedis.DICT+":"+key);
		return obj == null ? null :obj;
	}
	
	public static String findDictMapHget(String key, String type) {
		RedisUtil redisUtil = (RedisUtil)SpringContextUtil.getBean("redisUtil");
		String obj = redisUtil.hGetStr(ConstantsRedis.DICT + ":" + key, type);
		return obj == null ? null : obj;
	}
}
