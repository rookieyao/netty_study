package decorator;

/**
 * @Author rookie
 * @Date 2020/4/15 8:54
 * @Description
 **/
public class ConcreteComponent implements Component {
    @Override
    public void doSomething() {

        System.out.printf("功能A!");
    }
}
