package oop.innerclasses.anonymousclasses;

//http://www.samouczekprogramisty.pl/klasy-wewnetrzne-i-anonimowe-w-jezyku-java/
public class GreetingModuleTest {

    public void doSomething() {
        GreetingModule greetingModule = new GreetingModule() {
            @Override
            public void sayHello() {
                System.out.println("HELLO");
            }
        }; //tworzenie anonimowej klasy

        GreetingModule greetingModuleAsLambda = () -> { //dajemy od razu nawiasy klamrowe bo tworzymy od razu cialo metody
            System.out.println("HELLO as Lambda");
        }; //tworzenie anonimowej klasy jako lambda

        GreetingModule greetingModuleImpl = new GreetingModuleImpl();

        greetingModule.sayHello();
        greetingModuleAsLambda.sayHello();
        greetingModuleImpl.sayHello();
    }

}
