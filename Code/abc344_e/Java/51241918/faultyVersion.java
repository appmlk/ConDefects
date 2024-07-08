import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Node> map = new HashMap<>();
        int N = scanner.nextInt();
        Node previousNode = null;
        for (int i = 0; i < N; i++) {
            Node node = new Node(scanner.nextInt());
            map.put(node.value, node);

            if(i > 0) {
                previousNode.setNext(node);
                node.setPrevious(previousNode);
            }
            previousNode = node;
        }
        int Q = scanner.nextInt();
        for (int i = 0; i < Q; i++) {
            if(scanner.nextInt() == 1) {
                int X = scanner.nextInt();
                int Y = scanner.nextInt();
                Node prevNode = map.get(X);
                Node nextNode = prevNode.getNext();
                Node newNode = new Node(Y);
                prevNode.setNext(newNode);
                newNode.setPrevious(prevNode);
                if(nextNode != null) {
                    newNode.setNext(nextNode);
                    nextNode.setPrevious(newNode);
                }
                map.put(Y, newNode);
            } else {
                int X = scanner.nextInt();
                Node node = map.get(X);
                Node prevNode = node.getPrevious();
                Node nextNode = node.getNext();
                map.remove(node);
                if(prevNode == null) {
                    nextNode.setPrevious(null);
                } else if(nextNode == null) {
                    prevNode.setNext(null);
                } else {
                    prevNode.setNext(nextNode);
                    nextNode.setPrevious(prevNode);
                }
            }
        }
        Node firstNode = null;
        for (Node node : map.values()) {
            if(node.getPrevious()==null){
                firstNode = node;
            }
        }
        Node targetNode = firstNode;
        StringBuilder sb = new StringBuilder();
        while(true){
            sb.append(targetNode.value).append(" ");
            targetNode = targetNode.getNext();
            if(targetNode == null) {
                break;
            }
        }
        System.out.println(sb.toString().trim());

    }
    public static class Node {
        final int value;
        public Node next = null;
        public Node previous = null;
        Node(int value) {
            this.value = value;
        }
        public void setNext(Node next) {
            this.next = next;
        }
        public Node getNext() {
            return this.next;
        }
        public void setPrevious(Node previous) {
            this.previous = previous;
        }
        public Node getPrevious() {
            return this.previous;
        }
    }

}
