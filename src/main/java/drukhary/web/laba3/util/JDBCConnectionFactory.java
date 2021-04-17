package drukhary.web.laba3.util;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCConnectionFactory {
    private JDBCConnectionFactory() throws NamingException{
            dataSource = (DataSource)(new InitialContext()).lookup("java:/PostgresDS");
    }

    private static JDBCConnectionFactory instance;
    @Resource(lookup = "java:/PostgresDS")
    private static DataSource dataSource;

    public static JDBCConnectionFactory getInstance() throws NamingException {
        if (instance == null) {
            instance = new JDBCConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}
