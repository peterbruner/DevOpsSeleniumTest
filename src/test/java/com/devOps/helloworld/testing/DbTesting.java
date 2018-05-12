/**
 * 
 */
package com.devOps.helloworld.testing;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author pbruner
 *
 */
class DbTesting {

//	public static void selectQuery() throws SQLException, ClassNotFoundException { 
//		   String dbURL = "jdbc:sqlserver://ipAddress:portNumber/dbName";
//		   String username = myUserName;
//		        String password = myPassword;
//		        //Load MS SQL JDBC Driver
//		        Class.forName("net.sourceforge.jtds.jdbc.Driver");
//		        //Creating connection to the database
//		        Connection con = DriverManager.getConnection(dbURL,username,password);
//		        //Creating statement object
//		      Statement st = con.createStatement();
//		      String selectquery = "SELECT * FROM <tablename> WHERE <condition>";
//		        //Executing the SQL Query and store the results in ResultSet
//		      ResultSet rs = st.executeQuery(selectquery);
//		      //While loop to iterate through all data and print results
//		      while (rs.next()) {
//		         System.out.println(rs.getString("transaction_datetime"));
//		      }
//		        //Closing DB Connection
//		      con.close();
//		   }
	
	public ArrayList<User> readData() throws Exception 
	{
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//connect = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DevOps;user=sadevops;password=zb4^1OL7&6mbh/F6T6YS5j!V"); //for server testing
			connect = DriverManager.getConnection("jdbc:sqlserver://10.118.45.4;databaseName=DevOps;user=sadevops;password=zb4^1OL7&6mbh/F6T6YS5j!V"); //local machine testing
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select [DevOps].[dbo].[Users].UserName, " 
				+ "[DevOps].[dbo].[Users].LastName, "
				+ "[DevOps].[dbo].[Users].FirstName "
				+ "from [DevOps].[dbo].[Users]");
			
			ArrayList<User> users = new ArrayList<User>();
			
			while (resultSet.next())
			{
				User user = new User();
				user.setUserName(resultSet.getString(1));
				user.setLastName(resultSet.getString(2));
				user.setFirstName(resultSet.getString(3));
				users.add(user);
			}
			
			return users;
			
			
		}
		catch (Exception e)
		{
			throw e;
		}
		finally 
		{

		}
	}
	
//	public void readData() throws SQLException, ClassNotFoundException, Exception 
//	{
//		Connection connect = null;
//		Statement statement = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		
//		try 
//		{
//			//Class.forName("com.mysql.jdbc.Driver");
//			connect = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sadevops;password=zb4^1OL7&6mbh/F6T6YS5j!V");
//			statement = connect.createStatement();
//			resultSet = statement.executeQuery("Select * from users");
//			System.out.println(resultSet.getString(1));
//			
//		}
//		catch (Exception e)
//		{
//			throw e;
//		}
//		finally 
//		{
//
//		}
//	} //end readData()
	
//	public void writeData() throws Exception 
//	{
//		Connection connect = null;
//		Statement statement = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		
//		try 
//		{
//			final String connectionUrl = "jdbc:sqlserver://10.118.45.4;databaseName=DevOps;user=sadevops;password=zb4^1OL7&6mbh/F6T6YS5j!V";
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			connect = DriverManager.getConnection(connectionUrl);
//			statement = connect.createStatement();
//			
//			//dont need to do an Insert
//			String sqlStatement = "Insert into [DevOps].[dbo].[Users] VALUES ('45', 'DOENT', 'John', 'jdoe', 'jdoe1', '123 some street', '','Somecity', 'va', 'USA', '22121', 'notitle', 'xxx', '111');";
//			resultSet = statement.executeQuery(sqlStatement);
//			
//			
//		}
//		catch (Exception e)
//		{
//			throw e;
//		}
//		finally 
//		{
//
//		}
//	} //end writeData()
	
	
}
