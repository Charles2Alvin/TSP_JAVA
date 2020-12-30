public class TSP {
    Graph graph;
    int[][] adjMatrix;

    public TSP(Graph graph) {
        this.graph = graph;
        this.adjMatrix = graph.adjMatrix;
    }

    public int[] nearestNeighbourTour(int[][] matrix) {
        int N = matrix[0].length;
        // Start in an arbitrary city
        int index = (int) (Math.random() * N);
        int[] tour = new int[N];
        boolean[] used = new boolean[N];
        tour[0] = index;
        used[index] = true;
        for (int i = 1; i < N; i++) {
            int best = -1;
            for (int j = 0; j < N; j++) {
                if (!used[j] && (best == - 1 || matrix[tour[i - 1]][j] < matrix[tour[i - 1]][best])) {
                    best = j;
                }
            }
            tour[i] = best;
            used[best] = true;
        }

        return tour;
    }

    public int[] twoOpt(int[] tour) {
//        System.out.println("Before two opt: " + costOfTour(tour));
        int n = tour.length;
        for (int k = 0; k < 20; k++) {
            int maxGap = Integer.MIN_VALUE;
            int p = 0, q = 0;
            for (int i = 0; i < n - 2; i++) {
                for (int j = i + 1; j < n - 1; j++) {
                    int t1 = tour[i];
                    int t2 = tour[i + 1];
                    int t3 = tour[j];
                    int t4 = tour[j + 1];
                    int oldCost = adjMatrix[t1][t2] + adjMatrix[t3][t4];
                    int newCost = adjMatrix[t1][t3] + adjMatrix[t2][t4];
                    int gap = oldCost - newCost;
                    if (gap > 0 && gap > maxGap) {
                        maxGap = gap;
                        p = i + 1;
                        q = j;
                    }
                }
            }
            reverseSubsequence(tour, p, q);
//            System.out.printf("k = %s, newCost = %s\n", k, costOfTour(tour));
        }

        return tour;
    }

    public int[] threeOpt(int[] tour) {
        int n = tour.length;
//        for (int i = 0; i < n; i++) {
//
//        }
        return tour;
    }

    public static void reverseSubsequence(int[] tour, int p, int q) {
        int n = tour.length;
        if (p < 0 || q < 0 || p >= n || q >= n) {
            throw new RuntimeException("Illegal parameters");
        }
        for (int i = p, j = q; i <= j; i++, j--) {
            int temp = tour[i];
            tour[i] = tour[j];
            tour[j] = temp;
        }
    }

    public int costOfTour(int[] tour) {
        int sum = 0;
        int n = tour.length;
        for (int i = 0; i < n - 1; i++) {
            sum += adjMatrix[tour[i]][tour[i + 1]];
        }
        sum += adjMatrix[tour[0]][tour[n - 1]];

        return sum;
    }

    public int[] solve() {
        int[] tour = nearestNeighbourTour(graph.adjMatrix);
        int oldCost = costOfTour(tour);
        tour = twoOpt(tour);
        int newCost = costOfTour(tour);
//        System.out.println(oldCost + ", " + newCost);

        return tour;
    }
}
