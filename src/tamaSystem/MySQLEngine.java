package tamaSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/*MySQL Engine
 * 
 * This class will work with MySQL.
 * It will get DB and maybe get DB too.
 * 
 * MUST START SOMEWERE WHERE OTHER CLASS CAN GET GAMEVALUES?
 */

public class MySQLEngine {
	//ON TOP INCASE OF UPDATE OR CHANGE PATH.
	private final String SERVER_NAME = "localhost";
	private final String DATABASE_NAME = "dbprojecttama";
	private final int PORT = 3306;

	private MysqlDataSource ds;
	private Connection con = null;
	private	Statement queryCaller = null;
	private ResultSet result = null;
	private String inString = null;
	private ArrayList <Integer> gameValues = new ArrayList<Integer>();

	public void getMySQLDB(String user, String password){
		connectionMethod(user, password);
		statementMethod();
		getGameValue();
		testSYSO();//TEST METOD
	}

	private void connectionMethod(String user, String password){
		ds = new MysqlDataSource(); 
		ds.setServerName(SERVER_NAME);
		ds.setPort(PORT);
		ds.setDatabaseName(DATABASE_NAME);

		try {
			//DO NOT SHOW PASSWORD CODE IN YOUR CLIENT
			con = ds.getConnection(user, password); //try to connect to ds. With user and password.
		} catch (SQLException e) {
			System.out.println("-----ERROR: Could not connect!-----"); //Good to make a syso, just to check where the problem is.
			return;
		}		
		System.out.println("*****Connection succsessfull!*****"); //Good to make a syso that tells that its online.

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
		System.out.println("*****Statement Succsessfull!*****");
	}

	//INSERT, ONE
	private void insertMethod(){
		int affectedRows = 0;
		try {
			affectedRows = queryCaller.executeUpdate("INSERT INTO actor VALUES('201', 'IVAN', 'DRAGO', '2006-02-15 04:34:33')");
		} catch (SQLException e) {
			System.out.println("-----INSERT ERROR-----" + e.getMessage());
		}
		System.out.println("*****INSERT SUCCSESSFULL*****");
		System.out.println("Adected rows: " + affectedRows);
	}
	
	//INSERT, 

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
			System.out.println("-----QueryCaller ERROR!-----" + e.getMessage());
		}

		System.out.println("*****QueryCaller succsess!*****");
		//		System.out.println(inString);
	}

	public ArrayList<Integer> getGameValue(){
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
				// DATA BAS STUFF always starts at 1, not 0 like in normal JAVA.
				for (int i = 1; i < nCols; i++) {
				int	x = 0;
					System.out.println(result.getInt(i) + " ");
					gameValues.add(x, result.getInt(i));
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("-----QueryCaller ERROR!-----" + e.getMessage()); //TESTER
		}
		System.out.println("*****QueryCaller succsess!*****"); //TESTER
		closeEverything();
		return gameValues;
	}

	private void closeEverything(){
		//CLOSE EVERYTHING
		if(result != null){
			try {
				result.close();
			} catch (SQLException e) {
				System.out.println("Warning: Couldn't close results.");
			}
		}
		if(queryCaller != null){
			try {
				queryCaller.close();
			} catch (SQLException e) {
				System.out.println("Warning: Couldn't close the statement.");
			}
		}
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Warning: Couldn't close the connection.");
			}
		}
	}

	private void testSYSO(){
			System.out.println(gameValues);
	}
}
