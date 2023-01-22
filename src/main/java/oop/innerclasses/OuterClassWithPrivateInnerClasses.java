package oop.innerclasses;

public class OuterClassWithPrivateInnerClasses {

     private class InnerClass {

        private class InnerClass2 {

        }

    }

    public InnerClass instantiate() {
        return new InnerClass();
    }


    public void testPrivateMethod() {
        InnerClass innerClass = new InnerClass();
        InnerClass.InnerClass2 innerClass22 = innerClass.new InnerClass2();

        System.out.println(innerClass.getClass().getName());
        System.out.println(innerClass22.getClass().getName());

    }

}
