package common;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;

public class JDBConnect {
	protected Connection con;

	public JDBConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/musthave";
			String id = "root";
			String pwd = "tiger";
			con = DriverManager.getConnection(url, id, pwd);

			System.out.println("DB 연결 성공(기본생성자)");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public JDBConnect(String driver, String url, String id, String pwd) {
		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB연결 성공(인수생성자1)");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public JDBConnect(ServletContext application) {
		try {
			String driver = application.getInitParameter("MySQLDriver");
			Class.forName(driver);

			String url = application.getInitParameter("MySQLURL");
			String id = application.getInitParameter("MySQLId");
			String pwd = application.getInitParameter("MySQLPwd");
			con= DriverManager.getConnection(url, id, pwd);

			System.out.println("DB연결 성공(인수생성자2)");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}

	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
