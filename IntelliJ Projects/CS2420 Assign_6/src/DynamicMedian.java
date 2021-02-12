import java.text.DecimalFormat;

public class DynamicMedian<E extends Comparable<E>> {
    public E currMedian;
    private LeftistHeap<E> maxHeap = new LeftistHeap<>();
    private SkewHeap<E> minHeap = new SkewHeap<>();
    private int count;
    public int printCount = 25;

    DynamicMedian() {}

    /**
     * Inserts element into either the minHeap or the maxHeap according to it's value from the currMedian
     * @param: the element to be inserted
     */
    public void insert(E element) {
        E tmp = currMedian;

        // Check if value is less than or greater than the currMedian, and places it accordingly
        if (currMedian == null) currMedian = element;
        if (currMedian.compareTo(element) < 0) {
            minHeap.insert(element);
        }
        if (currMedian.compareTo(element) > 0) {
            maxHeap.insert(element);
        }

        // If maxHeap is bigger than minHeap by more than 1 then the new currMedian is the maxVal of the maxHeap
        // and the old currMedian is pushed into minHeap
        if (maxHeap.sizeOf() > minHeap.sizeOf()) {
            int sizeDifference = Math.abs(maxHeap.sizeOf() - minHeap.sizeOf());
            if (sizeDifference > 1) {
                currMedian = maxHeap.removeMax();
                minHeap.insert(tmp);
            }
        }
        // If minHeap is bigger than maxHeap by more than 1 then the new currMedian is the minVal of the minHeap
        // and the old currMedian is pushed into maxHeap
        if (minHeap.sizeOf() > maxHeap.sizeOf()) {
            int sizeDifference = Math.abs(maxHeap.sizeOf() - minHeap.sizeOf());
            if (sizeDifference > 1) {
                currMedian = minHeap.removeMin();
                maxHeap.insert(tmp);
            }
        }
        count += 1;

        // Simple counter check to print off dynamic median every n (printCount) inserts
        if (count % printCount == 0) {
            printDynamicMedian(currMedian);
        }
    }

    /**
     * Prints the Dynamic Median
     * @param: currMedian to be printed
     */
    public void printDynamicMedian(E currMedian) {
        DecimalFormat format = new DecimalFormat("$###,###,###");
        System.out.println("Median Salary after " + count + " entries is: " + format.format(currMedian));
    }

    /**
     * Sets the print count to desired value
     * @param: new desired printCount
     */
    public void setPrintCount(int newCount) {
        printCount = newCount;
    }
}
