package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Author rookie
 * @Date 2020/5/5 9:48
 * @Description
 * ---数据发散
 *      Scattering:在将数据写入到buffer中时， ，依次写入，一个buffer满了就写下一个。
 * ---数据聚合
 *      Gatering：在将数据读出到buffer中时，可以采用buffer数组，依次读入，一个buffer满了就读下一个。
 **/
public class NioTest5 {

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(inetSocketAddress);
        int messageLength=9;
        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0]= ByteBuffer.allocate(2);
        buffers[1]= ByteBuffer.allocate(3);
        buffers[2]= ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();
        while (true){
            int bytesRead = 0;
            while (bytesRead <messageLength){
                long r = socketChannel.read(buffers);
                if(r !=-1){
                    bytesRead += r;
                    System.out.println("bytesRead"+bytesRead);
                    Arrays.asList(buffers).stream().map(buffer -> "position: "+buffer.position()+",limit: "+buffer.limit()).
                            forEach(System.out::println);
                }else{
                    System.out.println("数据读取有误!");
                    break;
                }
            }
            Arrays.asList(buffers).forEach(buffer -> {
                buffer.flip();
            });
            long byteWritten = 0;
            while(byteWritten < messageLength){
                long r = socketChannel.write(buffers);
                byteWritten += r;
            }
            Arrays.asList(buffers).forEach(buffer -> {
                buffer.clear();
            });

            System.out.println("bytesRead: " + bytesRead + ", bytesWritten: "+ byteWritten + ", messageLength: "+messageLength);

        }
    }
}
