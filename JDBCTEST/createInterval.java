import java.sql.*;
import javax.sql.*;
import sunje.goldilocks.jdbc.*;
import sunje.goldilocks.jdbc.GoldilocksInterval;

public class createInterval
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

        return DriverManager.getConnection(URL_BASIC, "test", "test");
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
        Connection con = createConnectionByDriverManager("test", "test");
	
	GoldilocksInterval gi = GoldilocksInterval.createIntervalYear(2, true, 99);

	System.out.println(gi.getYear());


	con.close();
    }
}

