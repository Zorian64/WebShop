package org.zorian.com;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.zorian.com.entity.Client;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		FileInputStream fis;
		Properties property = new Properties();

		try {
			fis = new FileInputStream("src/main/resources/app.properties");
			property.load(fis);

			String driver = property.getProperty("db.driver");
			String url = property.getProperty("db.url");
			String username = property.getProperty("db.username");
			String password = property.getProperty("db.password");

			System.out.println(
					"DRIVER: " + driver + ", URL: " + url + ", USERNAME: " + username + ", PASSWORD: " + password);

			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);

			System.out.println("Connected database successfully...");

			Statement stmt = conn.createStatement();

			// String sql = "CREATE TABLE CLIENT " +
			// "(id INTEGER not NULL, " +
			// " first_name VARCHAR(255), " +
			// " last_name VARCHAR(255), " +
			// " phone_number VARCHAR(255), " +
			// " address VARCHAR(255), " +
			// " email VARCHAR(255), " +
			// " PRIMARY KEY ( id ))";
			//
			// stmt.executeUpdate(sql);
			// System.out.println("Created table in given database...");

			// String sql = "INSERT INTO CLIENT " +
			// "VALUES (100, 'Zorian', 'Fedoryga', '+380636961273',
			// 'Lviv,Patona' ,'fedoryga_zorik@mail.ru')";
			// stmt.executeUpdate(sql);
			// System.out.println("Inserted records into the table...");
			//

			String sql = "SELECT id, first_name, last_name FROM CLIENT";
			ResultSet rs = stmt.executeQuery(sql);
			Client client = new Client();
			while (rs.next()) {
				
				// Retrieve by column name
				 client.setId(rs.getInt("id"));
				 client.setFirstName(rs.getString("first_name"));
				 client.setLastName(rs.getString("last_name"));
				

				
				System.out.print(client);

				
			}
			rs.close();

		} catch (IOException e) {
			System.err.println("ОШИБКА: Файл свойств отсуствует!");
		}

	}

}
