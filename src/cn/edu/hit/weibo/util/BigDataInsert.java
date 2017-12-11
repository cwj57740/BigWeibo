package cn.edu.hit.weibo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.sql.PreparedStatement;

public class BigDataInsert {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final String url = "jdbc:mysql://127.0.0.1:8066/TESTDB";
        final String name = "com.mysql.jdbc.Driver";
        final String user = "root";
        final String password = "root";
        Connection conn = null;
        Class.forName(name);//指定连接类型
        conn = DriverManager.getConnection(url, user, password);//获取连接
        if (conn!=null) {
            System.out.println("获取连接成功");
            insert(conn);
        }else {
            System.out.println("获取连接失败");
        }

    }
    public static void insert(Connection conn) {
        // 开始时间
        Long begin = new Date().getTime();
        // sql前缀
        String userPrefix = "INSERT INTO user (username, password, hits) VALUES ";
        String blogPrefix = "INSERT INTO blog (bid, uid, title, text, views, datetime) VALUES ";
        String logPrefix = "INSERT INTO log (lid, bid,message) VALUES ";
        Random rand = new Random();
        try {
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement  userPst = conn.prepareStatement("");//准备执行语句
            PreparedStatement  blogPst = conn.prepareStatement("");
            PreparedStatement  logPst = conn.prepareStatement("");
            int t=0;
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 10; i++) {

                StringBuffer userSql = new StringBuffer();
                userSql.append(userPrefix+"('"+"name"+i+"',"+"'123', '");
                int hits = 0;
                // 第j次提交步长
                for (int j = 1; j <= 100000; j++) {
                    t++;
                    StringBuffer blogSql = new StringBuffer();
                    StringBuffer logSql = new StringBuffer();
                    int views = rand.nextInt(100000);
                    Date date=new Date();     //获取一个Date对象
                    DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
                    String punchTime = simpleDateFormat.format(date);   //格式化后的时间
                    // 构建SQL
                    blogSql.append(blogPrefix+"('"+t+"','" + i +"','"+ UUID.randomUUID()+"','"+UUID.randomUUID()+"','"+views +"','"+punchTime+"');");
                    hits+=views;
                    blogPst.addBatch(blogSql.toString());
                    logSql.append(logPrefix+"('"+t+"','" + i*j+"','"+"新增微博');");
                    logPst.addBatch(logSql.toString());
                    System.out.println(j);
                }
                userSql.append(hits+"' );");
                // 构建完整SQL
                userPst.addBatch(userSql.toString());
                // 执行操作
                blogPst.executeBatch();
                userPst.executeBatch();
                logPst.executeBatch();
                // 提交事务
                conn.commit();
                System.out.println("--插入次数--"+i+"---");
            }
            // 头等连接
            blogPst.close();
            logPst.close();
            userPst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println("数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
    }
}
