package drukhary.web.laba3.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCConnectionFactory {

    private static Connection connection=null;

    private static void connectWithDS() {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:/PostgresDS");

            connection = ds.getConnection();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        if (connection==null) {
                connectWithDS();
        }
        return connection;
    }
}
