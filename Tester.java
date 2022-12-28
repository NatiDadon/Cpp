import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Tester
{
    final static int MAX_LISTS = 100;
    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);
        boolean toExitThisProgram = false;
        int current_Sorted_Lists = 0;
        int current_Unsorted_Lists = 0;
        MergeableHeap[] array_Of_Sorted_Mergeable_Heap = new MergeableHeap[MAX_LISTS];
        MergeableHeap[] array_Of_Unsorted_Mergeable_Heap = new MergeableHeap[MAX_LISTS];
        while(!toExitThisProgram && (current_Sorted_Lists<MAX_LISTS) && (current_Unsorted_Lists<MAX_LISTS))
        {
            System.out.println("Please choose the lists that you want to work with :");
            System.out.print("1) Sorted lists.\n2) Unsorted lists.\n3) Disjoint lists.\n");
            int listOption = input.nextInt();

            if( listOption == 1) // if the user choose sorted list
            {
                int i = 0;
                int chosenAction = 1; // assume that first order is to make empty heap
                boolean toExitSortedLists = false;
                while (!toExitSortedLists && (current_Sorted_Lists<MAX_LISTS)) // while user didn't choose exit
                {
                    MergeableHeap sorted_MH = new MergeableHeap();
                    sorted_MH.setSorted(true); //property to recognize that this MH is sorted
                    System.out.print("Choose the action:\n1) Make heap.\t2) Insert.\t3) get Minimum.\n");
                    System.out.print("4) Extract minimum.\t5) Union.\t6) Exit.\n");
                    chosenAction = input.nextInt();
                    switch(chosenAction)
                    {
                        case 1 : // in case make new heap, we save the next actions in the the next cell
                            i++;
                            array_Of_Sorted_Mergeable_Heap[i] = sorted_MH.MakeHeap();
                            array_Of_Sorted_Mergeable_Heap[i].setSorted(true);
                            current_Sorted_Lists++;
                            break;

                        case 2: // in case the user choose to insert x
                            System.out.println("Please choose the number to insert :");
                            int x = input.nextInt();
                            array_Of_Sorted_Mergeable_Heap[i].Insert(x);
                            System.out.println("The list now is :");
                            array_Of_Sorted_Mergeable_Heap[i].printList();
                            break;

                        case 3: // in case the user choose to get minimum
                            int min = array_Of_Sorted_Mergeable_Heap[i].Min();
                            System.out.println("The Minimum in this list is : "+min);
                            System.out.println("The list now is :");
                            array_Of_Sorted_Mergeable_Heap[i].printList();
                            break;

                        case 4: // in case the user choose to extract minimum
                            Node minNode = array_Of_Sorted_Mergeable_Heap[i].ExtractMin();
                            int minVal = minNode._data;
                            System.out.println("The Minimum that extract is : "+minVal);
                            System.out.println("The list now is :");
                            array_Of_Sorted_Mergeable_Heap[i].printList();
                            break;

                        case 5: // in case the user choose to union lists
                            System.out.println("All the lists now are :");
                            for(int j=1; j<=current_Sorted_Lists; j++)
                            {
                                System.out.print("List "+j+" :");
                                array_Of_Sorted_Mergeable_Heap[j].printList();
                                System.out.println("\n");
                            }
                            System.out.println("choose the 2 lists that you want to union (x y):");
                            int firstList = input.nextInt();
                            int secondList = input.nextInt();
                            System.out.println("The list that are chosen:");
                            array_Of_Sorted_Mergeable_Heap[firstList].printList();
                            array_Of_Sorted_Mergeable_Heap[secondList].printList();

                            array_Of_Sorted_Mergeable_Heap[current_Sorted_Lists+1] = array_Of_Sorted_Mergeable_Heap[firstList].Union(array_Of_Sorted_Mergeable_Heap[secondList]);

                            System.out.println("The new list now is :");
                            array_Of_Sorted_Mergeable_Heap[current_Sorted_Lists+1].printList();
                            current_Sorted_Lists++;
                            i++;
                            break;

                        default:
                            toExitSortedLists = true;
                    }

                }

            }
            else // Unsorted/Disjoint
            {
                int i = 0;
                int chosenAction = 1; // assume that first order is to make empty heap
                boolean toExitUnsortedLists = false;
                while (!toExitUnsortedLists && (current_Unsorted_Lists<MAX_LISTS)) // while user didn't choose exit
                {
                    MergeableHeap unsorted_MH= new MergeableHeap();
                    //sorted_MH.setSorted(true); //property to recognize that this MH is sorted
                    System.out.print("Choose the action:\n1) Make heap.\t2) Insert.\t3) get Minimum.\n");
                    System.out.print("4) Extract minimum.\t5) Union.\t6) Exit.\n");
                    chosenAction = input.nextInt();
                    switch(chosenAction)
                    {
                        case 1 : // in case make new heap, we save the next actions in the the next cell
                            i++;
                            array_Of_Unsorted_Mergeable_Heap[i] = unsorted_MH.MakeHeap();
                            unsorted_MH.setSorted(false);
                            current_Sorted_Lists++;
                            break;

                        case 2: // in case the user choose to insert x
                            System.out.println("Please choose the number to insert :");
                            int x = input.nextInt();
                            array_Of_Unsorted_Mergeable_Heap[i].Insert(x);
                            System.out.println("The list now is :");
                            array_Of_Unsorted_Mergeable_Heap[i].printList();
                            break;

                        case 3: // in case the user choose to get minimum
                            int min = array_Of_Unsorted_Mergeable_Heap[i].Min();
                            System.out.println("The Minimum in this list is : "+min);
                            System.out.println("The list now is :");
                            array_Of_Unsorted_Mergeable_Heap[i].printList();
                            break;

                        case 4: // in case the user choose to extract minimum
                            Node minNode = array_Of_Unsorted_Mergeable_Heap[i].ExtractMin();
                            int minVal = minNode._data;
                            System.out.println("The Minimum that extract is : "+minVal);
                            System.out.println("The list now is :");
                            array_Of_Unsorted_Mergeable_Heap[i].printList();
                            break;

                        case 5: // in case the user choose to union lists
                            System.out.println("All the lists now are :");
                            for(int j=1; j<=current_Sorted_Lists; j++)
                            {
                                System.out.print("List "+j+" :");
                                array_Of_Unsorted_Mergeable_Heap[j].printList();
                                System.out.println("\n");
                            }
                            System.out.println("choose the 2 lists that you want to union (x y):");
                            int firstList = input.nextInt();
                            int secondList = input.nextInt();
                            System.out.println("The list that are chosen:");
                            array_Of_Unsorted_Mergeable_Heap[firstList].printList();
                            array_Of_Unsorted_Mergeable_Heap[secondList].printList();

                            array_Of_Unsorted_Mergeable_Heap[current_Sorted_Lists+1] = (MergeableHeap) array_Of_Unsorted_Mergeable_Heap[firstList].Union(array_Of_Unsorted_Mergeable_Heap[secondList]);

                            System.out.println("The new list now is :");
                            array_Of_Unsorted_Mergeable_Heap[current_Sorted_Lists+1].printList();
                            break;

                        default:
                            toExitUnsortedLists = true;
                    }

                } }
            }

            System.out.println("Please choose the lists that you want to work with :");
            System.out.print("1) Sorted lists.\n2) Unsorted lists.\n3) Disjoint lists.\n4)Exit program.");
            int listOption = input.nextInt();
            if(listOption == 4)
            { toExitThisProgram =true; }
        System.out.println("Thanks for using my structure, happy testing :)");
    }

}



