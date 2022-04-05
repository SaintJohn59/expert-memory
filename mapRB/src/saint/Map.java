package saint;

import java.util.Objects;

public class Map<K extends Comparable<K>, V> {
    Tree<Pair<K, V>> tree;

    public Map() {
        tree = new Tree<>();
    }

    public Map(Map<K, V> other) {
        tree = new Tree<>(other.tree);
    }

    public void putValue(K key, V value) {
        Objects.requireNonNull(key);
        tree.add(new Pair<>(key, value));
    }

    public void clear() {
        tree.clear();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public V getValue(K key) {
         Pair<K, V> temp = tree.isContain(new Pair<>(key, null));
         if (temp != null) {
             return temp.value2;
         } else {
             return null;
         }
    }

    public void print() {
        tree.print();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Map<?, ?> map = (Map<?, ?>) o;
        return tree.equals(map.tree);
    }

    @Override
    public int hashCode() {
        return tree != null ? tree.hashCode() : 0;
    }


    public int getMaxHeight() {
        return tree.getMaxHeight();
    }

    public int getMinHeight() {
        return tree.getMinHeight();
    }
}
