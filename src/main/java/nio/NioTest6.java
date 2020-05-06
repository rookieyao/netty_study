package nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author rookie
 * @Date 2020/5/5 16:22
 * @Description
 **/
public class NioTest6 {

    public static void main(String[] args) throws Exception {
        int[] ports = new int[5];
        for (int i = 0; i < ports.length ; i++) {
            ports[i] = 5000+i;
        }
        Selector selector = Selector.open();
        for (int i = 0; i <ports.length ; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocket.bind(address);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口 = " + ports[i]);
        }

        while (true){
            int number = selector.select();
            System.out.println("number = " + number);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys = " + selectionKeys);
            Iterator<SelectionKey> ite = selectionKeys.iterator();
            while (ite.hasNext()){
                SelectionKey selectionKey = ite.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    ite.remove();
                    System.out.println("get Client Connection = " + socketChannel);
                }else if(selectionKey.isReadable()){

                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    int bytesRead = 0;
                    while (true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        System.out.println("read1 = " + read);
                        if(read <=0){
                            System.out.println("read2 = " + read);
                            break;
                        }
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        bytesRead += read;
                    }
                    System.out.println("读取: " + bytesRead+"，来自于: "+socketChannel);
                    ite.remove();
                }
            }
        }
    }
}
