package uno;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class Publisher {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException, InterruptedException {
        System.out.println("Publisher starts...");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://guest:guest@localhost");
        factory.setConnectionTimeout(300000);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("my-queue", true, false, false, null);

        int count = 0;

        while (true) {
            String message = "Message number " + count;

            channel.basicPublish("", "my-queue", null, message.getBytes());
            count++;
            System.out.println("Published message: " + message);

//            Thread.sleep(500);
        }
    }
}
