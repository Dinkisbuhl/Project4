
public class Node {

    String item;
    Node prev;
    Node next;

    public Node(String thing) {
        item = thing;
        prev = null;
        next = null;
    }


    public String getItem() {
        return item;
    }


    public Node getPrev() {
        return prev;
    }


    public Node getNext() {
        return next;
    }


    public void setItem(String r) {
        item = r;
    }


    public void setPrev(Node n) {
        prev = n;
    }


    public void setNext(Node n) {
        next = n;
    }

}
