package zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author rookie
 * @Date 2020/5/6 22:29
 * @Description
 **/
public class NewIOClient {
    public static void main(String[] args) throws Exception{

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8899));
        socketChannel.configureBlocking(true);
        String fileName = "D:/work/software/gradle-6.2.2-all.zip";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        long endTime = System.currentTimeMillis();
        System.out.println("发送总字节数:"+transferCount+"，耗时:"+(endTime - startTime));
        fileChannel.close();
    }
}
