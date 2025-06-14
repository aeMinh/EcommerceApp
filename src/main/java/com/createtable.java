package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class createtable {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ecommerceapp", "root", "123456");

			Statement stmt = conn.createStatement();
			String[] queries = """
                CREATE TABLE IF NOT EXISTS brand ( bid INT DEFAULT NULL, bname VARCHAR(50) DEFAULT NULL );
                INSERT INTO brand VALUES (1,'samsung'),(2,'sony'),(3,'lenovo'),(4,'acer'),(5,'onida');

                CREATE TABLE IF NOT EXISTS cart (
                  Name VARCHAR(100) DEFAULT NULL, bname VARCHAR(50) DEFAULT NULL,
                  cname VARCHAR(50) DEFAULT NULL, pname VARCHAR(50) DEFAULT NULL,
                  pprice INT DEFAULT NULL, pquantity INT DEFAULT NULL, pimage VARCHAR(200) DEFAULT NULL );

                CREATE TABLE IF NOT EXISTS category ( cid INT DEFAULT NULL, cname VARCHAR(50) DEFAULT NULL );
                INSERT INTO category VALUES (1,'laptop'),(2,'tv'),(3,'mobile'),(4,'watch');

                CREATE TABLE IF NOT EXISTS contactus (
                  id INT NOT NULL AUTO_INCREMENT, Name VARCHAR(100) DEFAULT NULL,
                  Email_Id VARCHAR(100) DEFAULT NULL, Contact_No INT DEFAULT NULL,
                  Message VARCHAR(8000) DEFAULT NULL, PRIMARY KEY (id) );

                CREATE TABLE IF NOT EXISTS customer (
                  Name VARCHAR(100) DEFAULT NULL, Password VARCHAR(20) DEFAULT NULL,
                  Email_Id VARCHAR(100) DEFAULT NULL, Contact_No INT DEFAULT NULL );

                CREATE TABLE IF NOT EXISTS login (
                  username VARCHAR(100) DEFAULT NULL, password VARCHAR(100) DEFAULT NULL );

                CREATE TABLE IF NOT EXISTS order_details (
                  Date VARCHAR(100) DEFAULT NULL, Name VARCHAR(100) DEFAULT NULL,
                  bname VARCHAR(50) DEFAULT NULL, cname VARCHAR(50) DEFAULT NULL,
                  pname VARCHAR(50) DEFAULT NULL, pprice INT DEFAULT NULL,
                  pquantity INT DEFAULT NULL, pimage VARCHAR(200) DEFAULT NULL );

                CREATE TABLE IF NOT EXISTS orders (
                  Order_Id INT NOT NULL AUTO_INCREMENT, Customer_Name VARCHAR(100) DEFAULT NULL,
                  Customer_City VARCHAR(45) DEFAULT NULL, Date VARCHAR(100) DEFAULT NULL,
                  Total_Price INT DEFAULT NULL, Status VARCHAR(45) DEFAULT NULL, PRIMARY KEY (Order_Id) );

                CREATE TABLE IF NOT EXISTS product (
                  pid INT NOT NULL AUTO_INCREMENT, pname VARCHAR(50) DEFAULT NULL,
                  pprice INT DEFAULT NULL, pquantity INT DEFAULT NULL, pimage VARCHAR(200) DEFAULT NULL,
                  bid INT DEFAULT NULL, cid INT DEFAULT NULL, PRIMARY KEY (pid) );

                CREATE VIEW IF NOT EXISTS viewlist AS
                  SELECT brand.bname, category.cname, product.pname, product.pprice,
                         product.pquantity, product.pimage
                  FROM brand JOIN product ON brand.bid = product.bid
                             JOIN category ON product.cid = category.cid;

                CREATE VIEW IF NOT EXISTS mobile AS
                  SELECT brand.bname, category.cname, product.pname, product.pprice,
                         product.pquantity, product.pimage
                  FROM brand JOIN product ON brand.bid = product.bid
                             JOIN category ON product.cid = category.cid
                  WHERE category.cid = 3;

                CREATE VIEW IF NOT EXISTS laptop AS
                  SELECT brand.bname, category.cname, product.pname, product.pprice,
                         product.pquantity, product.pimage
                  FROM brand JOIN product ON brand.bid = product.bid
                             JOIN category ON product.cid = category.cid
                  WHERE category.cid = 1;

                CREATE VIEW IF NOT EXISTS tv AS
                  SELECT brand.bname, category.cname, product.pname, product.pprice,
                         product.pquantity, product.pimage
                  FROM brand JOIN product ON brand.bid = product.bid
                             JOIN category ON product.cid = category.cid
                  WHERE category.cid = 2;

                CREATE VIEW IF NOT EXISTS watch AS
                  SELECT brand.bname, category.cname, product.pname, product.pprice,
                         product.pquantity, product.pimage
                  FROM brand JOIN product ON brand.bid = product.bid
                             JOIN category ON product.cid = category.cid
                  WHERE category.cid = 4;

                CREATE TABLE IF NOT EXISTS usermaster (
                  Name VARCHAR(100) DEFAULT NULL,
                  Password VARCHAR(20) DEFAULT NULL
                );
                INSERT IGNORE INTO usermaster VALUES ('admin','admin');
            """.split(";");

			for (String q : queries) {
				if (!q.trim().isEmpty()) {
					try {
						stmt.executeUpdate(q.trim());
						System.out.println("✅ Executed: " + q.trim());
					} catch (Exception ex) {
						System.out.println("❌ Failed: " + q.trim());
						ex.printStackTrace();
					}
				}
			}

			// insert product separately
			String[] productInserts = {
					"INSERT IGNORE INTO product VALUES (5,'sonysmart',50000,1,'sonywatch.webp',2,4)",
					"INSERT IGNORE INTO product VALUES (6,'GalaxyBook',45000,1,'samsunglaptop.jpg',1,1)",
					"INSERT IGNORE INTO product VALUES (7,'smarttv',28000,1,'onidatv.jpg',5,2)",
					"INSERT IGNORE INTO product VALUES (8,'smartphone',15000,1,'lenovomobile.webp',3,3)",
					"INSERT IGNORE INTO product VALUES (9,'aspire',52000,1,'acerlaptop.jpg',4,1)",
					"INSERT IGNORE INTO product VALUES (10,'Braviass',52,1,'sonytv.jpg',2,2)",
					"INSERT IGNORE INTO product VALUES (11,'GalaxyWatch',22000,1,'galaxywatch.webp',1,4)",
					"INSERT IGNORE INTO product VALUES (14,'kdl',45000,1,'sony kdl.jpg',2,2)",
					"INSERT IGNORE INTO product VALUES (15,'series a7',21000,1,'acer series a7.jpg',4,2)",
					"INSERT IGNORE INTO product VALUES (17,'leo',31000,1,'onida leo.jpg',5,2)",
					"INSERT IGNORE INTO product VALUES (18,'crystal',42000,1,'samsung crystal.webp',1,2)",
					"INSERT IGNORE INTO product VALUES (19,'Aspire 7',55000,1,'acer aspire7.jpg',4,1)",
					"INSERT IGNORE INTO product VALUES (20,'ideapad',37000,1,'lenovo ideapad.jpg',3,1)",
					"INSERT IGNORE INTO product VALUES (21,'legion',51000,1,'lenovo legion.jpg',3,1)",
					"INSERT IGNORE INTO product VALUES (22,'Galaxy Z Fold3',66000,1,'Galaxy z fold3.jpg',1,3)",
					"INSERT IGNORE INTO product VALUES (23,'Galaxy S22',55000,1,'Samsung galaxy s22.webp',1,3)",
					"INSERT IGNORE INTO product VALUES (24,'Xperia 1v',56000,1,'sony xperia 1v.jpg',2,3)",
					"INSERT IGNORE INTO product VALUES (26,'A850',14500,1,'lenovo a850.jpg',3,3)",
					"INSERT IGNORE INTO product VALUES (27,'Galaxy watch1',8000,1,'galaxy watch.jpg',1,4)",
					"INSERT IGNORE INTO product VALUES (28,'Galaxy Watch2',95000,1,'galaxy watch4.jpg',1,4)",
					"INSERT IGNORE INTO product VALUES (29,'Smart Fit',11000,1,'smart fit.jpg',3,4)",
					"INSERT IGNORE INTO product VALUES (30,'Sony Smart2',12000,1,'sony smart2.webp',2,4)",
					"INSERT IGNORE INTO product VALUES (31,'Gaming Predator',120000,1,'Acer Predator.jpg',4,1)",
					"INSERT IGNORE INTO product VALUES (32,'Liquid',16000,1,'Acer liquid.jpg',4,3)",
					"INSERT IGNORE INTO product VALUES (33,'Neo QLED',46000,1,'Samsung neo Qled.webp',1,2)",
					"INSERT IGNORE INTO product VALUES (34,'VAIO',53000,1,'Sony Vaio.jpg',2,1)",
					"INSERT IGNORE INTO product VALUES (35,'Xperia Z',32000,1,'sonyxperiaz.png',2,3)"
			};

			for (String insert : productInserts) {
				try {
					stmt.executeUpdate(insert);
					System.out.println("✅ Inserted: " + insert);
				} catch (Exception ex) {
					System.out.println("❌ Failed to insert: " + insert);
					ex.printStackTrace();
				}
			}

			stmt.close();
			conn.close();
			System.out.println("✅ Database initialized successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
