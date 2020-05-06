package zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

/**
 * @Author rookie
 * @Date 2020/5/6 22:22
 * @Description
 **/
public class OldClient {

    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost", 8899);
        String fileName = "D:/work/software/gradle-6.2.2-all.zip";
        FileInputStream inputStream = new FileInputStream(fileName);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] buffer = new byte[4096];
        long readCount;
        long total = 0;
        long startTime = System.currentTimeMillis();
        while ((readCount = inputStream.read(buffer))>=0){
            total += readCount;
            dataOutputStream.write(buffer);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("发送总字节数:"+total+"，耗时:"+(endTime-startTime));
        dataOutputStream.close();;
        socket.close();
        inputStream.close();

    }
}
