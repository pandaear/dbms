package com.pandaear.handler;

import com.pandaear.info.PathInfo;

import java.io.*;

/**
 * 表操作类
 * @author: pandaear
 * @create: 2019-02-19 11:22
 **/
public class TableHandler {
	//可操作的表文件
	private File file = null;

	public TableHandler(){}

	public TableHandler(String tableName){
		this.createTable(tableName);
	}

	/**
	 * 创建数据表文件
	 * @param name 文件名
	 * @return
	 */
	public int createTable(String name){
		this.file = new File(PathInfo.ABSOLUTE_PATH+PathInfo.TABLE_PATH+name+PathInfo.FILE_TYPE);
		//获取父目录
		File fileParent = file.getParentFile();
		//判断是否存在
		if (!fileParent.exists()) {
			//创建父目录文件
			fileParent.mkdirs();

		}
		if (file.canExecute()) {
			return 1;
		}
		try /*(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PathInfo.ABSOLUTE_PATH+PathInfo.TABLE_PATH+name+PathInfo.FILE_TYPE))))*/{
			file.createNewFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	/**
	 * 删除table操作
	 * @param name 文件名
	 * @return
	 */
	public int deleteTable(String name){
		try {
			File file = new File(PathInfo.ABSOLUTE_PATH+PathInfo.TABLE_PATH+name+PathInfo.FILE_TYPE);
			file.delete();
			return 1;
		}catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获取写出对象
	 * @param append
	 * @return
	 */
	public BufferedWriter getWriter(boolean append){
		if (file != null && file.canExecute()) {
			try {
				return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,append)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取读入对象
	 * @return
	 */
	public BufferedReader getReader(){
		if (file != null && file.canExecute()) {
			try {
				return new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		new TableHandler().createTable("abc");
		new TableHandler().deleteTable("abc");
	}
}
