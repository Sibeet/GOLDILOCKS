import java.sql.*;
import javax.sql.*;

public class getPrecision
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
	String createsql = "CREATE TABLE precisiontest(c1 number(10), c2 varchar(20))";
        Connection con = createConnectionByDriverManager("test", "test");
	
	Statement stmt = con.createStatement();
	
	System.out.println("Create table : "+createsql);
	stmt.execute(createsql);
	
	System.out.println();
	ResultSet rs = stmt.executeQuery("SELECT * FROM precisiontest");

	ResultSetMetaData md = rs.getMetaData();
	
	System.out.println("column 1 precision : "+md.getPrecision(1));
	System.out.println("column 2 precision : "+md.getPrecision(2));

	stmt.execute("DROP TABLE precisiontest");
	System.out.println("Drop table.");

	con.close();
    }
}

