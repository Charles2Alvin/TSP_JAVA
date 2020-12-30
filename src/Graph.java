import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Point {
    float x, y;
    Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

class Vertex {
    int id;

    public Vertex(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class Edge {
    Vertex start;
    Vertex end;
    int weight;

    public Edge(Vertex start, Vertex end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return start.id + "-->" + end.id + ", w = " + weight + "";
    }
}

public class Graph {
    int N;                  // Number of vertices
    int[][] adjMatrix;      // Adjacency matrix
    List<Vertex> vertexList = new ArrayList<>();
    List<Edge> edgeList = new ArrayList<>();
    public static final int INF = Integer.MAX_VALUE;

    public static Graph getTestGraph() {
        Graph graph = new Graph();
        int n = 7;
        int[][] matrix = {
                {INF, 7, INF, 5, INF, INF, INF},
                {7, INF, 8, 9, 7, INF, INF},
                {INF, 8, INF, INF, 5, INF, INF},
                {5, 9, INF, INF, 15, 6, INF},
                {INF, 7, 5, 15, INF, 8, 9},
                {INF, INF, INF, 6, 8, INF, 11},
                {INF, INF, INF, INF, 9, 11, INF}

        };
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vertices.add(new Vertex(i));
        }
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] != INF) {
                    edges.add(new Edge(vertices.get(i), vertices.get(j), matrix[i][j]));
                    edges.add(new Edge(vertices.get(j), vertices.get(i), matrix[i][j]));
                }
            }
        }
        graph.N = n;
        graph.vertexList = vertices;
        graph.edgeList = edges;
        graph.adjMatrix = matrix;

        return graph;
    }

    public static int dist(float x1, float y1, float x2, float y2) {
        return (int) Math.round(Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
    }

    public void buildGraph(List<Point> points) {
        /* Initialization */
        N = points.size();
        for (int i = 0; i < N; i++) {
            vertexList.add(new Vertex(i));
        }
        adjMatrix = new int[N][];
        for (int i = 0; i < N; i++) {
            adjMatrix[i] = new int[N];
            adjMatrix[i][i] = Integer.MAX_VALUE;
        }
        /* Compute the edge weights */
        for (int i = 0; i < N; i++) {
            adjMatrix[i][i] = INF;
            for (int j = i + 1; j < N; j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                int distance = dist(p1.x, p1.y, p2.x, p2.y);
                adjMatrix[i][j] = distance;
                adjMatrix[j][i] = distance;
                edgeList.add(new Edge(vertexList.get(i), vertexList.get(j), distance));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("The adjacency matrix of the graph:\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                String value = adjMatrix[i][j] == INF ? "INF" : String.valueOf(adjMatrix[i][j]);
                builder.append(value).append("\t");
            }
            builder.append("\n");
        }
        builder.append("\nThe edges of the graph:\n");
        for (Edge edge : edgeList) {
            String weight = edge.weight == INF ? "INF" : String.valueOf(edge.weight);
            builder.append(edge.start.id).append(" --> ").append(edge.end.id).append(", w = ").append(weight);
            builder.append("\n");
        }
        return builder.toString();
    }
}
