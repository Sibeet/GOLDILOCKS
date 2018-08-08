//setOdject.java
//Prepare Statement의 수행을 위해 값을 바인딩한다.

import java.sql.*;
import javax.sql.*;
import java.math.*;

public class setObject
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

        return DriverManager.getConnection(URL_BASIC, "TEST", "test");
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
	String createsql = "CREATE TABLE settest(C1 int)";
	BigInteger bint = new BigInteger("1000000");
        Connection con = createConnectionByDriverManager("TEST", "test");
	
	Statement stmt = con.createStatement();

	System.out.println("Create table : "+createsql);
	stmt.execute(createsql);

	stmt.close();
	
	PreparedStatement psmt = con.prepareStatement("insert into settest values(?)");
	psmt.setObject(1, bint);
	 
	boolean rs = psmt.execute();

	con.close();
	

    }
}

