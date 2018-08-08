import java.sql.*;
import javax.sql.*;

public class getString
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
	String createsql = "CREATE TABLE gettest(c1 varchar(20))";
        Connection con = createConnectionByDriverManager("TEST", "test");

	Statement stmt = con.createStatement();

	System.out.println("Create table. ("+createsql+")");
	stmt.execute(createsql);

	stmt.execute("INSERT INTO gettest VALUES('Getstring test')");
	
	ResultSet rs = stmt.executeQuery("SELECT * FROM gettest");

	while(rs.next()){
	
        String text = rs.getString(1);

	System.out.println("Column value : "+text);

	}

	stmt.execute("DROP TABLE gettest");
	System.out.println("Drop table.");

	rs.close();
	stmt.close();
	con.close();
	

    }
}

