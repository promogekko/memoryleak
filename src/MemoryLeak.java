import java.util.HashMap;
import java.util.Map;

/**
 * Created by hme on 10/06/16.
 */


public class MemoryLeak
{

        public static void main(String[] args) {
            Map<Key, String> map = new HashMap<Key, String>(1000);
            int counter = 0;
            while (true) {
                // creates duplicate objects due to bad Key class
                map.put(new Key("dummyKey"), "value");
                counter++;
                if (counter % 1000 == 0) {
                    System.out.println("Map size: " + map.size());
                    System.out.println("Free memory after count " + counter
                            + " is " + getFreeMemory() + "MB");
                    sleep(1000);
                }
            }
        }
        // inner class key without hashcode() or equals()
        // bad implementation causes memory leak
        static class Key {
            private String key;
            public Key(String key) {
                this.key = key;
            }
        }

        public static void sleep(long sleepFor) {
            try {
                Thread.sleep(sleepFor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static long getFreeMemory() {
            return Runtime.getRuntime().freeMemory() / (1024 * 1024);
        }
}

