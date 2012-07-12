package jms.sample;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JmsSample {

	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL,"tcp://10.229.12.153:62616");
		javax.naming.Context ctx = new InitialContext(props);
		
		ConnectionFactory connectionFactory = null;
		connectionFactory = (ConnectionFactory)ctx.lookup("ConnectionFactory");

	}

}
