import java.sql.*;
import javax.sql.*;

import sunje.goldilocks.jdbc.GoldilocksDataSource;

public class setRoleNametest
{
    protected static final String GOLDILOCKS_DRIVER_CLASS = "sunje.goldilocks.jdbc.GoldilocksDriver";
    protected static final String URL_BASIC = "jdbc:goldilocks://127.0.0.1:22581/test";
    protected static final String URL_NAMED = "jdbc:goldilocks://127.0.0.1:22581/test?program=MySample";
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

        return DriverManager.getConnection(URL_BASIC, "sys", "gliese");
    }

    public static Connection createConnectionByDataSource(String id, String password) throws Exception
    {
        Class.forName(GOLDILOCKS_DRIVER_CLASS);

        sunje.goldilocks.jdbc.GoldilocksDataSource sDataSource = new sunje.goldilocks.jdbc.GoldilocksDataSource();
        //GoldilocksDataSource sDataSource = new GoldilocksDataSource();
        
        sDataSource.setDatabaseName("test");
        sDataSource.setServerName("127.0.0.1");
        sDataSource.setPortNumber(22581);
	sDataSource.setRoleName("SYSDBA");
        sDataSource.setUser(id);
        sDataSource.setPassword(password);


        return sDataSource.getConnection();
    }
    
    public static void main(String[] args) throws Exception
    {
	Connection con = createConnectionByDataSource("sys", "gliese");

	System.out.println("Success connection");

        Statement stmt = con.createStatement();
    
        stmt.close();
        con.close();
    }
}

