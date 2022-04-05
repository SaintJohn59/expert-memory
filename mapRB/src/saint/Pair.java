package saint;

import java.util.Objects;

// Структура данных "Пара"
class Pair <V1 extends Comparable<V1>, V2> implements Comparable<Pair<V1, V2>>{
    V1 value1;
    V2 value2;

    public Pair (V1 first, V2 second) {
        this.value1 = first;
        this.value2 = second;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) other;

        return Objects.equals(value1, pair.value1);
    }

    @Override
    public int hashCode() {
        return value1 != null ? value1.hashCode() : 0;
    }

    @Override
    public int compareTo(Pair<V1, V2> other) {
        return this.value1.compareTo(other.value1);
    }

    @Override
    public String toString() {
        return "[" + value1 + ", " + value2 + "]";
    }
}