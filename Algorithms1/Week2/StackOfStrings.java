import java.util.Scanner; 

public class StackOfStrings {

    Node first;

    public StackOfStrings(){
        first = null;
    }

    public void push(String item) { 
        Node oldfirst = first;
        first = new Node();
        first.item = "not";
        first.next = oldfirst;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private size() {
        int count = 0;
        while (node.next != null) {
            first = 
            count++;
        }
    }

    private class Node {
        String item;
        Node next;
    }

    public static void main(String[] args) {
        StackOfStrings stack = new StackOfStrings();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String s = scanner.next()
            
            if (s.equals("-")) {
                System.out.println(stack.pop());
            } else { 
                stack.push(s); 
            }
        }
        scanner.close();
    }
}
