package pruebaRabbitmq;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
	//set up the class and name the queue
	private final static String QUEUE_NAME = "hello";
	
	public static void main (String[] argv) throws Exception{
		
		//creamos la coneccion al servidor
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");//aqui esta de forma local. IP para conectarnos a otra compu
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		//para enviar declaramos un queue para tener a donde enviar
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!!";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println(" [x] Sent '"+message + "'");
		
		//cerramos la coneccion y el canal
		
		channel.close();
		connection.close();
	}
}
