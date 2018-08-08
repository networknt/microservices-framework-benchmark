package example;

import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.List;

import com.generallycloud.baseio.buffer.ByteBuf;
import com.generallycloud.baseio.codec.http11.ServerHttpCodec;
import com.generallycloud.baseio.component.ChannelAcceptor;
import com.generallycloud.baseio.component.ChannelContext;
import com.generallycloud.baseio.component.ChannelEventListenerAdapter;
import com.generallycloud.baseio.component.IoEventHandle;
import com.generallycloud.baseio.component.LoggerChannelOpenListener;
import com.generallycloud.baseio.component.NioEventLoopGroup;
import com.generallycloud.baseio.component.NioSocketChannel;
import com.generallycloud.baseio.protocol.Future;

public class Server {

    public static void main(String[] args) throws Exception {

        NioEventLoopGroup group = new NioEventLoopGroup();
        group.setMemoryPoolCapacity(1024 * 64);
        group.setMemoryPoolUnit(512);
        ChannelContext context = new ChannelContext(8087);
        ChannelAcceptor acceptor = new ChannelAcceptor(context, group);
        context.setProtocolCodec(new ServerHttpCodec(1024 * 4));
        context.setMaxWriteBacklog(Integer.MAX_VALUE);
        context.addChannelEventListener(new LoggerChannelOpenListener());
        context.addChannelEventListener(new ChannelEventListenerAdapter() {

            @Override
            public void channelOpened(NioSocketChannel channel) throws Exception {
                channel.setOption(StandardSocketOptions.TCP_NODELAY, true);
                channel.setIoEventHandle(new IoEventHandle() {
                    boolean       addTask = true;
                    List<ByteBuf> fs      = new ArrayList<>(256);

                    @Override
                    public void accept(NioSocketChannel channel, Future future) throws Exception {
                        future.write("Hello World", channel);
                        fs.add(channel.encode(future));
                        if (addTask) {
                            addTask = false;
                            channel.getEventLoop().dispatchAfterLoop(() -> {
                                channel.flush(fs);
                                addTask = true;
                                fs.clear();
                            });
                        }
                    }
                });
            }
        });

        acceptor.bind();
    }

}
