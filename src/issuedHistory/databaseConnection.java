package issuedHistory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection
{
	public static Connection doConnect()
	{
		Connection con=null;
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 con=	DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_donor_databse?characterEncoding=latin1&useConfigs=maxPerformance","root","kirti123#");
			 System.out.println("Connected to mysql Successfully");
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
	public static void main(String []args)
	{
		doConnect();
	}
}