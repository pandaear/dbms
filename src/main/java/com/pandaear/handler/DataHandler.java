package com.pandaear.handler;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author: pandaear
 * @create: 2019-02-19 14:19
 **/
public class DataHandler <T>{
	/**
	 * 保存单条对象数据
	 * @param t
	 * @param tableHandler
	 * @return
	 */
	public int saveData(T t,TableHandler tableHandler){
		try {
			Writer writer = tableHandler.getWriter(true);
			//向文件中写入json串
			writer.write(strToNum(JSONObject.toJSONString(t)));
			//以换行分割
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * 保存集合数据
	 * @param t
	 * @param tableHandler
	 * @return
	 */
	public int saveList(List<T> t,TableHandler tableHandler){

			Writer writer = tableHandler.getWriter(true);
			t.stream().forEach(ele->{
				try {
					writer.write(strToNum(JSONObject.toJSONString(ele)));
					writer.write("\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 1;
	}

	/**
	 * 插入所有数据
	 * @param tableHandler
	 * @return
	 */
	public List<T> selectAll(TableHandler tableHandler){
		BufferedReader reader = tableHandler.getReader();
			List<T> list = new ArrayList<>();
		try {
			String line = reader.readLine();
			while (line != null){
				T t = (T)JSONObject.parse(numToStr(line));
				list.add(t);
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据条件查询数据
	 * @param params
	 * @param tableHandler
	 * @return
	 */
	public List<T> selectByParams(Map<String,Object> params,TableHandler tableHandler){
		BufferedReader reader = tableHandler.getReader();
		List<T> list = new ArrayList<>();
		try {
			String line = reader.readLine();
			while (line != null){
				JSONObject jsonObject = JSONObject.parseObject(numToStr(line));
				int size = 0;
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					Object o = jsonObject.get(entry.getKey());
					if ((jsonObject.get(entry.getKey())+"").equals(entry.getValue()+"")) {
						size ++;
					}
				}
				if (params.size() == size) {
					list.add((T)JSONObject.parse(numToStr(line)));
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据条件删除数据
	 * @param params
	 * @param tableHandler
	 */
	public void deleteData(Map<String,Object> params,TableHandler tableHandler){
		BufferedReader reader = tableHandler.getReader();
		try {
			StringBuffer stringBuffer = new StringBuffer();
			String line = reader.readLine();
			a:while (line != null){
				JSONObject jsonObject = JSONObject.parseObject(numToStr(line));
				int size = 0;
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					Object o = jsonObject.get(entry.getKey());
					if ((jsonObject.get(entry.getKey())+"").equals(entry.getValue()+"")) {
						size ++;
					}
				}
				if (params.size() == size) {
					line = reader.readLine();
					continue a;
				}
				stringBuffer.append(line).append("\n");
				line = reader.readLine();
			}
			BufferedWriter writer = tableHandler.getWriter(false);
			writer.write(stringBuffer.toString());
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将字符转换成数字
	 * @param str
	 * @return
	 */
	public String strToNum(String str){
		char[] chars = str.toCharArray();
		StringBuilder stringBuilder = new StringBuilder();
		int length = chars.length;
		for (int i = 0;i<length;i++) {
			int numericValue = (int)chars[i];
			stringBuilder.append(Integer.toString(numericValue));
			if (i < length -1) {
				stringBuilder.append("10x21");
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 将数字转换为字符
	 * @param str
	 * @return
	 */
	public String numToStr(String str){
		String[] split = str.split("10x21");
		StringBuilder stringBuilder = new StringBuilder();
		for (String s : split) {
			int i = Integer.parseInt(s);
			char ch = (char) i;
			stringBuilder.append(ch);
		}
		return stringBuilder.toString();
	}
}
