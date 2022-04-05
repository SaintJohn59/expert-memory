package saint;
public class testHash {
    public static void main(String[] args) {
        hashMap<Integer, Integer> map = new hashMap<Integer, Integer>();
        for (int i = 0; i < 50; i++) {
            map.addPair(i, i);
        }
        System.out.println("Вывод HashMap после добавления 50 элементов");
        map.printHashMap();
        System.out.println("Текущий уровень загруженности: " + map.getPresentLoudFactor()
                            + "\nМаксимальный уровень загруженности: " + map.getLoudFactor()
                            + "\nКоличество элементов: " + map.getNumOfElements() + "\n");
        for (int i = 0; i < 10; i++){
            map.deleteElement(i);
        }
        System.out.println("Вывод HashMap после удаления 10 элементов");
        map.printHashMap();
        System.out.println("Текущий уровень загруженности: " + map.getPresentLoudFactor()
                             + "\nМаксимальный уровень загруженности: " + map.getLoudFactor()
                             + "\nКоличество элементов: " + map.getNumOfElements() + "\n");
        map.changeLoudFactor(0.7);
        System.out.println("HashMap с измененным уровнем загруженности");
        map.printHashMap();
        System.out.println("Текущий уровень загруженности: " + map.getPresentLoudFactor()
                            + " \nМаксимальный уровень загруженности: " + map.getLoudFactor());
        map.deleteAllElements();
        System.out.println("Вывод HashMap после удаления всех элементов");
        map.printHashMap();
        System.out.println("Текущий уровень загруженности: " + map.getPresentLoudFactor()
                             + "\nМаксимальный уровень загруженности: " + map.getLoudFactor()
                             + "\nКоличество элементов: " + map.getNumOfElements() + "\n");
    }
}
