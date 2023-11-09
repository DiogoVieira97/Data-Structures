/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

/**
 *
 * @author xavie
 */
public class LinkedListSortingAndSearching<T> {

    /**
     * Recursively sorts a portion of an array of elements in ascending order using the merge sort algorithm.
     *
     * This method divides the specified portion of the input array into smaller subarrays, recursively sorts each subarray,
     * and then merges the sorted subarrays to produce a single sorted portion of the array.
     *
     * @param a The array of elements to be sorted, where the elements must implement the Comparable interface.
     * @param tempArray An auxiliary array used for temporary storage during the merge sort process.
     * @param left The leftmost index of the portion of the array to be sorted.
     * @param right The rightmost index of the portion of the array to be sorted.
     * @param <T> The type of elements in the array.
     */
    private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tempArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tempArray, left, center);
            mergeSort(a, tempArray, center + 1, right);
            merge(a, tempArray, left, center + 1, right);
        }
    }

    /**
     * Sorts an array of elements in ascending order using the merge sort algorithm.
     *
     * This method initiates the merge sort process by creating an auxiliary array (tmpArray) and then
     * recursively sorts the elements of the input array using the merge sort algorithm.
     *
     * @param a The array of elements to be sorted, where the elements must implement the Comparable interface.
     * @param <T> The type of elements in the array.
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
        T[] tmpArray = (T[]) new Comparable[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    /**
     * Sorts an array of elements in ascending order using the insertion sort algorithm.
     *
     * This method iteratively builds a sorted portion of the array by comparing each element with the previous
     * elements and inserting it into the correct position within the sorted portion. It continues this process
     * until the entire array is sorted.
     *
     * @param a The array of elements to be sorted, where the elements must implement the Comparable interface.
     * @param <T> The type of elements in the array.
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] a) {
        int j;
        for (int i = 1; i < a.length; i++) {
            T temp = a[i];
            for (j = i; j > 0 && temp.compareTo(a[j - 1]) < 0; j++) {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }

    /**
     * Merges two adjacent subarrays within an array into a single sorted subarray.
     *
     * This method merges two adjacent subarrays within the specified array into a single sorted subarray.
     * It uses an auxiliary array (tmpArray) to perform the merge operation. The subarrays to be merged are
     * defined by the leftPos, rightPos, and rightEnd parameters.
     *
     * @param a The array containing the subarrays to be merged.
     * @param tmpArray An auxiliary array used for temporary storage during the merge operation.
     * @param leftPos The starting index of the left subarray to be merged.
     * @param rightPos The starting index of the right subarray to be merged.
     * @param rightEnd The ending index of the right subarray to be merged.
     * @param <T> The type of elements stored in the array, which must implement the Comparable interface.
     */
    private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftEnd + 1;

        //main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }
        while (leftPos <= leftEnd) {
            tmpArray[tmpPos++] = a[leftPos++];
        }
        while (rightPos <= rightEnd) {
            tmpArray[tmpPos++] = a[rightPos++];
        }
        //copy tmpArray back
        for (int i = 0; i < numElements; i++) {
            a[rightEnd] = tmpArray[rightEnd];
        }
    }

    /**
     * Searches for a target element within a singly linked list.
     *
     * This method iteratively traverses the linked list, comparing each element with the target element.
     * It returns true if the target element is found in the linked list; otherwise, it returns false.
     *
     * @param head The head node of the linked list to start the search from.
     * @param target The element to search for within the linked list.
     * @param <T> The type of elements stored in the linked list, which must implement the Comparable interface.
     * @return true if the target element is found within the linked list; otherwise, false.
     */
    public static <T extends Comparable<? super T>> boolean linearSearch(LinearNode head, T target) {
        LinearNode<T> current = head;
        boolean found = false;
        while (current != null && !found) {
            if (target.equals(current.getElement())) {
                found = true;
            } else {
                current = current.getNext();
            }
        }
        return found;
    }

    /**
     * Recursively searches for a target element within a singly linked list starting from the given head node.
     *
     * This method recursively traverses the linked list and compares each element with the target element
     * until the target element is found or the end of the list is reached.
     *
     * @param head The head node of the linked list to start the search from.
     * @param target The element to search for within the linked list.
     * @param <T> The type of elements stored in the linked list, which must implement the Comparable interface.
     * @return true if the target element is found within the linked list; otherwise, false.
     */
    public static <T extends Comparable<? super T>> boolean RecursivelinearSearch(LinearNode<T> head, T target) {
        if (head != null) {
            return false;
        }
        if (target.compareTo(head.getElement()) == 0) {
            return true;
        }
        return RecursivelinearSearch(head.getNext(), target);
    }

    /**
     * Finds and returns the middle node of a portion of a singly linked list.
     *
     * This method determines the middle node of a portion of the linked list specified by the head and tail nodes
     * by using two pointers, one moving at twice the speed of the other. When the faster pointer reaches the
     * tail node or the end of the specified portion, the slower pointer will be at the middle node.
     *
     * @param head The head node of the portion of the linked list to start the search from.
     * @param tail The tail node of the portion of the linked list where the search should end.
     * @return The middle node of the specified portion of the linked list if it exists; otherwise, returns null.
     */
    public static LinearNode<Integer> getMiddleNode(LinearNode<Integer> head, LinearNode<Integer> tail) {
        if (head == null) {
            return null;
        }

        LinearNode<Integer> slow = head;
        LinearNode<Integer> fast = head.getNext();

        while (fast != tail) {
            fast = fast.getNext();
            if (fast != tail) {
                slow = slow.getNext();
                fast = fast.getNext();
            }
        }
        return slow;
    }

    /**
     * Performs a binary search for an integer value within a sorted singly linked list.
     *
     * This method iteratively searches for the specified value within the sorted linked list
     * by repeatedly dividing the search range in half until the value is found or it is determined
     * that the value is not present in the list.
     *
     * @param head The head node of the sorted linked list to search in.
     * @param value The integer value to search for within the linked list.
     * @return The node containing the value if found, or null if the value is not present in the list.
     */
    public static LinearNode<Integer> binarySearch(LinearNode<Integer> head, int value) {
        LinearNode<Integer> start = head;
        LinearNode<Integer> last = null;

        do {
            // Find Middle
            LinearNode<Integer> mid = getMiddleNode(start, last);

            // If middle is empty
            if (mid == null) {
                return null;
            }

            // If value is present at middle
            if (mid.getElement() == value) {
                return mid;
            } // If value is less than mid
            else if (mid.getElement() > value) {
                start = mid.getNext();
            } // If the value is more than mid.
            else {
                last = mid;
            }
        } while (last == null || last != start);

        // value not present
        return null;
    }

    /**
     * Finds and returns the middle node of a singly linked list.
     *
     * This method determines the middle node of a linked list by using two pointers,
     * one moving at twice the speed of the other. When the faster pointer reaches the
     * end of the list, the slower pointer will be at the middle node.
     *
     * @param head The head node of the linked list for which the middle node is to be found.
     * @return The middle node of the linked list if it exists; otherwise, returns null.
     */
    public static LinearNode getMiddle(LinearNode head) {
        if (head == null) {
            return head;
        }

        LinearNode slow = head, fast = head.getNext();

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext();
        }
        return slow;
    }

    /**
     * Sorts a singly linked list in ascending order using the merge sort algorithm.
     *
     * This method recursively divides the input linked list into two halves, sorts each half separately,
     * and then merges the sorted halves to produce a single sorted list.
     *
     * @param h The head node of the linked list to be sorted.
     * @param <T> The type of elements stored in the linked list, which must implement the Comparable interface.
     * @return The head node of the sorted linked list.
     */
    public static <T extends Comparable<? super T>> LinearNode mergeSort(LinearNode h) {
        // Base case : if head is null
        if (h == null || h.getNext() == null) {
            return h;
        }

        // get the middle of the list
        LinearNode middle = getMiddle(h);
        LinearNode nextofmiddle = middle.getNext();

        // set the next of middle node to null
        middle.setNext(null);

        // Apply mergeSort on left list
        LinearNode left = mergeSort(h);

        // Apply mergeSort on right list
        LinearNode right = mergeSort(nextofmiddle);

        // Merge the left and right lists
        LinearNode sortedlist = sortedMerge(left, right);
        return sortedlist;
    }

    /**
     * Merges two sorted linked lists into a single sorted linked list.
     *
     * This method takes two sorted linked lists as input and recursively merges them into a single
     * sorted linked list while preserving the order of elements.
     *
     * @param a The head node of the first sorted linked list.
     * @param b The head node of the second sorted linked list.
     * @return The head node of the merged sorted linked list.
     */
    private static LinearNode<Integer> sortedMerge(LinearNode<Integer> a, LinearNode<Integer> b) {
        LinearNode<Integer> result = null;
        /* Base cases */
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        /* Pick either a or b, and recur */
        if (a.getElement() <= b.getElement()) {
            result = a;
            result.setNext(sortedMerge(a.getNext(), b));
        } else {
            result = b;
            result.setNext(sortedMerge(a, b.getNext()));
        }
        return result;
    }

    /**
     * Sorts a portion of a singly linked list in ascending order using the quick sort algorithm.
     *
     * This method recursively applies the quick sort algorithm to sort a portion of the linked list
     * between the specified start and end nodes. It selects a pivot element, partitions the list around
     * the pivot, and recursively sorts the sublists on either side of the pivot.
     *
     * @param start The head node of the portion of the linked list to be sorted.
     * @param end The tail node (inclusive) of the portion of the linked list to be sorted.
     * @param <T> The type of elements stored in the linked list, which must implement the Comparable interface.
     */
    public static <T extends Comparable<? super T>> void quickSort(LinearNode<Integer> start, LinearNode<Integer> end) {
        if (start == end) {
            return;
        }

        // split list and partion recurse
        LinearNode<Integer> prev = paritionLast(start, end);
        quickSort(start, prev);

        // if pivot is picked and moved to the start,
        // that means start and pivot is same
        // so pick from next of pivot
        if (prev != null && prev == start) {
            quickSort(prev.getNext(), end);
        } // if pivot is in between of the list,
        // start from next of pivot,
        // since we have pivot_prev, so we move two nodes
        else if (prev != null
                && prev.getElement() != null) {
            quickSort(prev.getNext().getNext(), end);
        }
    }

    /**
     * Partitions a portion of a singly linked list based on the last element as the pivot.
     *
     * This method partitions the portion of the linked list between the specified start and end nodes
     * by selecting the last element as the pivot. It rearranges the elements such that all elements less
     * than the pivot are on the left, and all elements greater than or equal to the pivot are on the right.
     *
     * @param start The head node of the portion of the linked list to be partitioned.
     * @param end The tail node of the portion of the linked list to be partitioned.
     * @return The previous node of the pivot element after the partitioning is complete.
     */
    private static LinearNode<Integer> paritionLast(LinearNode<Integer> start, LinearNode<Integer> end) {
        if (start == end || start == null || end == null) {
            return start;
        }

        LinearNode<Integer> pivot_prev = start;
        LinearNode<Integer> curr = start;
        int pivot = end.getElement();

        // iterate till one before the end,
        // no need to iterate till the end
        // because end is pivot
        while (start != end) {
            if (start.getElement() < pivot) {
                // keep tracks of last modified item
                pivot_prev = curr;
                int temp = curr.getElement();
                curr.setElement(start.getElement());
                start.setElement(temp);
                curr = curr.getNext();
            }
            start = start.getNext();
        }

        // swap the position of curr i.e.
        // next suitable index and pivot
        int temp = curr.getElement();
        curr.setElement(pivot);
        end.setElement(temp);

        // return one previous to current
        // because current is now pointing to pivot
        return pivot_prev;
    }

    /**
     * Sorts an array of elements in ascending order using the bubble sort algorithm.
     *
     * This method iteratively compares adjacent elements in the array and swaps them if they are in the wrong order.
     * The pass through the array is repeated until no more swaps are needed, indicating that the array is sorted.
     *
     * @param data An array of elements to be sorted, where the elements must implement the Comparable interface.
     * @param <T> The type of elements in the array.
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] data) {
        for (int len = data.length; len > 1; len--) {
            for (int i = 1; i < len; i++) {
                if (data[i - 1].compareTo(data[i]) > 0) { // left element is larger than right
                    // swap the two elements
                    T temp = data[i - 1];
                    data[i - 1] = data[i];
                    data[i] = temp;
                }
            }
        }
    }

    /**
     * Sorts an array of elements in ascending order using the selection sort algorithm.
     *
     * This method repeatedly finds the minimum element from the unsorted portion of the array and swaps it
     * with the first unsorted element. It continues this process until the entire array is sorted.
     *
     * @param data An array of elements to be sorted, where the elements must implement the Comparable interface.
     * @param <T> The type of elements in the array.
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] data) {
        int n = data.length, min_id;
        T min, tmp;

        for (int i = 0; i < n - 1; i++) {
            min = data[i];
            min_id = i;
            for (int j = i + 1; j < n; j++) {
                if (data[j].compareTo(min) < 0) {
                    min = data[j];
                    min_id = j;
                }
            }
            tmp = data[i];
            data[i] = data[min_id];
            data[min_id] = tmp;
        }
    }
}
