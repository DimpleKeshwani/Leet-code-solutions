import java.util.*;

public class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build the adjacency list for the graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[]{v, w});
        }

        // dist[u][c] stores the minimum weight to reach node u 
        // with exactly c consecutive identical characters ending at u.
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // PriorityQueue stores arrays of format: {current_weight, node, consecutive_count}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Initialize state for node 0
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currWeight = current[0];
            int u = current[1];
            int count = current[2];

            if (currWeight > dist[u][count]) {
                continue;
            }

            if (u == n - 1) {
                return currWeight;
            }

            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];

                int nextCount = (labels.charAt(v) == labels.charAt(u)) ? count + 1 : 1;

                if (nextCount > k) {
                    continue;
                }

                if (currWeight + weight < dist[v][nextCount]) {
                    dist[v][nextCount] = currWeight + weight;
                    pq.offer(new int[]{dist[v][nextCount], v, nextCount});
                }
            }
        }

        return -1;
    }
}
