import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Dasom Eom
 * @version 1.8
 */
public class GraphAlgorithms {
    /**
     * Perform breadth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * You may import/use {@code java.util.Queue}, {@code java.util.Set},
     * {@code java.util.Map}, {@code java.util.List}, and any classes
     * that implement the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                                         Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("null"
                    + " cannot be used as input parameters!");
        }
        Map<Vertex<T>, List<VertexDistancePair<T>>> adj
                = graph.getAdjacencyList();
        if (adj.get(start) == null) {
            throw new IllegalArgumentException("The starting "
                    + "vertex is not in the graph!");
        }
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(start);
        List<Vertex<T>> list = new ArrayList<>();
        list.add(start);
        Set<Vertex<T>> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            start = queue.remove();
            visited.add(start);
            for (int i = 0; i < adj.get(start).size(); i++) {
                if (!visited.contains(adj.get(start).get(i).getVertex())) {
                    queue.add(adj.get(start).get(i).getVertex());
                    list.add(adj.get(start).get(i).getVertex());
                    visited.add(adj.get(start).get(i).getVertex());
                }
            }
        }
        return list;
    }

    
    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("null "
                    + "cannot be used as input parameters!");
        } else if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("The starting "
                    + "vertex is not in the graph!");
        }
        Map<Vertex<T>, List<VertexDistance<T>>> adj = graph.getAdjList();
        List<Vertex<T>> result = new LinkedList<>();
        Set<Vertex<T>> visited = new HashSet<>();
        search(start, result, visited, adj);
        return result;
    }

    /**
     * Recursive helper method of depthFirstSearch method.
     *
     * @param <T> the generic typing of the data
     * @param current the current Vertex that need to be examined.
     * @param list the List of vertices in the order that you visited them
     * @param visited the temporary set to be used to store the visited vertices
     * @param adj the provided adjacency list.
     */
    private static <T> void search(Vertex<T> current, List<Vertex<T>> list,
                                   Set<Vertex<T>> visited, Map<Vertex<T>,
            List<VertexDistance<T>>> adj) {
        if (!visited.contains(current) && visited.size() < adj.size()) {
            visited.add(current);
            list.add(current);
            for (VertexDistance<T> vertex : adj.get(current)) {
                if (!list.contains(vertex.getVertex())) {
                    search(vertex.getVertex(), list, visited, adj);
                }
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check that not all vertices have been visited.
     * 2) Check that the PQ is not empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                      Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("input cannot be null!");
        } else if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("The starting "
                    + "vertex is not in the graph!");
        }
        Map<Vertex<T>, List<VertexDistance<T>>> adj = graph.getAdjList();
        Map<Vertex<T>, Integer> list = new HashMap<>();
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<VertexDistance<T>> queue = new PriorityQueue<>();

        for (Vertex<T> vertex:adj.keySet()) {
            if (vertex.equals(start)) {
                list.put(vertex, 0);
            } else {
                list.put(vertex, Integer.MAX_VALUE);
            }
        }
        queue.add(new VertexDistance<>(start, 0));
        while (!queue.isEmpty() && visited.size() < adj.size()) {
            VertexDistance<T> temp = queue.poll();
            if (!visited.contains(temp.getVertex())) {
                visited.add(temp.getVertex());
                list.put(temp.getVertex(), temp.getDistance());
                for (VertexDistance<T> vx : adj.get(temp.getVertex())) {
                    Vertex<T> vxtex = vx.getVertex();
                    int temD = temp.getDistance();
                    int vxD = vx.getDistance();
                    if (list.get(vxtex) > temD + vxD) {
                        list.put(vxtex, temD + vxD);
                        queue.add(new VertexDistance<>(vxtex, temD + vxD));
                    }
                }
            }
        }
        return list;
    }

    /**
     * Runs Kruskal's algorithm on the given graph and returns the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * Kruskal's will also require you to use a Disjoint Set which has been
     * provided for you. A Disjoint Set will keep track of which vertices are
     * connected given the edges in your current MST, allowing you to easily
     * figure out whether adding an edge will create a cycle. Refer
     * to the {@code DisjointSet} and {@code DisjointSetNode} classes that
     * have been provided to you for more information.
     *
     * You should NOT allow self-loops into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null
     * @param <T> the generic typing of the data
     * @param graph the graph we are applying Kruskals to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("input cannot be null!");
        }
        PriorityQueue<Edge<T>> queue = new PriorityQueue<>(
                graph.getEdges());
        DisjointSet<Vertex<T>> vertSet = new DisjointSet<>(
                graph.getAdjList().keySet());

        Set<Edge<T>> answer = new HashSet<>();
        while (!queue.isEmpty() && (answer.size() < 2 * (graph.getAdjList().keySet().size() - 1))) {
            Edge<T> edge = queue.poll();
            Vertex<T> u = edge.getU();
            Vertex<T> v = edge.getV();
            if (vertSet.find(u) != vertSet.find(v)) {
                answer.add(edge);
                answer.add(new Edge<>(v, u, edge.getWeight()));
                vertSet.union(u, v);
            }
        }

        if (answer.size() == 2 * (graph.getAdjList().keySet().size() - 1)) {
            return answer;
        } else {
            return null;
        }
    }
}