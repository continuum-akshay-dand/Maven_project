package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.lti.entity.Customer;

//Data Access Object
public class CustomerDao {

	//public void add(int id, String name, String email) {
	public void add(Customer customer) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Properties dbProps = new Properties();
			dbProps.load(this.getClass().getClassLoader().getResourceAsStream("dev-db.properties"));
			
			Class.forName(dbProps.getProperty("driverClassName"));
			conn = DriverManager.getConnection(dbProps.getProperty("url"),dbProps.getProperty("username"),dbProps.getProperty("password"));

			String sql = "insert into customer values(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer.getId());
			stmt.setString(2, customer.getName());
			stmt.setString(3, customer.getEmail());
			stmt.executeUpdate();
		}
		catch(Exception e) { //bad, should catch individual exception(s)
			e.printStackTrace(); //very bad, should throw user defined exception instead
		}
		finally {
			try { stmt.close(); } catch(Exception e) { }
			try { conn.close(); } catch(Exception e) { }
		}
	}
}
