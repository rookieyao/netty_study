package nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;
import java.security.Security;

/**
 * @Author rookie
 * @Date 2020/4/19 21:52
 * @Description
 **/
public class NioTest1 {
    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i=0; i<intBuffer.capacity(); i++){
            int num = new SecureRandom().nextInt(20);
            intBuffer.put(num);
        }
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
