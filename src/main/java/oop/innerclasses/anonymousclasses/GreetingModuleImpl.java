package oop.innerclasses.anonymousclasses;

public class GreetingModuleImpl implements GreetingModule {
    @Override
    public void sayHello() {
        System.out.println("HELLO from greeting module impl");
    }
}
