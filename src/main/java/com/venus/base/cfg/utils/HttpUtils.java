package com.venus.base.cfg.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
	private static final Integer READ_TIMEOUT = 5000;
	private static final Integer CONNECT_TIMEOUT = 10000;
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	public static String sendHttpGet(String url, String param) {
		HttpURLConnection connection = null;
		InputStream is = null;
        BufferedReader br = null;
		
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            
            connection = (HttpURLConnection)realUrl.openConnection();
            
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            connection.connect();
            if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				StringBuffer sbf = new StringBuffer();
				String temp = null;
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
				    sbf.append("\r\n");
				}
				
				return sbf.toString();
            } else {
            	logger.error("发送http/get请求失败,{}/{}" , connection.getResponseCode() , urlNameString);
            }
            
            return null;
        } catch (Exception e) {
        	logger.error("发送http/get请求异常,{}" , e.getMessage());
        	
            return null;
        } finally {
        	if (connection != null) {
        		connection.disconnect();
        	}
        	try {
	        	if (is != null) {
	        		is.close();
	        	}
	        	
	        	if (br != null) {
	        		br.close();
	        	}
        	} catch (Exception e) {
        		logger.error("发送http/get关闭流异常,{}" , e.getMessage());
        	}
        }
    }
}
