import java.util.*;

public class Solution {
    // HLD variables
    private int[] parent, depth, heavy, head, pos;
    private int curPos;
    private List<Integer>[] adj;
    
    // Segment Tree variables
    private int[] tree;
    private int[] nodeValue; // Stores the current bitmask representation of character at node i

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        // Initialize adjacency list
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // Initialize HLD structures
        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        Arrays.fill(heavy, -1);
        curPos = 0;

        // DFS to calculate heavy edges, depths, and parents
        dfs(0, -1, 0);
        // Decompose into paths
        decompose(0, 0);

        // Initialize segment tree and initial state values
        tree = new int[4 * n];
        nodeValue = new int[n];
        
        // Build the segment tree with initial characters mapped to masks
        int[] initialArr = new int[n];
        for (int i = 0; i < n; i++) {
            int mask = 1 << (s.charAt(i) - 'a');
            nodeValue[i] = mask;
            initialArr[pos[i]] = mask;
        }
        buildSegmentTree(1, 0, n - 1, initialArr);

        // Process queries
        List<Boolean> ansList = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                
                int newMask = 1 << (c - 'a');
                nodeValue[u] = newMask;
                updateSegmentTree(1, 0, n - 1, pos[u], newMask);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                
                int pathMask = queryPath(u, v, n);
                // A bitmask forms a palindrome if it has <= 1 bits set (power of 2 check)
                boolean canFormPalindrome = (pathMask & (pathMask - 1)) == 0;
                ansList.add(canFormPalindrome);
            }
        }

        return ansList;
    }

    // --- Heavy-Light Decomposition Helper Methods ---
    private int dfs(int v, int p, int d) {
        parent[v] = p;
        depth[v] = d;
        int size = 1;
        int maxSubtreeSize = 0;
        
        for (int neighbor : adj[v]) {
            if (neighbor != p) {
                int subtreeSize = dfs(neighbor, v, d + 1);
                size += subtreeSize;
                if (subtreeSize > maxSubtreeSize) {
                    maxSubtreeSize = subtreeSize;
                    heavy[v] = neighbor;
                }
            }
        }
        return size;
    }

    private void decompose(int v, int h) {
        head[v] = h;
        pos[v] = curPos++;
        
        if (heavy[v] != -1) {
            decompose(heavy[v], h);
        }
        
        for (int neighbor : adj[v]) {
            if (neighbor != parent[v] && neighbor != heavy[v]) {
                decompose(neighbor, neighbor);
            }
        }
    }

    private int queryPath(int u, int v, int n) {
        int resMask = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] > depth[head[v]]) {
                int t = u; u = v; v = t;
            }
            resMask ^= querySegmentTree(1, 0, n - 1, pos[head[v]], pos[v]);
            v = parent[head[v]];
        }
        if (depth[u] > depth[v]) {
            int t = u; u = v; v = t;
        }
        resMask ^= querySegmentTree(1, 0, n - 1, pos[u], pos[v]);
        return resMask;
    }

    // --- Segment Tree Helper Methods ---
    private void buildSegmentTree(int node, int start, int end, int[] arr) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = start + (end - start) / 2;
        buildSegmentTree(2 * node, start, mid, arr);
        buildSegmentTree(2 * node + 1, mid + 1, end, arr);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private void updateSegmentTree(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            updateSegmentTree(2 * node, start, mid, idx, val);
        } else {
            updateSegmentTree(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int querySegmentTree(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        int p1 = querySegmentTree(2 * node, start, mid, l, r);
        int p2 = querySegmentTree(2 * node + 1, mid + 1, end, l, r);
        return p1 ^ p2;
    }
}
