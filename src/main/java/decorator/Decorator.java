package decorator;

/**
 * @Author rookie
 * @Date 2020/4/15 8:57
 * @Description
 **/
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
