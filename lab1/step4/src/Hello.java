
package step4.src;
import external.*;

public class Hello{
    public static void main(String args[]){
        Dog dog = new Dog();
        System.out.println("Dog: "+dog.voice());
        System.out.println("2+2 = " + ExternalSummator.sum(2,2));
        System.out.println("Hello World! Finished step 4");
    }
}