//getUpdateCount.java
//해당 쿼리문의 Update, Insert 여부와 갱신된 Row 수를 반환. 그 외 쿼리는 -1로 표시된다.

import java.sql.*;
import javax.sql.*;

public class getUpdateCount
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
        Connection con = createConnectionByDriverManager("test", "test");
	
	Statement stmt = con.createStatement();

	boolean rs = stmt.execute("SELECT * FROM T1");

	int i = stmt.getUpdateCount();
	System.out.println("Select Count : "+i);


	rs = stmt.execute("create table t2(c1 int,c2 int)");

	i = stmt.getUpdateCount();
	System.out.println("create Count : "+i);


	rs = stmt.execute("insert into t2 values(1,1)");
	rs = stmt.execute("insert into t2 values(1,1)");

	i = stmt.getUpdateCount();
	System.out.println("insert Count : "+i);
	rs = stmt.execute("update t2 set c2=1 where c1=1");

	i = stmt.getUpdateCount();
	System.out.println("update Count : "+i);

	rs = stmt.execute("drop table t2");

	i = stmt.getUpdateCount();
	System.out.println("drop Count : "+i);

	stmt.close();
	con.close();
    }
}

