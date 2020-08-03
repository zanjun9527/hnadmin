package com.xiaoyuer.hn.admin.Util;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
public class StringUtil {
	/**
	 * 特殊字符
	 */
	public static final String REGEX_SPECIAL_CHAR = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?～！＠＃￥％……＆×（）——＋｜｛｝【】［］‘；：＂。，、．＜＞《》’”“-／？]";

	public static final String REGEX_PUNCTUATION_CHAR = "\\p{P}";

	/**
	 * 字母和数字
	 */
	public static final String REGEX_CAPITAL_CHAR = "[\\w]";

	/**
	 * 中文
	 */
	public static final String REGEX_CHINESE_CHAR = "[\u4E00-\u9FA5]";

//	// 生成订单号
//	public static String generateOrderNumber(String orderType, Integer userId) {
//		// orderNumber an8..32，最大32个字母、数字和-及_
//		userId = userId % 100000000;// 取id末8位
//		String orderNumber = orderType + ConstantsForDB.ORDER_SPLIT + userId
//				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//		return orderNumber;
//	}

	/**
	 * 获取 隐藏中间部分的字符串
	 * 
	 * @param str
	 * @param frontCount
	 *            前面显示的位数
	 * @param behindCount
	 *            后面显示的位数
	 * @return
	 */
	public static String getHideMiddleStr(String str, int frontCount,
			int behindCount) {
		if (isEmpty(str) || str.length() < frontCount + behindCount) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (i < frontCount || i >= str.length() - behindCount) {
				builder.append(str.charAt(i));
			} else {
				builder.append("*");
			}
		}
//		System.out.println(builder.toString());
		return builder.toString();
	}

	/**
	 * 隐藏手机号码中间的4位
	 * 
	 * @param cellPhone
	 * @return
	 */
	public static String getDisabledCellPhone(String cellPhone) {
		if (StringUtil.isEmpty(cellPhone) || cellPhone.length() != 11) {
			return null;
		}
		return cellPhone.substring(0, 3) + "****"
				+ cellPhone.substring(7, cellPhone.length());
	}

	public static String encryptPassword(String repassword) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(repassword.getBytes());
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return String.valueOf(str);
		} catch (NoSuchAlgorithmException ex) {
			ex.toString();
			return null;
		}

	}

	/**
	 * 获取保留两位小数(四舍五入)
	 * 
	 * @param number
	 * @return
	 */
	public static String getKeepTwoNumber(String number) {
		DecimalFormat df = new DecimalFormat("0.00");
		double num = Double.parseDouble(number) * 100;
		return df.format(num);
	}

	public static String getShortContent(String content, int length) {
		if (content == null) {
			return "";
		}
		if (content.length() <= length)
			return content;
		else
			return content.substring(0, length - 3) + "...";
	}

	/**
	 * 如果为空字符则返回NULL
	 * 
	 * @param str
	 * @return
	 */
	public static String emptyToNull(Object obj) {

		if (obj != null) {
			String str = obj.toString();

			if (str.trim().equals("")) {
				str = null;
			} else {
				str.trim();
			}
			return str;
		} else {
			return null;
		}

	}

	/**
	 * 如果为空返回""字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String nullToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str;

	}

	/**
	 * 获取随机数字字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String getRandomString() {
		Random ran = new Random();
		Long l = ran.nextLong();
		return String.valueOf(Math.abs(l));
	}

	public static int toInt(String str) {
		int value = 0;
		if (str != null && !"".equals(str.trim())) {
			value = Integer.parseInt(str);
		}
		return value;
	}

	public static double toDouble(String str) {
		double value = 0;
		if (str != null && !"".equals(str.trim())) {
			value = Double.parseDouble(str);
		}
		return value;
	}

	/**
	 * 创建指定数量的随机字符串
	 * 
	 * @param numberFlag
	 *            是否是数字
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {

		String retStr = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}

	// 生成编号--仲裁有用到
	public static String generateOrderNumber() {
		// orderNumber an8..32，最大32个字母、数字和-及_
		String orderNumber = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		orderNumber = orderNumber + new Random().nextInt(2);
		return orderNumber;
	}

	// 生成订单号
	/*
	 * public static String generateOrderNumber(String orderType,Integer
	 * userId){ //orderNumber an8..32，最大32个字母、数字和-及_ userId =
	 * userId%100000000;//取id末8位 String orderNumber = orderType +
	 * UnionpayUtil.ORDER_SPLIT + userId + new
	 * SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); return
	 * orderNumber; }
	 */

	// 银联代付 -- 生成提现流水号与订单号互换
	public static String generateWithDrawNoByOrderNumber(String orderNumber) {
		String withDrawNo = "";
		if (orderNumber != null && orderNumber.trim().length() >= 14) {
			int length = orderNumber.trim().length();
			withDrawNo = orderNumber.trim().substring(length - 14, length);
		}
		return withDrawNo;
	}

	// 银联代付 -- 生成订单号与提现流水号互换
	/*
	 * public static String generateOrderNumberByWithDrawNo(String withDrawNo){
	 * 
	 * String orderNumber = UnionpayUtil.TX + UnionpayUtil.ORDER_SPLIT +
	 * withDrawNo; return orderNumber; }
	 */
	// 分 元 互相转换 转换为元
	public static Double fengToYuan(String feng) {
		Double yuan = null;
		try {
			if (feng != null && feng.trim().length() > 0) {
				yuan = Double.valueOf(feng) / 100;
			}
		} catch (Exception e) {

		}
		return yuan;
	}


	/**
	 * 元转分
	 * 20190516 乘积精度 改用bigdecimal处理
	 * @param yuan
	 * @return
	 */
	public static String yuanToFeng(Double yuan) {
		String feng = "";
		if (yuan != null) {
			BigDecimal a1 = new BigDecimal(String.valueOf(yuan));
			BigDecimal b1 = new BigDecimal(100);
			BigDecimal result = a1.multiply(b1);
			feng = String.valueOf(result.longValue());
		}
		return feng;
	}

	/**
	 * 元转分
	 * 
	 * @param yuan
	 * @return
	 */
	public static String yuanToFengString(String yuan) {
		String feng = yuanToFeng(Double.valueOf(yuan));
		return feng;
	}

	/**
	 * 将银行卡号转换为带***显示，后4位显示数字
	 * 
	 * @param yuan
	 * @return
	 */
	public static String stringToInvisionStar(String str) {
		if (str != null && str.trim().length() > 4) {
			String starStr = "";
			int starLength = str.trim().length() - 4;
			for (int i = 0; i < starLength; i++) {
				starStr += "*";
			}
			String endStr = str.substring(str.trim().length() - 4);
			starStr += endStr;
			return starStr;

		} else {
			return str;
		}
	}


	public static String toString(String[] arry, String split) {
		StringBuilder sb = new StringBuilder();
		for (String s : arry) {
			if (s != null && !"".equals(s)) {
				sb.append(s + split);
			}
		}
		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - 1).toString();
		} else {
			return "";
		}
	}
	
	/**
	 * url 格式为http://192.168.77.100:8080或<br>
	 * http://www.xiaoyuer.com
	 * 
	 * @param url
	 * @return xiaoyuer.com或192.168.77.100
	 */
	public static String urlIP(String url) {
		String noHttp = null;
		if (url.startsWith("https")) {
			noHttp = url.substring(8, url.length());
		} else if (url.startsWith("http")) {
			noHttp = url.substring(7, url.length());
		}else{
			noHttp = url;
		}

		String newUrl = "";
		try {
			if (noHttp.indexOf(":") != -1) { // 开发
				String[] urlArr = noHttp.split(":");
				newUrl = urlArr[0];
			} else {
				int index = noHttp.indexOf(".");
				noHttp = noHttp.substring(index);
				
				if (noHttp.indexOf("www.") != -1) { // 线上或测试
					newUrl = noHttp.substring(4);
				} else if (noHttp.indexOf("m.") != -1) { // 线上或测试
					newUrl = noHttp.substring(2);
				} else if (noHttp.indexOf("www1.") != -1) { // 线上或测试
					newUrl = noHttp.substring(5);
				} else if (noHttp.indexOf("m1.") != -1) { // 线上或测试
					newUrl = noHttp.substring(3);
				} else {
					newUrl = noHttp;
				}
			}
		} catch (Exception e) {
		}

		return newUrl;
	}

	/**
	 * 解析html获取纯文本
	 * 
	 * @param inputString
	 * @return
	 */
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		java.util.regex.Matcher m_script;
		Pattern p_style;
		java.util.regex.Matcher m_style;
		Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textStr;// 返回文本字符串
	}

	/**
	 * 是否为空
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0 || "null".equals(str);
	}

	/*
	 * public static String analyzer(String str) { String text =
	 * "基于java语言开发的轻量级的中文分词工具包"; // 创建分词对象 Analyzer anal = new IKAnalyzer(true);
	 * StringBuffer sb = new StringBuffer(""); StringReader reader = new
	 * StringReader(text); try { // 分词 TokenStream ts = anal.tokenStream("",
	 * reader); CharTermAttribute term =
	 * ts.getAttribute(CharTermAttribute.class); // 遍历分词数据 while
	 * (ts.incrementToken()) { sb.append(term.toString() + "|"); }
	 * System.out.println(sb.toString());
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } finally { reader.close();
	 * anal.close(); } return sb.toString();
	 * 
	 * }
	 */

	/**
	 * 分割功能，指定开始分割的位置，及分割符,返回字符串数组
	 * 
	 * @param inputString
	 *            输入字符串
	 * @param spliter
	 *            分割符
	 * @param begin
	 *            开始位置
	 * @return String[]
	 */
	public static String[] split(String inputString, String spliter, int begin) {
		String[] str = null;
		String strRemain = null;

		if (inputString != null && !inputString.trim().equals("")) {
			strRemain = inputString.substring(begin);
			str = strRemain.split(spliter);
		}
		return str;
	}

	/**
	 * 按照指定字符进行分割,如果第一位为分隔符则忽略第一个字符
	 * 
	 * @param inputString
	 * @param spliter
	 * @return 字符串数组
	 */
	public static String[] split(String inputString, String spliter) {
		int begin = 0;
		if (!StringUtil.isEmpty(inputString)
				&& ",".equals(inputString.substring(0, 1))) {
			begin = 1;
		}
		String[] str = null;
		String strRemain = null;

		if (inputString != null && !inputString.trim().equals("")) {
			strRemain = inputString.substring(begin);
			str = strRemain.split(spliter);
		}
		return str;
	}

	public static final String SPECIAL_CHAR = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?～！＠＃￥％……＆×（）——＋｜｛｝【】［］‘；：＂。，、．＜＞《》’”“-／？]";

	/**
	 * 替换特殊字符(全角&半角)
	 * 
	 * @param srcString
	 *            原字符串 <a href="http://home.51cto.com/index.php?s=/space/34010"
	 *            target="_blank">@return</a> 替换后的字符串 <a
	 *            href="http://home.51cto.com/index.php?s=/space/2305405"
	 *            target="_blank">@throws</a> PatternSyntaxException <a
	 *            href="http://home.51cto.com/index.php?s=/space/34010"
	 *            target="_blank">@return</a> String
	 */
	public static String StringFilter(String srcString)
			throws PatternSyntaxException {
		return Pattern.compile(SPECIAL_CHAR).matcher(srcString).replaceAll("")
				.replaceAll("[url=]\\\\[/url]", "").trim();
	}

	/**
	 * 检查指定字符串中是否包含特殊字符
	 * 
	 * @param srcString
	 *            原字符串
	 * @param specialChar
	 *            特殊字符数组 <a
	 *            href="http://home.51cto.com/index.php?s=/space/34010"
	 *            target="_blank">@return</a> 是否存在特殊字符 <a
	 *            href="http://home.51cto.com/index.php?s=/space/2305405"
	 *            target="_blank">@throws</a> void <a
	 *            href="http://home.51cto.com/index.php?s=/space/34010"
	 *            target="_blank">@return</a> boolean
	 */
	public static boolean existSpecialChar(String srcString, char[] specialChar) {
		for (Character c : specialChar) {
			if (srcString.contains(c.toString())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断一个字符串是否null或""或"null"
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isBlank_new(String str) {
		if (str == null)
			return true;
		if (str.trim().length() == 0)
			return true;
		if ("null".equals(str.trim()))
			return true;
		return false;
	}

	/**
	 * 使用指定的连接符把map中的key-value连接起来
	 * 
	 * @param map
	 * @param connector
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String joinMapValue(Map<String, String> map, char connector) {
		StringBuffer b = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			b.append(entry.getKey());
			b.append('=');
			if (entry.getValue() != null) {
				b.append(entry.getValue());
			}
			b.append(connector);
		}
		return b.toString();
	}

	/**
	 * 验证openid
	 * 
	 * @return
	 */
	public static boolean checkOpenid(String openid) {
		return !isEmpty(openid) && openid.length() == 28;
	}

	/**
	 * 删除字符串中的乱码
	 * 
	 * @param content
	 * @return
	 */
	public static String deleteSpecialChar(String content) {
		if (content == null) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		char[] contentList = content.toCharArray();
		for (int i = 0; i < content.length(); i++) {
			String item = contentList[i] + "";
			if (item.matches(REGEX_CAPITAL_CHAR)
					|| item.matches(REGEX_CHINESE_CHAR)
					|| item.matches(REGEX_PUNCTUATION_CHAR)) {
				builder.append(item);
			}
		}
		return builder.toString();
	}
	/**
	 * String 数组转Integer数组，若空则返回空,调用时注意异常处理！
	 * wangjs	2019-1-29
	 * @param strs
	 * @return
	 */
	public static Integer[] stringArrayToIntegerArray(String[] strs){
		if(strs.length<=0 || strs==null){
			return new Integer[0];
		}else{
			int length=strs.length;
			Integer[] ins=new Integer[length];
			for (int i = 0; i < length; i++) {
				Integer in=Integer.valueOf(strs[i]);
				ins[i]=in;
			}
			return ins;
		}
	}

	/**
	 * 字符的转码
	 * @author sun yu chao
	 * @param byteEncode 把字符串以这种编码转为字节
	 * @param strEncode  把字节以这种编码转为字符串
	 * */
	public static String getEncodedStr(String origin, Charset byteEncode,Charset strEncode){
		byte[] bytes = origin.getBytes(byteEncode);
		return new String(bytes,strEncode);
	}

	/**
	 * 测试类  便于随时测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		try {  
			
		}catch(Exception e){
		}
	}
	
	
	/**
	 * 检查是否有特殊字符
	 * （由baseController中迁移而来）
	 * wangjs	2019-08-22
	 * @param str
	 * @return
	 */
	public static Map<String,String> validateSpecialString(String str){
		Map<String,String> info = new HashMap<String,String>();
		if(str!=null&&!"".equals(str)){			
			int index1 = str.indexOf("\n");//换行
			int index2 = str.indexOf("\r");//回车
			int index3 = str.indexOf("\t");//Tab
			if(index1==-1&&index2==-1&&index3==-1){
				if(str.indexOf("\\")!=-1){
					str=str.replace("\\","＼");
				}
				String regEx = DictUtil.findDictMap("special_string_RegExp");  
				boolean flag = str.matches(regEx); 
				if(flag){
					info.put("result", "true");
					info.put("errMsg", "不可包含特殊字符");
				}else{
					info.put("result", "false");
				}
				return info;
			}else{
				String errMsg = "不可包含";
				if(index1 != -1){
					errMsg += "换行";
				}
				if(index2 != -1){
					errMsg += "回车";
				}
				if(index3 != -1){
					errMsg += "Tab";
				}
				info.put("errMsg", errMsg);
				info.put("result", "true");
				return info;
			}
		}else{
			info.put("errMsg", "");
			info.put("result", "");
			return info;
		}
	}
			
}
