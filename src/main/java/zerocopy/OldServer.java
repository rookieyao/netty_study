package zerocopy;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author rookie
 * @Date 2020/5/6 22:40
 * @Description
 **/
public class OldServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true){
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] bytes = new byte[4096];
                while (true){
                    int readCount = dataInputStream.read(bytes,0,bytes.length);
                    if(-1 == readCount){
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
