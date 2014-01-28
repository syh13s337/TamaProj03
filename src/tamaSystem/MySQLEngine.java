package tamaSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/*MySQL Engine
 * 
 * This class will work with MySQL.
 * It will get DB and maybe get DB too.
 * 
 * 
 * 
 */

public class MySQLEngine {

	private MysqlDataSource ds;
	private Connection con = null;
	private	Statement queryCaller = null;
	private ResultSet result = null;
	private String inString = null;

	public void getMySQLDB(String user, String password){
		connectionMethod(user, password);
		statementMethod();
		getGameValue();
	}
	
	private void connectionMethod(String user, String password){
		ds = new MysqlDataSource(); 
		ds.setServerName("localhost");
		ds.setPort(3306);
		ds.setDatabaseName("dbprojecttama");

		try {
			//DO NOT SHOW PASSWORD CODE IN YOUR CLIENT
			con = ds.getConnection(user, password); //try to connect to ds. With user and password.
		} catch (SQLException e) {
			System.out.println("-----ERROR: Could not connect!"); //Good to make a syso, just to check where the problem is.
			return;
		}		
		System.out.println("*****Connection succsessfull!"); //Good to make a syso that tells that its online.
		
	}
	
	
	private void statementMethod(){
		try {
			queryCaller = con.createStatement();
		} catch (SQLException e) {
			try {
				con.close(); //close connection
			} catch (SQLException e1) {
			}
			System.out.println("-----STATETMENT ERROR!" + e.getMessage());
		}
		System.out.println("*****Statement Succsessfull!");
	}
	
	//INSERT
	private void insertMethod(){
		int affectedRows = 0;
		try {
			affectedRows = queryCaller.executeUpdate("INSERT INTO actor VALUES('201', 'IVAN', 'DRAGO', '2006-02-15 04:34:33')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Adected rows: " + affectedRows);
	}

	//select and get information, Need path to column.
	private void resultMethod(){
		try {
			//SELECT
			result = queryCaller.executeQuery("SELECT * FROM gamevalues;");
			result.beforeFirst();
			ResultSetMetaData resultInfo = result.getMetaData();
			int nCols = resultInfo.getColumnCount();

			for (int i = 1; i < nCols; i++) {
				System.out.println(resultInfo.getColumnLabel(i) + " ");

			}

			while(result.next()){
				//				System.out.println(result.getString("first_name"));	
				//				inString += " " + result.getString("first_name"); 

				// DATA BAS STUFF always starts at 1, not 0 like in normal JAVA.
				for (int i = 1; i < nCols; i++) {
					System.out.println(result.getString(i) + " ");
				}
				System.out.println();
			}

		} catch (SQLException e) {
			System.out.println("-----QueryCaller ERROR!" + e.getMessage());
		}

		System.out.println("*****QueryCaller succsess!");
		//		System.out.println(inString);
	}
	
	private void getGameValue(){
		try {
			//SELECT
			result = queryCaller.executeQuery("SELECT * FROM gamevalues;");
			result.beforeFirst();
			ResultSetMetaData resultInfo = result.getMetaData();
			int nCols = resultInfo.getColumnCount();

			for (int i = 1; i < nCols; i++) {
				System.out.println(resultInfo.getColumnLabel(i) + " ");

			}

			while(result.next()){
				//				System.out.println(result.getString("first_name"));	
				//				inString += " " + result.getString("first_name"); 

				// DATA BAS STUFF always starts at 1, not 0 like in normal JAVA.
				for (int i = 1; i < nCols; i++) {
					System.out.println(result.getString(i) + " ");
				}
				System.out.println();
			}

		} catch (SQLException e) {
			System.out.println("-----QueryCaller ERROR!" + e.getMessage());
		}

		System.out.println("*****QueryCaller succsess!");
		//		System.out.println(inString);
		
		
	}
}
