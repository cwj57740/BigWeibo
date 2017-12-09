package cn.edu.hit.weibo.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * The type Jdbc utils.
 */
public class JdbcUtils {
    // 配置文件的默认配置！要求你必须给出c3p0-config.xml！！！
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    /**
     * 使用连接池返回一个连接对象
     *
     * @return connection connection
     * @throws SQLException the sql exception
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 返回连接池对象！
     *
     * @return data source
     */
    public static DataSource getDataSource() {
        return dataSource;
    }
}
