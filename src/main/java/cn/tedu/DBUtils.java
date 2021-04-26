package cn.tedu;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DBUtils {
    private static DruidDataSource ds;
    static {
        //通过属性对象读取配置文件数据 替换掉下面写死的内容
        Properties  p = new Properties();
        //获取文件输入流
        InputStream in  = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = p.getProperty("db.driver");
        String url = p.getProperty("db.url");
        String username = p.getProperty("db.username");
        String password = p.getProperty("db.password");
        String maxActive = p.getProperty("db.maxActive");
        String initialSize = p.getProperty("db.initialSize");

//创建数据库连接池
        ds = new DruidDataSource();
        //设置数据库连接信息
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setInitialSize(Integer.parseInt(initialSize)); //设置初始连接数量

        ds.setMaxActive(Integer.parseInt(maxActive));//设置最大连接数量
    }
    public static Connection getConn() throws Exception {
        Connection conn = ds.getConnection();//获取连接 异常抛出
        return conn;
    }
}
