// Time Complexity
// Constructor- 	O(alpha(N))
// Find- 	O(alpha(N))
// Union- 	O(alpha(N))
// Connected-    O(alpha(N))

// Note: N is the number of vertices in the graph. alphaa refers to the Inverse Ackermann function. 
//In practice, we assume it's a constant. In other words, O(α(N)) is regarded as O(1) on average.

//Explanation- For the union-find constructor, we need to create two arrays of size N each.
// When using the combination of union by rank and the path compression optimization,
// the find operation will take O(alpha(N)) time on average. 
// Since union and connected both make calls to find and all other operations require constant time, 
// union and connected functions will also take O(α(N)) time on average.


// Space Complexity-  O(N)
// Explanation- We need O(N) space to store the array of size N.


public class DisjointSet {

    private int[] rank; // saves height of each vertex
    private int[] root; // saves parent of each set

    public DisjointSet(int size) {
        rank = new int[size];
        root = new int[size];
        for (int i = 0; i < size; i++) {
            rank[i] = 1; // initially every vertex is a individual set hence height = 1
            root[i] = i; // initial every vertex is considered individual set hence parent is itself
        }
    }

    // finds parent of given vertex
    public int find(int x) {
        if (x == root[x])
            return x;
        return root[x] = find(root[x]);
    }
    
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootY] > rank[rootX]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX; // rootX preferred for tie breaker
                rank[rootX] += 1;
            }
        }
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}