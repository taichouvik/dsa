// biniary indexed tree
// just like a binary tree which has a template for getting balanced
// it can sub a bst as smaller index represent smaller elements and vice versa
// segment tree also has a template for getting balanced

//this implementation is 1 based index.
//ref is cp-algorithms
//logn point updates
//logn sum queries

public class FenwickTree {
    int[] bit;

    public void build(int[] ar) {
        int N = ar.length;
        bit = new int[2 * N + 1];
        for (int i = 0; i < ar.length; i++) {
            update(i, ar[i]);
        }
    }

    public void update(int idx, int val) {
        idx++;
        for (int i = idx; i < bit.length; i += i & (-i)) {
            bit[i] += val;
        }
    }

    public int query(int idx) {
        idx++;
        int sum = 0;
        for (int i = idx; i > 0; i -= i & (-i)) {
            sum += bit[i];
        }
        return sum;
    }

}

class Runner {
    public static void main(String[] args) {
        FenwickTree ft = new FenwickTree();
        ft.build(new int[] { 1, 2, 3, 4, 5 });
        System.out.println(ft.query(4));
        ft.update(2, 5);
        System.out.println(ft.query(4));
    }
}