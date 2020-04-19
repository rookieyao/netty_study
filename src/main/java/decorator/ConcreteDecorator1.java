package decorator;

/**
 * @Author rookie
 * @Date 2020/4/15 9:00
 * @Description
 **/
public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }

    public void doAnotherThing(){
        System.out.printf("功能B");
    }
}
