public class TwoDSegmentTree {
    int M, N;
    int[][] t;
    int[][] a;

    public TwoDSegmentTree(int[][] a, int n, int m) {
        M = m;
        N = n;
        this.a = a;
        t = new int[N][M];
    }

    public void build(int v, int tl, int tr) {
        if (tl != tr) {
            int tm = tl + (tr - tl) / 2;
            build(v * 2, tl, tm);
            build(v * 2 + 1, tm + 1, tr);
        }
        buildY(1, 0, M - 1, v, tl, tr);
    }

    public void buildY(int v, int tl, int tr, int vx, int tlx, int trx) {
        if (tl == tr) {
            if (tlx == trx)
                t[vx][v] = a[tlx][tl];
            else
                t[vx][v] = t[vx * 2][v] + t[vx * 2 + 1][v];

        } else {
            int tm = tl + (tr - tl) / 2;
            buildY(v * 2, tl, tm, vx, tlx, trx);
            buildY(v * 2 + 1, tm + 1, tr, vx, tlx, trx);
            t[vx][v] = t[vx][v * 2] + t[vx][v * 2 + 1];
        }
    }

    int sum(int v, int tl, int tr, int l, int r, int l2, int r2) {
        if (l > r)
            return 0;
        if (l == tl && r == tr)
            return sumY(1, 0, M - 1, l2, r2, v);
        int tm = tl + (tr - tl) / 2;
        return sum(v * 2, tl, tm, l, Math.min(tm, r), l2, r2) + sum(v * 2 + 1, tm + 1, tr, Math.max(tm, l), r, l2, r2);

    }

    int sumY(int v, int tl, int tr, int l, int r, int vx) {
        if (l > r)
            return 0;
        if (l == tl && r == tr)
            return t[vx][v];
        int tm = tl + (tr - tl) / 2;
        return sumY(v * 2, tl, tm, l, Math.min(tm, r), vx) + sumY(v * 2 + 1, tm + 1, tr, Math.max(tm, l), r, vx);

    }

    void update(int v, int tl, int tr, int l, int r, int val) {
        if (tl != tr) {
            int tm = tl + (tr - tl) / 2;
            if (l <= tm)
                update(v * 2, tl, tm, l, r, val);
            else
                update(v * 2 + 1, tm + 1, tr, l, r, val);
        }
        updateY(1, 0, M - 1, l, r, val, v, tl, tr);
    }

    public void updateY(int v, int tl, int tr, int l, int r, int val, int vx, int tlx, int trx) {
        if (tl == tr) {
            if (tlx == trx)
                t[vx][v] = val;
            else
                t[vx][v] = t[vx * 2][v] + t[vx * 2 + 1][v];

        } else {
            int tm = tl + (tr - tl) / 2;
            if (r <= tm)
                buildY(v * 2, tl, tm, vx, tlx, trx);
            else
                buildY(v * 2 + 1, tm + 1, tr, vx, tlx, trx);
            t[vx][v] = t[vx][v * 2] + t[vx][v * 2 + 1];
        }
    }
}
