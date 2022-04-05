package saint;

public class Main {

    public static void main(String[] args) {
        Map<Integer, String> map = new Map<>();

//        map.putValue(1, "AAA");
//        map.putValue(2, "BBB");
//        map.putValue(3, "CCC");
//        map.putValue(4, "DDD");
//        map.putValue(5, "EEE");
//        map.putValue(6, "FFF");
//        map.putValue(7, "GGG");
//        map.putValue(8, "HHH");
//
//        System.out.println("---------------");
//        System.out.println("Get by key 1. Expected 'AAA': " + map.getValue(1));
//        System.out.println("Get by key 2. Expected 'BBB': " + map.getValue(2));
//        System.out.println("Get by key 3. Expected 'CCC': " + map.getValue(3));
//        System.out.println("Get by key 100. Expected null: " + map.getValue(100));
//        System.out.println("Get by key 200. Expected null: " + map.getValue(200));
//        System.out.println("Change existed key. Substitute [1 : 'AAA'] to [1 : 'aaa']");
//        map.putValue(1, "aaa");
//        System.out.println("Get by key 1. Expected 'aaa': " + map.getValue(1));
//
//        System.out.println("-------------- ");
//        System.out.println("now map: ");
//        map.print();
//        System.out.println("copy constructor...");
//        Map<Integer, String> other = new Map<>(map);
//        System.out.println("copied map: ");
//        other.print();
//        System.out.println("Maps should be equals. Expected true: " + map.equals(other));
//
//
//        System.out.println("-------------- ");
//        System.out.println("Check isEmpty. Expected false: " + map.isEmpty());
//        System.out.println("clear map...");
//        map.clear();
//        System.out.println("Check isEmpty. Expected true: " + map.isEmpty());

        map.putValue(1, "1");
        map.putValue(2, "2");
        map.putValue(3, "3");
        map.putValue(4, "4");
        map.putValue(5, "5");
        map.putValue(6, "6");

        System.out.println(map.getMaxHeight());
    }
}