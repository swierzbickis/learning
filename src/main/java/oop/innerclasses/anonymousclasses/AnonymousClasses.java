package oop.innerclasses.anonymousclasses;

public class AnonymousClasses {

    public static class Robot {
        private final HelpingModule helpingModule;

        public Robot(HelpingModule helpingModule) {
            this.helpingModule = helpingModule;
        }

        public void saySomething() {
            helpingModule.sayHello();
        }


    }

    public interface HelpingModule {
        void sayHello();
    }


}
