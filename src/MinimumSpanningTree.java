import java.util.ArrayList;
import java.util.List;

public class MinimumSpanningTree {
    public List<Edge> prim(Graph graph) {
        List<Vertex> vertexList = graph.vertexList;
        List<Edge> edgeList = graph.edgeList;
        List<Vertex> newVertex = new ArrayList<>();
        List<Edge> result = new ArrayList<>();
        Vertex start = vertexList.get(0);
        newVertex.add(start);
        for (int i = 0; i < vertexList.size() - 1; i++) {
            Vertex temp = new Vertex(start.id);
            Edge tempEdge = new Edge(temp, temp, Integer.MAX_VALUE);
            for (Vertex v : newVertex) {
                for (Edge e : edgeList) {
                    if (e.start.id == v.id && !newVertex.contains(e.end) && e.weight < tempEdge.weight) {
                        temp = e.end;
                        tempEdge = e;
                    }
                }
            }
            newVertex.add(temp);
            result.add(tempEdge);
        }
        return result;
    }
}
