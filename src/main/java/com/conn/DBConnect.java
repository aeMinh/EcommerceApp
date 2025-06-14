package com.conn;

import com.createtable;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private static Connection conn = null;
	private static boolean initialized = false;

	public static Connection getConn() {
		if (conn != null) return conn;

		try {
			// Gọi tạo bảng tự động, chỉ 1 lần
			if (!initialized) {
				createtable.main(null);  // Gọi class createtable
				initialized = true;
			}

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ecommerceapp",
					"root", "123456" // Thay bằng pass nếu có
			);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
}
