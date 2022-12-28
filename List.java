public class List
{
    Node _head;
    Node _tail;

    public List(){
        _head = null; _tail = null;
    }
    public List(Node head,Node tail)
    {
        _head = head; _tail = tail;
    }

    protected List copyList(List other)
    {
        List thisList = new List();
        Node t = other._head;
        while(t != null)
        {
            thisList.addToTail(t._data);
            t = t._next;
        }
        return thisList;
    }

    public List(Node head)
    {
        _head = head;
    }

    public Node getHead()
    {
        return _head;
    }

    public Node getTail()
    {
        return _tail;
    }

    // Time complexity = O(1); Space Complexity = O(1)
    public void addToHead(int n)
    {
        Node newNode = new Node(n,null);
        if(_head == null){
            _head = newNode; _tail = newNode;
        }
        else{
            newNode._next = _head;
            _head = newNode;
        }

    }

    // Time complexity = O(1); Space Complexity = O(1)
    public void addToTail(int n)
    {
        Node newNode = new Node(n,null);
        if(_head == null){
            _head = newNode; _tail = newNode;
        }
        else{
            _tail._next = newNode;
            _tail = newNode;
        }
    }



    // Time complexity = O(n); Space Complexity = O(1)
    public void printList()
    {
        Node p = _head;
        while(p != null) {
            System.out.print(p._data + " ");
            p = p._next;
        }
        System.out.println();
    }
}