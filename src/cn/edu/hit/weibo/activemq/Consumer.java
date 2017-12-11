package cn.edu.hit.weibo.activemq;
import cn.edu.hit.weibo.dao.LogDao;
import cn.edu.hit.weibo.model.Log;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {

    private static final String QUEUE_NAME = "First Queue";
    private static LogDao logDao = new LogDao();

    public static void main(String[] args) {
        receiveMessage();
    }

    /**
     * @Title: sendMessage
     * @Description: 操作数据
     * @param @param String   参数
     * @return void    返回类型
     */
    private static void operation(String message) {
        Log log = new Log();
        log.setBid(Integer.parseInt(message.split("\\.")[0]));
        log.setMessage(message.split("\\.")[1]);
        logDao.addLog(log);
    }

    /**
     * @Title: sendMessage
     * @Description: 从消息队列接收消息
     * @param @param    参数
     * @return void    返回类型
     */
    private static void receiveMessage() {
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // MessageProducer：消息发送者
        MessageConsumer consumer;
        // 构造ConnectionFactory实例对象
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );
        try {
            // 构造从工厂得到的了解对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取session
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            // 获取操作连接
            destination = session.createQueue(QUEUE_NAME);
            // 得到消息生成者【发送者】
            consumer = session.createConsumer(destination);
            while (true) {
                System.out.println("----等待日志消息----");
                //设置接收者接收消息的时间
                TextMessage message = (TextMessage) consumer.receive(100000);
                if (null != message) {
                    System.out.println("收到消息 " + message.getText());
                    operation(message.getText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }
}
