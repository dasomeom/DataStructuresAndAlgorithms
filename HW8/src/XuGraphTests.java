import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Graph tests that include tests on every type of graph, exceptions,
 * and whether or not the graph has been modified after every method.
 *
 * @author Mary Xu
 * @version 1.0
 */
public class XuGraphTests {

    private Graph<Character> dirConnectedGraph;
    private Graph<Character> dirUnconnectedGraph;
    private Graph<Character> dirAcyclicGraph;
    private Graph<Character> dirCyclicGraph;
    private Graph<Character> dirSparseGraph;
    private Graph<Character> dirDenseGraph;

    private Graph<Character> undirConnectedGraph;
    private Graph<Character> undirUnconnectedGraph;
    private Graph<Character> undirAcyclicGraph;
    private Graph<Character> undirCyclicGraph;
    private Graph<Character> undirSparseGraph;
    private Graph<Character> undirDenseGraph;

    private Graph<Character> noPathGraph;
    private Graph<Character> monsterGraph;

    public static final int TIMEOUT = 200;

    @Before
    public void init() {
        dirConnectedGraph = XuCreateGraphs.createDirConnected();
        dirUnconnectedGraph = XuCreateGraphs.createDirUnconnected();
        dirAcyclicGraph = XuCreateGraphs.createDirAcyclic();
        dirCyclicGraph = XuCreateGraphs.createDirCyclic();
        dirSparseGraph = XuCreateGraphs.createDirSparse();
        dirDenseGraph = XuCreateGraphs.createDirDense();

        undirConnectedGraph = XuCreateGraphs.createUndirConnected();
        undirUnconnectedGraph = XuCreateGraphs.createUndirUnconnected();
        undirAcyclicGraph = XuCreateGraphs.createUndirAcyclic();
        undirCyclicGraph = XuCreateGraphs.createUndirCyclic();
        undirSparseGraph = XuCreateGraphs.createUndirSparse();
        undirDenseGraph = XuCreateGraphs.createUndirDense();

        noPathGraph = XuCreateGraphs.createNoPath();
        monsterGraph = XuCreateGraphs.createMonster();
    }

    //---------------------------------------------------------------
    //---------------------------------------------------------------
    //---------------------------------------------------------------
    //----------------------------- DFS -----------------------------
    //---------------------------------------------------------------
    //---------------------------------------------------------------
    //---------------------------------------------------------------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsDFSnullStart() {
        GraphAlgorithms.depthFirstSearch(null, monsterGraph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsDFSnullGraph() {
        GraphAlgorithms.depthFirstSearch(new Vertex<>('A'), null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsDFSbothNull() {
        GraphAlgorithms.depthFirstSearch(null, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsDFSnoStartExist() {
        GraphAlgorithms.depthFirstSearch(new Vertex<>('M'), monsterGraph);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSUndirConnected() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('B'), undirConnectedGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSUndirUnconnected() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), undirUnconnectedGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSUndirSparse() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), undirSparseGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSUndirDense() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), undirDenseGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));
        dfsExpected.add(new Vertex<>('C'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSUndirCyclic() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), undirCyclicGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSUndirAcyclic() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), undirAcyclicGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSDirConnected() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), dirConnectedGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));
        dfsExpected.add(new Vertex<>('C'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSDirUnconnected() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), dirUnconnectedGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSDirSparse() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), dirSparseGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSDirDense() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), dirDenseGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSDirCyclic() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), dirCyclicGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSDirAcyclic() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), dirAcyclicGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('E'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSNoPath() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), noPathGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('D'));

        assertEquals(dfsExpected, dfs);
    }

    @Test(timeout = TIMEOUT)
    public void testDFSMonster() {
        List<Vertex<Character>> dfs = GraphAlgorithms.depthFirstSearch(
                new Vertex<>('A'), monsterGraph);

        List<Vertex<Character>> dfsExpected = new LinkedList<>();
        dfsExpected.add(new Vertex<>('A'));
        dfsExpected.add(new Vertex<>('F'));
        dfsExpected.add(new Vertex<>('E'));
        dfsExpected.add(new Vertex<>('B'));
        dfsExpected.add(new Vertex<>('C'));
        dfsExpected.add(new Vertex<>('D'));
        dfsExpected.add(new Vertex<>('G'));
        dfsExpected.add(new Vertex<>('H'));

        assertEquals(dfsExpected, dfs);
    }

    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //----------------------------- Dijkstras -----------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsDijkstrasNullStart() {
        GraphAlgorithms.dijkstras(null, monsterGraph);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsDijkstrasNullGraph() {
        GraphAlgorithms.dijkstras(new Vertex<>('A'), null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsDijkstrasBothNull() {
        GraphAlgorithms.dijkstras(null, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsDijkstrasNoStartExist() {
        GraphAlgorithms.dijkstras(new Vertex<>('M'), monsterGraph);
    }

    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    //----------------------------- Kruskals -----------------------------
    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    //--------------------------------------------------------------------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalArgsKruskalsNullGRAPH() {
        GraphAlgorithms.kruskals(null);
    }

    @Test(timeout = TIMEOUT)
    public void testKruskalsUndirConnected() {
        Set<Edge<Character>> mst = GraphAlgorithms.kruskals(
                undirConnectedGraph);
        Set<Edge<Character>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('B'), 2));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 4));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 4));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 9));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 9));

        assertEquals(edges, mst);
    }

    @Test(timeout = TIMEOUT)
    public void testKruskalsUndirUnconnected() {
        Set<Edge<Character>> mst = GraphAlgorithms.kruskals(
                undirUnconnectedGraph);

        assertNull(mst);
    }

    @Test(timeout = TIMEOUT)
    public void testKruskalsUndirSparse() {
        Set<Edge<Character>> mst = GraphAlgorithms.kruskals(
                undirSparseGraph);
        Set<Edge<Character>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 10));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 10));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 5));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 2));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('E'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('C'), 1));

        assertEquals(edges, mst);
    }

    @Test(timeout = TIMEOUT)
    public void testKruskalsUndirDense() {
        Set<Edge<Character>> mst = GraphAlgorithms.kruskals(
                undirDenseGraph);
        Set<Edge<Character>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 1));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 1));
        edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 1));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('B'), 1));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 2));
        edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 2));
        edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('E'), 3));
        edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('C'), 3));

        assertEquals(edges, mst);
    }
}