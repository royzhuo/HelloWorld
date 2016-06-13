package com.struts2.util;

import java.sql.*;

import javax.management.RuntimeErrorException;

public class DB {

	// �����������ݿ�����Ĳ���
	 static String driver = "com.mysql.jdbc.Driver";
	 static String url = "jdbc:mysql://localhost:3306/bbs2009";
	 static String username = "root";
	 static String password = "123";

	 static Connection cn=null;
	 static PreparedStatement ps=null;
	 static ResultSet rs=null;

	// ��������
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �������ݿ�
	public static Connection getCon() {
		try {
			cn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cn;
	}

	// �ر����ݿ�
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

	// �����ݽ�����ɾ�Ĳ���
	public static void executeUpdate(String sql, String[] parameters) {
		
		try {
			cn = getCon();
			// �������ݲ���
			ps = cn.prepareStatement(sql);

			// ���ʺŽ��и�ֵ
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}

			// ִ�в���
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			close();
		}
	}

	// ��ѯ����
	public static ResultSet executeQuery(String sql, String[] parameters) {
		cn = getCon();

		try {

			// ���ݲ�ѯ
			ps = cn.prepareStatement(sql);
			// ���ʺŸ�ֵ
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}

			// ִ�в�ѯ
			rs = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;

	}

}
