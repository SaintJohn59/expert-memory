package saint;

public class Pair<Key, Value>{
    private Key key;                    // ключ пары
    private Value value;                // значение пары
    private Pair<Key, Value> next;      // ссылка на следующий элемент

    // конструктор с параметрами: ключ, значение
    public Pair(Key key, Value value){
        this(key, value, null);
    }
    // конструктор класса с параметрами: ключ, значение, ссылка на следующий
    public Pair(Key key, Value value, Pair<Key, Value> next){
        this.key = key;
        this.value = value;
        this.next = next;
    }

    // предоставляет ссылку на следующий элемент
    public Pair<Key, Value> getNext() { return next; }
    // устанавливает ссылку на следующий элемент
    public void setNext(Pair<Key, Value> next) { this.next = next; }
    // предоставляет ключ пары
    public Key getKey() { return key; }
    // предоставляет значение пары
    public Value getValue() { return value; }
    // устанавливиает значение пары
    public void setValue(Value value) { this.value = value; }
    // переодпреление метода toString
    public String toString() { return "key: " + key + " ---> " + "value: " + value; }
}