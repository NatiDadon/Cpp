/**
 * @autohor Nati Dadon
 * @date January 2022
 * this class represnts lists with merging options for sorted/unsorted lists
 */
import java.util.HashSet;
public class MergeableHeap extends List
{
    static int MIN_VAL = Integer.MAX_VALUE;
    static Node MIN_NODE = null;
    static HashSet<Integer> hs = new HashSet<Integer>();
    static int LENGTH = 0;

    /* properties */
    List headOfMH;
    boolean is_MH_sorted;

    public MergeableHeap()
    {
        super(null,null);
        headOfMH = null;
    }

    public MergeableHeap(List list)
    {
        super(list._head,list._tail);
        headOfMH = list;
    }

    /**
     * remove duplicates from this list. for example: Before: 1->2->3->2->null . After: 1->2->3->null
     * @param head head of the list to remove the duplicates
     */
     void removeDuplicate(Node head)
    {
        // Hash to store seen values
        HashSet<Integer> hs = new HashSet<>();

        /* Pick elements one by one */
        Node current = head;
        Node prev = null;
        while (current != null) {
            int curval = current._data;

            // If current value is seen before
            if (hs.contains(curval)) // Time/Space complexity = O(1)
            {
                prev._next = current._next;
            }
            else {
                hs.add(curval);
                prev = current;
            }
            current = current._next;
        }
    }

    // Merges two lists with headers as h1 and h2.
    // It assumes that h1's data is smaller than
    // or equal to h2's data.
     Node mergeUtil(Node h1, Node h2)
    {
        // if only one node in first list
        // simply point its head to second list
        if (h1._next == null) {
            h1._next = h2;
            return h1;
        }

        // Initialize current and next pointers of
        // both lists
        Node curr1 = h1, next1 = h1._next;
        Node curr2 = h2, next2 = h2._next;

        while (next1 != null && curr2 != null) {
            // if curr2 lies in between curr1 and next1
            // then do curr1->curr2->next1
            if ((curr2._data) >= (curr1._data) && (curr2._data) <= (next1._data)) {
                next2 = curr2._next;
                curr1._next = curr2;
                curr2._next = next1;

                // now let curr1 and curr2 to point
                // to their immediate next pointers
                curr1 = curr2;
                curr2 = next2;
            }
            else {
                // if more nodes in first list
                if (next1._next != null) {
                    next1 = next1._next;
                    curr1 = curr1._next;
                }

                // else point the last node of first list
                // to the remaining nodes of second list
                else {
                    next1._next = curr2;
                    return h1;
                }
            }
        }
        return h1;
    }

    /**
     * Merges two given lists in-place. This function mainly compares head nodes and calls mergeUtil()
     * @param h1 head of list 1
     * @param h2 head of list 2
     * @return list 1 and 2 merged and sorted (with duplicates)
     */

     Node merge(Node h1, Node h2)
    {
        if (h1 == null)
            return h2;
        if (h2 == null)
            return h1;

        // start with the linked list
        // whose head data is the least
        if (h1._data < h2._data)
            return mergeUtil(h1, h2);
        else
            return mergeUtil(h2, h1);
    }


    /**
     *  function to insert a new_node in a list in sorted way.
     *  Time Complexity = O(n). Space Complexity = O(1)
     * @param new_node new node to insert to correct place in the sorted list
     */
     void sortedInsert(Node new_node)
    {
        Node current;

        /* Special case for head node */
        if (_head == null || _head._data >= new_node._data)
        {
            new_node._next = _head;
            _head = new_node;
        }
        else {

            /* Locate the node before point of insertion. */
            current = _head;

            while (current._next != null && current._next._data < new_node._data) {
                current = current._next;
            }

            new_node._next = current._next;
            current._next = new_node;
        }
    }


    /**
     * 1. check that x doesn't already exist in hash set,else don't insert. Time complexity on average = O(1)
     * 2. insert x in sorted way, and to the hash set. Time complexity on average = O(n)
     * 3. update the minimum if x < MIN_VAL . update the length of the list O(1)
     * @param x value to insert.
     */
     void insert_To_Sorted_List(int x)
    {
        if(hs.contains(x)) // Check if x exist in the list - Time/Space complexity on average = O(1)
        {}
        else
        {
            /* add to tail - O(1) */
            Node newNode = new Node(x,null);
            if(_head == null){
                _head = newNode; _tail = newNode;
            }
            else // insert to correct place in the sorted list
            {
                sortedInsert(newNode); // Time complexity O(n)
            }
            hs.add(x); // add this value to hash set
            if(x < MIN_VAL)
            {
                MIN_VAL = x;
                MIN_NODE = newNode;
            }
            LENGTH++;
        }
    }

    /**
     * 1. check that x doesn't already exist in hash set,else don't insert. Time complexity on average = O(1)
     * 2. insert x to tail of the list, and to the hash set. Time complexity on average = O(1)
     * 3. update the minimum if x < MIN_VAL . O(1)
     * @param x value to insert.
     */
     void insert_To_Unsorted_List(int x)
    {
        if(hs.contains(x)) // Check if x exist in the structure - Time complexity on average = O(1)
        {}
        else
        {
            /* add to tail - O(1) */
            Node newNode = new Node(x,null);
            if(_head == null){
                _head = newNode; _tail = newNode;
                MIN_VAL = x;
            }
            else{
                _tail._next = newNode;
                _tail = newNode;
            }
            hs.add(x); // add this value to hash set Time/Space complexity = O(1)
            if(x < MIN_VAL)
            {
                MIN_VAL = x;
                MIN_NODE = newNode;
            }
            LENGTH++;
        }
    }


    /**
     * 1. if list had 0 elements return null. if list had 1 elements return him. O(1)
     * 2. else, remove the head of the list, and update the new head to head.next. O(1)
     * @return node of the minimum
     */
     Node Extract_Min_Of_Sorted_List()
    {
        if(_head == null) {
            System.out.println("Cannot extract from empty list!");
            return null;
        }
        else if(_head._next == null){ // if only 1 element on the list
            MIN_VAL = Integer.MIN_VALUE;
            Node minNode = _head;
            _head = null;
            return minNode;
        }
        else {
            Node newMinNode = this._head._next; // update the new minimum
            Node minNodeToExtract = _head; // pointer to save the old minimum
            _head = newMinNode; // update the head of list after the extract
            minNodeToExtract._next = null; // extract old minimum from the list
            MIN_VAL = newMinNode._data; // update the static minimum value
            return minNodeToExtract; // return the old minimum
        }
    }

    /**
     * 1. if list had 0 elements return null. if list had 1 elements return him. O(1)
     * 2. else,scan and find the node of the minimum, and concatenate her prev with her next. O(n)
     * 3. scan and find the new minimum, after the remove of the old minimum. O(n)
     * @return node of the minimum
     */
     Node Extract_Min_Of_Unsorted_List()
    {
        if(_head == null) {
            System.out.println("Cannot extract from empty list!");
            return null;
        }
        else if(_head._next == null){ // if only 1 element on the list
            MIN_VAL = Integer.MIN_VALUE;
            Node minNode = _head;
            _head = null;
            return minNode;
        }
        else
        {
            Node prev = _head;
            Node curr = _head._next;
            Node minNode = _head;
            Node prevMinNode = null;

            while (curr != null)
            {
                if(minNode._data > curr._data)
                {
                    minNode = curr;
                    prevMinNode = prev;
                }
                prev = curr;
                curr = curr._next;
            }
            if(minNode==_head) // if we want to remove the head
            {
                _head=minNode._next;
                minNode._next = null;
            }
            else {
                prevMinNode._next = minNode._next; // remove minimum node
            }
            /* update the new minimum of the structure */
            int min = _head._data;
            Node current = _head._next;
            while(current != null)
            {
                if(current._data < min)
                {
                    min = current._data;
                }
                current = current._next;
            }
            MIN_VAL = min;
            LENGTH--;
            return minNode;
        }
    }

    /**
     * 1.copy values from 2 lists into 2 new lists. Time = O(n+m). Space = O(n+m)
     * 2.merge in sorted way the 2 new lists into 1 union mergeable list.
     * 3.remove duplications from the union list and return the union list. Time = O(n+m)
     * @param other other list to union
     * @return union mergeable list
     */
     MergeableHeap Union_Sorted_List(List other)
    {
        List newThisList = copyList(this); // copy values from this list to new list
        List newOtherList = copyList(other);// copy values from this list to new list

        List sortedMergeList = new List(merge(newThisList._head,newOtherList._head)); //O(n+m);

        MergeableHeap sortedMergeableList = new MergeableHeap(sortedMergeList);
        sortedMergeableList.removeDuplicate(sortedMergeList._head); // O(n+m)
        MIN_VAL = sortedMergeableList._head._data;
        LENGTH++;
        return sortedMergeableList;

    }

     MergeableHeap Union_Unsorted_List(List other)
    {

        List newThisList = copyList(this); // copy values from this list to new list. O(n)
        List newOtherList = copyList(other);// copy values from this list to new list. O(m)

        if(newThisList == null && newOtherList == null){ return null; }
        if(newThisList == null && newOtherList != null)
        {
            MergeableHeap newUnionList = new MergeableHeap(newOtherList);
            return newUnionList;
        }
        if(newThisList != null && newOtherList == null)
        {
            MergeableHeap newUnionList = new MergeableHeap(newThisList);
            return newUnionList;
        }

        newThisList._tail._next = newOtherList._head; // concatenate tail of this list to head of other list. O(1)
        MergeableHeap newUnionList = new MergeableHeap(newThisList);// O(1)
        newUnionList.removeDuplicate(newThisList._head); // O(n)
        return newUnionList;
    }

    public void setSorted(boolean sorted){is_MH_sorted = sorted;}
    public boolean isSorted(){ return is_MH_sorted; }

    public MergeableHeap MakeHeap()
    {
        return new MergeableHeap();
    }

    public void Insert(int x)
    {
        if(this.isSorted())
            this.insert_To_Sorted_List(x);
        else
            this.insert_To_Unsorted_List(x);
    }

    public int Min()
    {
        if (_head == null) {
            System.out.println("the list is empty!.\n the deault minimum:");
            return Integer.MIN_VALUE;
        }
        else
        {
            return MIN_VAL;
        }
    }

    public Node ExtractMin()
    {
        if(this.isSorted())
            return Extract_Min_Of_Sorted_List();
        else
            return Extract_Min_Of_Unsorted_List();
    }

    public MergeableHeap Union(List other)
    {
        if(this.isSorted())
            return this.Union_Sorted_List(other);
        else
            return this.Union_Unsorted_List(other);
    }
}
