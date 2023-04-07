package com.firday.util;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 *
 * Web����˷���JSON������
 * ����������FastJSON
 * ������֧�ַ���JSON��JSONP��ʽ����
 * @author accountwcx@qq.com
 *
 */
public class ResponseJsonUtils {
	/**
	 * Ĭ���ַ�����
	 */
	private static String encoding = "UTF-8";

	/**
	 * JSONPĬ�ϵĻص�����
	 */
	private static String callback = "callback";

	/**
	 * FastJSON�����л�����
	 */
	private static SerializerFeature[] features =  new SerializerFeature[]{
		//���Map��ΪNull��ֵ
		SerializerFeature.WriteMapNullValue,

		//����Boolean����ΪNull�������Ϊfalse
		SerializerFeature.WriteNullBooleanAsFalse,

		//����ListΪNull�������Ϊ[]
		SerializerFeature.WriteNullListAsEmpty,

		//����NumberΪNull�������Ϊ0
		SerializerFeature.WriteNullNumberAsZero,

		//���Null�ַ���
		SerializerFeature.WriteNullStringAsEmpty,

		//��ʽ���������
		SerializerFeature.WriteDateUseDateFormat
	};

	/**
	 * ��Java����JSON���л�
	 * @param obj ��ҪJSON���л���Java����
	 * @return JSON�ַ���
	 */
	private static String toJSONString(Object obj){
		return JSON.toJSONString(obj, features);
	}

	/**
	 * ����JSON��ʽ����
	 * @param response
	 * @param data �����ص�Java����
	 * @param encoding ����JSON�ַ����ı����ʽ
	 */
	public static void json(HttpServletResponse response, Object data, String encoding){
		//���ñ����ʽ
		response.setContentType("text/plain;charset=" + encoding);
		response.setCharacterEncoding(encoding);

		PrintWriter out = null;
		try{
			out = response.getWriter();
			out.write(toJSONString(data));
			out.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * ����JSON��ʽ���ݣ�ʹ��Ĭ�ϱ���
	 * @param response
	 * @param data �����ص�Java����
	 */
	public static void json(HttpServletResponse response, Object data){
		json(response, data, encoding);
	}

	/**
	 * ����JSONP���ݣ�ʹ��Ĭ�ϱ����Ĭ�ϻص�����
	 * @param response
	 * @param data JSONP����
	 */
	public static void jsonp(HttpServletResponse response, Object data){
		jsonp(response, callback, data, encoding);
	}

	/**
	 * ����JSONP���ݣ�ʹ��Ĭ�ϱ���
	 * @param response
	 * @param callback JSONP�ص���������
	 * @param data JSONP����
	 */
	public static void jsonp(HttpServletResponse response, String callback, Object data){
		jsonp(response, callback, data, encoding);
	}

	/**
	 * ����JSONP����
	 * @param response
	 * @param callback JSONP�ص���������
	 * @param data JSONP����
	 * @param encoding JSONP���ݱ���
	 */
	public static void jsonp(HttpServletResponse response, String callback, Object data, String encoding){
		StringBuffer sb = new StringBuffer(callback);
		sb.append("(");
		sb.append(toJSONString(data));
		sb.append(");");

		// ���ñ����ʽ
		response.setContentType("text/plain;charset=" + encoding);
		response.setCharacterEncoding(encoding);

		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(sb.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getEncoding() {
		return encoding;
	}

	public static void setEncoding(String encoding) {
		ResponseJsonUtils.encoding = encoding;
	}

	public static String getCallback() {
		return callback;
	}

	public static void setCallback(String callback) {
		ResponseJsonUtils.callback = callback;
	}
}

