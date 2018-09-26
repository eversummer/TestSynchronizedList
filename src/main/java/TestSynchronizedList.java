import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSynchronizedList {
    public static void main(String... args){
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                if (!synchronizedList.contains(i)) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                    }
                }
                synchronizedList.add(i);
            }
        };
        Thread threadOne = new Thread(runnable);
        Thread threadTwo = new Thread(runnable);
        threadOne.start();
        threadTwo.start();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        synchronizedList.forEach(System.out::println);
    }
}
