//getColumnClassName.java
//각 Column의 데이터를 받아오기 위해 필요한 java 내부 Class를 출력해준다.

import java.sql.*;
import javax.sql.*;

public class getColumnClassName
{
    protected static final String GOLDILOCKS_DRIVER_CLASS = "sunje.goldilocks.jdbc.GoldilocksDriver";
    protected static final String URL_BASIC = "jdbc:goldilocks://127.0.0.1:48900/test";
    protected static final String URL_NAMED = "jdbc:goldilocks://127.0.0.1:48900/test?program=MySample";
    protected static final String URL_FOR_DEBUGGING = URL_BASIC + "?global_logger=console&trace_log=on&query_log=on&protocol_log=on";

    public static Connection createConnectionByDriverManager(String id, String password) throws SQLException
    {
        try
        {
            Class.forName(GOLDILOCKS_DRIVER_CLASS);
        }
        catch (ClassNotFoundException sException)
        {
        }

        return DriverManager.getConnection(URL_BASIC, "test", "test");
    }

    public static Connection createConnectionByDataSource(String id, String password) throws SQLException
    {
        sunje.goldilocks.jdbc.GoldilocksDataSource sDataSource = new sunje.goldilocks.jdbc.GoldilocksDataSource();
        
        sDataSource.setDatabaseName("test");
        sDataSource.setServerName("127.0.0.1");
        sDataSource.setPortNumber(48900);
        sDataSource.setUser(id);
        sDataSource.setPassword(password);

        return sDataSource.getConnection();
    }
    
    public static void main(String[] args) throws SQLException
    {
	String sql = "CREATE TABLE intervaltest(c1 int, c2 varchar(20), c3 date, c4 timestamp, c5 binary(20), c6 varbinary(20), c7 long varbinary)";
        Connection con = createConnectionByDriverManager("test", "test");
	
	Statement stmt = con.createStatement();
	
	System.out.println("Create table : "+sql);
	stmt.execute(sql);

	ResultSet rs = stmt.executeQuery("SELECT * FROM intervaltest");

	System.out.println();
	ResultSetMetaData md = rs.getMetaData();
	System.out.println("c1 = "+md.getColumnClassName(1));
	System.out.println("c2 = "+md.getColumnClassName(2));
	System.out.println("c3 = "+md.getColumnClassName(3));
	System.out.println("c4 = "+md.getColumnClassName(4));
	System.out.println("c5 = "+md.getColumnClassName(5));
	System.out.println("c6 = "+md.getColumnClassName(6));
	System.out.println("c7 = "+md.getColumnClassName(7));
	
	System.out.println();
	System.out.println("Drop table...");
	stmt.execute("DROP TABLE intervaltest");


	con.close();
    }
}

