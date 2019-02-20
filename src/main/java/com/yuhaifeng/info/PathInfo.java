package com.yuhaifeng.info;

/**
 * @author: yuhaifeng
 * @create: 2019-02-19 11:23
 **/
public class PathInfo {
	private PathInfo(){}
	//项目的绝对路径
	public static final String ABSOLUTE_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	//表文件存储地址
	public static final String TABLE_PATH = "/db/data/";
	//表文件的后缀
	public static final String FILE_TYPE = ".sd";

	public static void main(String[] args) {
		System.out.println(PathInfo.ABSOLUTE_PATH);
	}
}
