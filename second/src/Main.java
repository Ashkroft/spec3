

class Main {
    //calculating the value of the expression
    public static Integer eval(Node<Integer, Character> node) {
        return node.process(i -> i, (Character s, Integer k, Integer n) -> {
            if (s == '+') return k + n;
            if (s == '-') return k - n;
            if (s == '*') return k * n;
            return 0;
        });
    }

    //transforming the tree into a character string
    public static String str(Node<Integer, Character> node) {
        return node.process(i -> Integer.toString(i), (Character s, String k, String n) -> "(" + k + s.toString() + n + ")");
    }

    //inverting the values in the leaves on zero
    public static Node<Integer, Character> invert(Node<Integer, Character> node) {
        return node.process(i -> new Leaf<Integer, Character>(-i), (TreeFunction<Character, Node<Integer, Character>>) BiNode::new);
    }


    public static void main(String[] args) {
        Node<Integer, Character> nodeOne = new BiNode<>('+', new Leaf<>(5), new Leaf<>(3));
        Node<Integer, Character> nodeTwo = new BiNode<>('-', nodeOne, new Leaf<>(4));
        Node<Integer, Character> nodeThree = new BiNode<>('*', nodeTwo, new Leaf<>(2));

        System.out.println("Значение выражения равно:" + eval(nodeThree));
        System.out.println("Дерево, записанное в символьной строке: " + str(nodeThree));
        System.out.println("Новое дерево имеет вид: " + str(invert(nodeThree)));
    }
}


