package saint;

class Tree<T extends Comparable<T>> {
    private Node<T> root = null;
    public int size;
    public Tree() {}

    // Конструктор копирования
    public Tree(Tree<T> other) {
        if (other.root == null) {
            root = null;
        } else {
            root = new Node<>(other.root.data, Node.Color.BLACK);
            if (other.root.leftChild != null) {
                root.leftChild = new Node<>(other.root.leftChild, other.root.leftChild.color);
            }
            if (other.root.rightChild != null) {
                root.rightChild = new Node<>(other.root.rightChild, other.root.rightChild.color);
            }
        }
    }

    // Очистка дерева
    public void clear() {
        root = null;
        size = 0;
    }

    // Проверка на пустоту
    public boolean isEmpty() {
        return root == null;
    }

    // Добавление нового узла (через значение)
    public void add(T value) {
        add(new Node<>(value, Node.Color.RED));
    }

    // Добавление нового узла
    private void add(Node<T> insertionNode) {
        if (root == null) {
            root = new Node<>(insertionNode.data, Node.Color.BLACK);
        }

        Node<T> cur = root;
        while (true) {
            if (insertionNode.data.compareTo(cur.data) < 0) {
                if (cur.leftChild == null) {
                    insertionNode.parent = cur;
                    cur.leftChild = insertionNode;
                    rebalance(cur.leftChild);
                    size++;
                    break;
                } else {
                    cur = cur.leftChild;
                    continue;
                }
            }

            if (insertionNode.data.compareTo(cur.data) > 0) {
                if (cur.rightChild == null) {
                    insertionNode.parent = cur;
                    cur.rightChild = insertionNode;
                    rebalance(cur.rightChild);
                    size++;
                    break;
                } else {
                    cur = cur.rightChild;
                }

            } else { // Содержимое одинаковое
                cur.data = insertionNode.data;
                return;
            }
        }
    }

    // Проверка на содержание значения
    public T isContain(T value) {
        Node<T> cur = root;

        while (cur != null) {
            if (cur.data.equals(value)) {
                return cur.data;
            }

            if (value.compareTo(cur.data) < 0) {
                cur = cur.leftChild;
            } else  {
                cur = cur.rightChild;
            }
        }

        return null;
    }

    // Ребалансировка
    private void rebalance(Node<T> node) {
        // Случай #1: Новый элемент - корень => Новый элемент должен быть черным
        if (node == root) {
            node.color = (Node.Color.BLACK);
            return;
        }

        // Родитель черный => балансировка не требуется
        Node<T> parent = node.parent;
        if (parent.color == Node.Color.BLACK) {
            return;
        }

        // Случай #2: Родитель нового элемента - корень => Родитель должен быть черным
        Node<T> grandparent = getGrandparent(node);
        if (grandparent == null) {
            parent.color = (Node.Color.BLACK);
            return;
        }

        // Случай #3: Дядя (node.parent.parent.otherChild) и родитель - красные => Изменить цвет родителя, дедушки (node.parent.parent) и дяди (node.parent.parent.otherChild)
        Node<T> uncle = getUncle(node);
        if (uncle != null && uncle.color == Node.Color.RED) {
            parent.color = (Node.Color.BLACK);
            grandparent.color = (Node.Color.RED);
            uncle.color = (Node.Color.BLACK);

            rebalance(grandparent);
        } else if (parent == grandparent.leftChild) { // Родитель левый потомок от дедушки
            // Случай #4: Дядя черный и новый элемент является левым-правым потомком от дедушки.
            if (node == parent.rightChild) {
                leftRotate(parent);
                parent = node;
            }

            // Случай #5: Дядя черный и новый элемент является левым-левым потомком от дедушки
            rightRotate(grandparent);
            parent.color = (Node.Color.BLACK);
            grandparent.color = (Node.Color.RED);
        } else { // Родитель правый потомок от дедушки
            // Случай #4: Дядя черный и новый элемент является правым-левым потомком от дедушки
            if (node == parent.leftChild) {
                rightRotate(parent);
                parent = node;
            }

            // Случай #5: Дядя черный и новый элемент правый-правый потомок от дедушки
            leftRotate(grandparent);
            parent.color = (Node.Color.BLACK);
            grandparent.color = (Node.Color.RED);
        }
    }

    // Левосторонний поворот узлов
    private void leftRotate(Node<T> node) {
        Node<T> parent = node.parent;
        Node<T> rightChild = node.rightChild;
        node.rightChild = rightChild.leftChild;
        if (rightChild.leftChild != null) {
            rightChild.leftChild.parent = node;
        }
        rightChild.leftChild = node;
        node.parent = rightChild;

        if (parent == null) {
            root = rightChild;
        } else if (parent.leftChild == node) {
            parent.leftChild = rightChild;
        } else if (parent.rightChild == node) {
            parent.rightChild = rightChild;
        } else {
            return;
        }

        if (rightChild != null) {
            rightChild.parent = parent;
        }
    }

    // Правосторонний поворот узлов
    private void rightRotate(Node<T> node) {
        Node<T> parent = node.parent;
        Node<T> leftChild = node.leftChild;
        node.leftChild = leftChild.rightChild;
        if (leftChild.rightChild != null) {
            leftChild.rightChild.parent = node;
        }
        leftChild.rightChild = node;
        node.parent = leftChild;

        if (parent == null) {
            root = leftChild;
        } else if (parent.leftChild == node) {
            parent.leftChild = leftChild;
        } else if (parent.rightChild == node) {
            parent.rightChild = leftChild;
        } else {
            return;
        }

        if (leftChild != null) {
            leftChild.parent = parent;
        }
    }

    private Node<T> getGrandparent(Node<T> node) {
        if (node.parent == null) {
            return null;
        }
        return node.parent.parent;
    }

    private Node<T> getUncle(Node<T> node) {
        Node<T> grandparent = node.parent.parent;
        if (grandparent.leftChild == node.parent) {
            return grandparent.rightChild;
        } else if (grandparent.rightChild == node.parent) {
            return grandparent.leftChild;
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;


        return Node.compareNodes(this.root, ((Tree<?>) other).root);
    }

    // Напечатать дерево
    public void print() {
        print("", root, false);
    }

    public void print(String prefix, Node<T> node, boolean isLeft) {
        if (node != null) {
            System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + node);
            print(prefix + (isLeft ? "|   " : "    "), node.leftChild, true);
            print(prefix + (isLeft ? "|   " : "    "), node.rightChild, false);
        }
    }

    public int getMaxHeight() {
        if (root.rightChild == null && root.leftChild == null) {
            return 1;
        } else if (root.leftChild == null && root.rightChild != null) {
            return maxHeight(2, root.rightChild);
        } else if (root.leftChild != null && root.rightChild == null) {
            return maxHeight(2, root.leftChild);
        } else {
            return Math.max(maxHeight(2, root.leftChild),maxHeight(2, root.rightChild));
        }
    }

    public int maxHeight(int counter, Node<T> node) {
        if (node.rightChild == null && node.leftChild == null) {
            return counter;
        } else if (node.rightChild != null && node.leftChild == null) {
            return maxHeight(counter + 1, node.rightChild);
        } else if (node.leftChild != null && node.rightChild == null) {
            return maxHeight(counter + 1, node.leftChild);
        } else {
            int right = maxHeight(counter + 1, node.rightChild);
            int left = maxHeight(counter + 1, node.leftChild);
            return Math.max(right, left);
        }
    }

    public int getMinHeight() {
        if (root.rightChild == null || root.leftChild == null) {
            return 1;
        } else {
            return minHeight(1, root);
        }
    }

    public int minHeight(int counter, Node<T> node) {
        if (node.rightChild == null && node.leftChild == null) {
            return counter;
        } else if (node.rightChild != null && node.leftChild == null) {
            return minHeight(counter + 1, node.rightChild);
        } else if (node.leftChild != null && node.rightChild == null) {
            return minHeight(counter + 1, node.leftChild);
        } else {
            int right = minHeight(counter + 1, node.rightChild);
            int left = minHeight(counter + 1, node.leftChild);
            return Math.min(right, left);
        }
    }

}