package saint;

import java.util.NoSuchElementException;
public class hashMap<Key extends Comparable<? super Key>, Value> {

    private static final double DEFAULT_LOAD_FACTOR = 2.0;           // максимальный уровень загруженности
    private static final int DEFAULT_CAPACITY = 10;
    private int numOfElements;                                       // число элементов
    Pair<Key, Value>[] table;                                            // список элементов
    private double loudFactor;                                       // уровень загруженности, если нужно изменить DEFAULT_LOAD_FACTOR

    @SuppressWarnings("unchecked")
    public hashMap() {
        this.loudFactor = DEFAULT_LOAD_FACTOR;
        this.numOfElements = 0;
        this.table = new Pair[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public hashMap(int capacity) {
        this.table = new Pair[capacity];
        this.loudFactor = DEFAULT_LOAD_FACTOR;
        this.numOfElements = 0;
    }

    public int getNumOfElements() {
        return numOfElements;
    }       // возвращает число элементов

    public double getLoudFactor() {
        return loudFactor;
    }          // возвращает уровень загруженности


    // удаление всех элементов
    @SuppressWarnings("unchecked")
    public void deleteAllElements() {
        table = new Pair[DEFAULT_CAPACITY];
        numOfElements = 0;
    }

    // добавление пары ключ-значение
    public Value addPair(Key key, Value value) {
        if (key == null || value == null) throw new IllegalArgumentException("key and value must not be null");

        int index = key.hashCode() % table.length;
        if (index < 0) index += table.length;
        if (table[index] == null) table[index] = new Pair<>(key, value, null);
        else {
            Pair<Key, Value> temp = table[index];
            while (temp.getNext() != null && temp.getKey().equals(key)) {
                temp.getNext();
            }
            if (temp.getKey().equals(key)) {
                Value oldValue = temp.getValue();
                temp.setValue(value);
                return oldValue;
            } else {
                temp.setNext(new Pair<>(key, value));
            }
        }
        numOfElements++;
        if (getPresentLoudFactor() > loudFactor) {
            resize(table.length * 2 + 1);
        }
        return null;
    }

    // получение элемента по ключу
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Key can't be null");
        int index = key.hashCode() % table.length;
        if (index < 0) index += table.length;
        if (table[index] == null) return null;
        Pair<Key, Value> temp = table[index];
        while (temp != null && temp.getKey().equals(key)) {
            temp = temp.getNext();
        }
        if (temp != null) return temp.getValue();
        // если значение по ключу не найдено
        return null;
    }

    // удаление пары по ключу
    public Value deleteElement(Key key) {
        if (key == null) throw new IllegalArgumentException("Key can't be null");
        int index = key.hashCode() % table.length;
        if (index < 0) index += table.length;
        if (table[index] == null) return null;
        if (table[index] != null) {
            Pair<Key, Value> previous = null;
            Pair<Key, Value> temp = table[index];
            while (temp.getNext() != null && !temp.getKey().equals(key)) {
                previous = temp;
                temp = temp.getNext();
            }
            if (temp.getKey().equals(key)) {
                Value val = temp.getValue();
                if (previous == null) {
                    table[index] = temp.getNext();
                } else {
                    previous.setNext(temp.getNext());
                }
                numOfElements--;
                return val;
            }
        }
        throw new NoSuchElementException("Key doesn't exist in table");
    }

    // изменение уровня загруженности
    public void changeLoudFactor(double newLoudFactor) {
        this.loudFactor = newLoudFactor;
        if (getPresentLoudFactor() > loudFactor) {
            resize(2 * table.length + 1);
        }
    }

    // получение текущего уровня загруженности
    public double getPresentLoudFactor() {
        return (double) numOfElements / this.table.length;
    }

    // перехэширование
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        Pair<Key, Value>[] newTable = new Pair[newSize];
        for (Pair<Key, Value> temp : table) {
            if (temp != null) {
                int index = Math.abs(temp.getKey().hashCode() % table.length);
                newTable[index] = temp;
            }
        }
        table = newTable;
    }

    public void printHashMap() {
        System.out.println("HashMap: ");
        for (int i = 0; i < table.length; i++) {
            Pair<Key, Value> temp = table[i];
            if (temp == null) continue;
            System.out.print("Index: " + i + " ");
            while (temp != null) {
                System.out.print(temp.toString() + "   ");
                temp = temp.getNext();
            }
            System.out.println();
        }
        System.out.println();
    }
}