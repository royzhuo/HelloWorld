package com.struts2.util;

import java.sql.*;

import javax.management.RuntimeErrorException;

public class DB {

	// 定义连接数据库所需的参数
	 static String driver = "com.mysql.jdbc.Driver";
	 static String url = "jdbc:mysql://localhost:3306/bbs2009";
	 static String username = "root";
	 static String password = "123";

	 static Connection cn=null;
	 static PreparedStatement ps=null;
	 static ResultSet rs=null;

	// 加载驱动
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 连接数据库
	public static Connection getCon() {
		try {
			cn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cn;
	}

	// 关闭数据库
	public static void close() {
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cn = null;
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps = null;
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}

	}

	// 对数据进行增删改操作
	public static void executeUpdate(String sql, String[] parameters) {
		
		try {
			cn = getCon();
			// 进行数据操作
			ps = cn.prepareStatement(sql);

			// 对问号进行赋值
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}

			// 执行操作
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			close();
		}
	}

	// 查询数据
	public static ResultSet executeQuery(String sql, String[] parameters) {
		cn = getCon();

		try {

			// 数据查询
			ps = cn.prepareStatement(sql);
			// 对问号赋值
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}

			// 执行查询
			rs = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;

	}

}
