package sample;

public class Main {
    public static boolean inOrder(int[] a, int low, int high) {
        if (a[low] >a[high]) return false;
        if (low == high) {
            return true;
        }

        int mid = (high + low) / 2;

        return inOrder(a, low, mid) && inOrder(a, mid + 1, high);

    }

    public static void main(String[] args) {
        int[] a = {1, 62, 15, 16, 17, 18, 192, 21, 55, 62, 63, 64, 65, 67, 68, 74};

        boolean okay = inOrder(a, 0, 15);
        if (okay) System.out.println("Is Okay");
        else System.out.println("Not  Okay");
    }
}
