package decorator;

/**
 * @Author rookie
 * @Date 2020/4/15 9:03
 * @Description
 **/
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));
        component.doSomething();
    }
}
