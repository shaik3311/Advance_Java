package CircularDependencyDemo.simple;

public class B {
    private A a;
    public B(){
        System.out.println("Creating B");
        this.a = new A();
    }
}
