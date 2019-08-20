package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class ChannelFactory {

    private static ConnectionFactory factory;


    public static Channel get() {
        if (factory == null) {
            factory = new ConnectionFactory();
            try {
                factory.setUri("amqp://guest:guest@localhost");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            factory.setConnectionTimeout(300000);
        }

        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        try {
            return connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
