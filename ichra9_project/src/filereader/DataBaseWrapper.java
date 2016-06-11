package filereader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseWrapper {

	/* Connexion à la base de données */
	private String url = "jdbc:mysql://localhost:3306/java_project";
	private String dbuser = "root";
	private String userPwd = "";
	private Connection connexion = null;

	DataBaseWrapper(){
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			connexion = DriverManager.getConnection( url, dbuser, userPwd );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void confCleanUp(){
		if(connexion != null){
			try {
				connexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String createTableQueryBuilder(String tablename, String[] tableFields){

		String query = "CREATE TABLE "+tablename+" ("
				+ " id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY";

		for(int i=0; i<tableFields.length; i++){
			query = query+", "+tableFields[i]+" VARCHAR(30)";
		}
		query = query+")";
		return query;
	}


	public boolean createTable(String tableName, String[] tableFields){
		boolean result = true;
		if(connexion != null){
			try {
				Statement statement = connexion.createStatement();
				String query = createTableQueryBuilder(tableName, tableFields);
				statement.execute("DROP TABLE IF EXISTS "+tableName+";");
				System.out.println("create table query: "+query);
				statement.execute(query);
				statement.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean insertValues(String tableName, String tableFieldsList, String valuesList){
		boolean result = true;
		if(connexion != null){
			try {
				Statement statement = connexion.createStatement();
				String query = "INSERT INTO "+tableName+" ("+tableFieldsList+") VALUES ("+valuesList+");";
				System.out.println("insert into query: "+query);
				statement.execute(query);
				statement.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
