import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Point> readFromStdin() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            float x = scanner.nextFloat();
            float y = scanner.nextFloat();
            points.add(new Point(x, y));
        }
        return points;
    }

    public static void main(String[] args) {
        List<Point> points = readFromStdin();
        Graph graph = new Graph();
        graph.buildGraph(points);

        TSP tsp = new TSP(graph);
        int[] tour = tsp.solve();
        for (int value : tour) {
            System.out.println(value);
        }
    }
}
