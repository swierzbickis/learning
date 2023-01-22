package oop.innerclasses;

public class StaticOuterClass {

    public static class StaticInnerClass {

        public void test(){
            System.out.println("test - normal method");
        }

        public static void test2(){
            System.out.println("test2 - static method");
        }

    }

    public StaticInnerClass instantiate() {
        return new StaticInnerClass();
    }

}
