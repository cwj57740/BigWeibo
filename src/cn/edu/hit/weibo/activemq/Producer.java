package cn.edu.hit.weibo.activemq;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName: Producer
 * @Description: 消息队列操作类
 * @author onceWarmth
 * @date 2017年12月9日
 *
 */
public class Producer {
    // ConnectionFactory ：连接工厂，JMS 用它创建连接
    private static ConnectionFactory connectionFactory;

     /**
     * @Title: sendMessage
     * @Description: 向消息队列发送消息
     * @param @param string string    参数
     * @return void    返回类型
     */
    public static void sendMessage(String message, String queueName) {
        /**
        * Producer.sendMessage("ActiveMq send message", "First Queue");
        * */
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // MessageProducer：消息发送者
        MessageProducer producer;

        while (connectionFactory == null) {
            connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD,
                    "tcp://localhost:61616"
            );
        }

        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取session
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(queueName);
            // 得到消息生成者【发送者】
            producer = session.createProducer(destination);
            // 设置不持久化
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // System.out.println(message);
            // 构造消息
            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Throwable ignore) {

            }
        }
    }
}
