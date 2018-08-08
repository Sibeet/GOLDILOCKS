//getDefaultTransactionIsolation_test.java
//DBMS의 Default Transaction Isolation level을 출력하는 메소드

import java.sql.*;
import javax.sql.*;

public class getDefaultTransactionIsolation_test
{
    protected static final String GOLDILOCKS_DRIVER_CLASS = "sunje.goldilocks.jdbc.GoldilocksDriver";
    protected static final String URL_BASIC = "jdbc:goldilocks://192.168.0.120:48900/test";
    protected static final String URL_NAMED = "jdbc:goldilocks://192.168.0.120:48900/test?program=MySample";
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

        return DriverManager.getConnection(URL_BASIC, "TEST", "test");
    }

    public static Connection createConnectionByDataSource(String id, String password) throws SQLException
    {
        sunje.goldilocks.jdbc.GoldilocksDataSource sDataSource = new sunje.goldilocks.jdbc.GoldilocksDataSource();
        
        sDataSource.setDatabaseName("test");
        sDataSource.setServerName("192.168.0.120");
        sDataSource.setPortNumber(48900);
        sDataSource.setUser(id);
        sDataSource.setPassword(password);

        return sDataSource.getConnection();
    }
    
    public static void main(String[] args) throws SQLException
    {
        Connection con = createConnectionByDriverManager("TEST", "test");
	
	int b = con.getTransactionIsolation();
	System.out.println("READ_COMMITTED :"+Connection.TRANSACTION_READ_COMMITTED);
	System.out.println("TRANSACTION_READ_UNCOMMITTED :"+Connection.TRANSACTION_READ_UNCOMMITTED);
	System.out.println("TRANSACTION_SERIALIZABLE :"+Connection.TRANSACTION_SERIALIZABLE);

	System.out.println("");
	System.out.println("connection isolation level : "+b);
	DatabaseMetaData dmd = con.getMetaData();

	try
	{
	    int a = dmd.getDefaultTransactionIsolation();
	    System.out.printf("default isolation level : %d\n", a);
	}
	catch(SQLException sException)
	{
	    System.out.println("SException");
	}
        con.close();
    }
}

