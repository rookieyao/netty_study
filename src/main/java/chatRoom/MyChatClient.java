package chatRoom;

import clientAndServer.MyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author rookie
 * @Date 2020/3/8 18:28
 * @Description
 **/
public class MyChatClient {

    public static void main(String[] args) throws Exception{

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());
            Channel channel =  bootstrap.connect("localhost",10092).sync().channel();

            //死循环，实时读取键盘输入的内容
            for (;;){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                channel.writeAndFlush(bufferedReader.readLine()+"\r\n");
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
