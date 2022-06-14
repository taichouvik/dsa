public class BinarySearch {

    // call function for inclusive search range
    // lo will be last true
    // hi will be first false
    public static int binarySearch(int lo, int hi) {
        lo--;
        hi++;

        while (hi - lo > 1) {
            int mid = lo + (hi - lo) / 2;
            if (f(mid)) {
                lo = mid;
            } else
                hi = mid;
        }
        return hi;
    }

    // tttttttffffffff
    public static boolean f(int x) {
        return x < 10;
    }

    public static void main(String args[]) {
        // [0,100] search space inclusive
        binarySearch(0, 100);
    }
}
