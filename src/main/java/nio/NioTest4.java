package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author rookie
 * @Date 2020/4/22 5:05
 * @Description
 **/
public class NioTest4 {

    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        while (true){
            byteBuffer.clear();

            byteBuffer.slice();
//            /**
//             * @描述
//             * read() :  从输入流中读取数据的下一个字节，返回0到255范围内的int字节值。
//             *          如果因为已经到达流末尾而没有可用的字节，则返回-1。
//             * read(byte[] b) :  从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中
//             *          以整数形式返回实际读取的字节数,如果 b 的长度为 0，则不读取任何字节并返回 0；
//             *          否则，尝试读取至少一个字节。如果因为流位于文件末尾而没有可用的字节，则返回值 -1；
//             *          否则，至少读取一个字节并将其存储在 b 中。
//             */
            int read = inputChannel.read(byteBuffer);

            System.out.println("read:"+read);
            if(-1 == read){
                break;
            }

            byteBuffer.flip();
            outputChannel.write(byteBuffer);
        }

        inputChannel.close();
        outputChannel.close();
    }
}
