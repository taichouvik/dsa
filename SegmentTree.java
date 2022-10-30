//concept
//segment = rangeFunction(lo,hi) = rangeFuntion[lo,mid] + rangeFunction[mid+1, hi-1]
//ref from cp-algorithm
public class SegmentTree {

    int[] t;
    int N;

    public SegmentTree(int n, int l, int r) {
        N = 4 * n;
        t = new int[N];
    }

    // call with (array, 1, 0, n-1) from main logn
    public void build(int[] a, int v, int tl, int tr) {
        if (tl == tr)
            t[v] = a[tl];
        else {
            int tm = tl + (tr - tl) / 2;
            build(a, 2 * v, tl, tm);
            build(a, 2 * v + 1, tm + 1, tr);
            t[v] = t[v * 2] + t[v * 2 + 1];
        }
    }

    // sum query logn
    public int query(int v, int tl, int tr, int l, int r) {
        if (l > r)
            return 0;
        if (l == tl && r == tr)
            return t[l];
        int tm = tl + (tr - tl) / 2;
        return query(2 * v, tl, tm, l, Math.min(r, tm)) + query(2 * v + 1, tm + 1, tr, Math.max(l, tm + 1), r);
    }

    // point update query logn
    public void update(int v, int tl, int tr, int x, int val) {
        if (tl == tr)
            t[tl] = val;
        int tm = tl + (tr - tl) / 2;
        if (x <= tm)
            update(v * 2, tl, tm, x, val);
        else
            update(v * 2 + 1, tm + 1, tr, x, val);
        t[v] = t[v * 2] + t[v * 2 + 1];
    }
}
