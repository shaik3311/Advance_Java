package CircularDependencyDemo.simple;

public class A {

    private B b;
    public A(){
        System.out.println("Creating A");
        this.b = new B();
    }
}
