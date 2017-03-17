package example;

import com.generallycloud.nio.acceptor.SocketChannelAcceptor;
import com.generallycloud.nio.codec.http11.ServerHTTPProtocolFactory;
import com.generallycloud.nio.component.IoEventHandleAdaptor;
import com.generallycloud.nio.component.NioSocketChannelContext;
import com.generallycloud.nio.component.SocketChannelContext;
import com.generallycloud.nio.component.SocketSession;
import com.generallycloud.nio.configuration.ServerConfiguration;
import com.generallycloud.nio.protocol.ReadFuture;

public class Server  {
	
	public static void main(String[] args) throws Exception {
		
		IoEventHandleAdaptor eventHandleAdaptor = new IoEventHandleAdaptor() {
			@Override
			public void accept(SocketSession session, ReadFuture future) throws Exception {
				future.write("Hello World!");
				session.flush(future);
			}
		};
		
		ServerConfiguration c = new ServerConfiguration(8080);
		
		c.setSERVER_MEMORY_POOL_UNIT(256);
		c.setSERVER_ENABLE_MEMORY_POOL_DIRECT(true);
		c.setSERVER_CORE_SIZE(2);
		c.setSERVER_ENABLE_MEMORY_POOL(true);
		c.setSERVER_MEMORY_POOL_CAPACITY_RATE(2);

		SocketChannelContext context = new NioSocketChannelContext(c);

		SocketChannelAcceptor acceptor = new SocketChannelAcceptor(context);
		
		context.setIoEventHandleAdaptor(eventHandleAdaptor);
		context.setProtocolFactory(new ServerHTTPProtocolFactory());
		
		acceptor.bind();
		
	}
	
}
