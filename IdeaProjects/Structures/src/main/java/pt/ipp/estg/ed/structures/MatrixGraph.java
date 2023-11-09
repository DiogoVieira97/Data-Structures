/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.interfaces.GraphADT;

import java.util.Iterator;

/**
 * @param <T>
 * @author xavie
 */
public class MatrixGraph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 5;
    protected int numVertices;
    protected int maxVertices;
    protected T[] vertices;
    protected int[][] edges;
    protected boolean[] marks;  // marks[i] is mark for vertices[i]

    public MatrixGraph() {
        numVertices = 0;
        maxVertices = DEFAULT_CAPACITY;
        vertices = (T[]) new Object[DEFAULT_CAPACITY];
        marks = new boolean[DEFAULT_CAPACITY];
        edges = new int[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
    }

    public MatrixGraph(int maxV) {
        numVertices = 0;
        maxVertices = maxV;
        vertices = (T[]) new Object[maxV];
        marks = new boolean[maxV];
        edges = new int[maxV][maxV];
    }

    public int indexIs(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addVertex(T vertex) {
        if (numVertices == maxVertices) {
            throw new IllegalStateException("The graph is full");
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            edges[numVertices][i] = 0;
            edges[i][numVertices] = 0;
        }
        numVertices++;
    }

    @Override
    public void removeVertex(T vertex) {
        int index = indexIs(vertex);
        if (index == -1) {
            throw new IllegalArgumentException("The vertex does not exist in the graph");
        }

        // Shift all vertices after the removed vertex one position to the left
        for (int i = index; i < numVertices - 1; i++) {
            vertices[i] = vertices[i + 1];
        }
        vertices[numVertices - 1] = null;
        numVertices--;

        // Shift all the rows after the removed row one position up
        for (int i = index; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                edges[i][j] = edges[i + 1][j];
            }
        }

        // Shift all the columns after the removed column one position to the left
        for (int i = 0; i < numVertices; i++) {
            for (int j = index; j < numVertices; j++) {
                edges[i][j] = edges[i][j + 1];
            }
        }
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        int index1 = indexIs(vertex1);
        int index2 = indexIs(vertex2);
        if (index1 == -1 || index2 == -1) {
            throw new IllegalArgumentException("One or more of the vertices does not exist in the graph");
        }
        edges[index1][index2] = 1;
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        int index1 = indexIs(vertex1);
        int index2 = indexIs(vertex2);
        if (index1 == -1 || index2 == -1) {
            throw new IllegalArgumentException("One or more of the vertices does not exist in the graph");
        }
        edges[index1][index2] = 0;
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex) throws EmptyCollectionException {
        LinkedQueue<T> traversalOrder = new LinkedQueue<>();
        ArrayUnorderedList<T> visitedVertices = new ArrayUnorderedList<>();

        int startIndex = indexIs(startVertex);
        if (startIndex == -1) {
            throw new IllegalArgumentException("The start vertex does not exist in the graph");
        }

        boolean[] visited = new boolean[numVertices];
        visited[startIndex] = true;
        traversalOrder.enqueue(startVertex);
        visitedVertices.addToRear(startVertex);

        while (!traversalOrder.isEmpty()) {
            T currentVertex = traversalOrder.dequeue();
            int currentIndex = indexIs(currentVertex);

            for (int i = 0; i < numVertices; i++) {
                if (edges[currentIndex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    traversalOrder.enqueue(vertices[i]);
                    visitedVertices.addToRear(vertices[i]);
                }
            }
        }

        return visitedVertices.iterator();
    }

    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        LinkedStack<T> traversalOrder = new LinkedStack<>();
        ArrayUnorderedList<T> visitedVertices = new ArrayUnorderedList<>();

        int startIndex = indexIs(startVertex);
        if (startIndex == -1) {
            throw new IllegalArgumentException("The start vertex does not exist in the graph");
        }

        boolean[] visited = new boolean[numVertices];
        visited[startIndex] = true;
        traversalOrder.push(startVertex);
        visitedVertices.addToRear(startVertex);

        while (!traversalOrder.isEmpty()) {
            T currentVertex = traversalOrder.pop();
            int currentIndex = indexIs(currentVertex);

            for (int i = 0; i < numVertices; i++) {
                if (edges[currentIndex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    traversalOrder.push(vertices[i]);
                    visitedVertices.addToRear(vertices[i]);
                }
            }
        }

        return visitedVertices.iterator();
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) throws EmptyCollectionException {
        ArrayUnorderedList<T> path = new ArrayUnorderedList<>();

        int startIndex = indexIs(startVertex);
        int targetIndex = indexIs(targetVertex);
        if (startIndex == -1 || targetIndex == -1) {
            throw new IllegalArgumentException("One or more of the vertices does not exist in the graph");
        }

        int[] predecessor = new int[numVertices];
        LinkedQueue<Integer> traversalOrder = new LinkedQueue<>();

        boolean[] visited = new boolean[numVertices];
        visited[startIndex] = true;
        traversalOrder.enqueue(startIndex);

        while (!traversalOrder.isEmpty()) {
            int currentIndex = traversalOrder.dequeue();
            if (currentIndex == targetIndex) {
                break;
            }

            for (int i = 0; i < numVertices; i++) {
                if (edges[currentIndex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    predecessor[i] = currentIndex;
                    traversalOrder.enqueue(i);
                }
            }
        }

        // If the target vertex was not visited, there is no path to it
        if (!visited[targetIndex]) {
            return path.iterator();
        }

        // Trace the path from the target vertex to the start vertex
        LinkedStack<T> reversePath = new LinkedStack<>();
        int index = targetIndex;
        while (index != startIndex) {
            reversePath.push(vertices[index]);
            index = predecessor[index];
        }
        reversePath.push(vertices[startIndex]);

        // Reverse the path to get the correct order
        while (!reversePath.isEmpty()) {
            path.addToRear(reversePath.pop());
        }

        return path.iterator();
    }

    @Override
    public boolean isEmpty() {
        return numVertices == 0;
    }

    @Override
    public boolean isConnected() throws EmptyCollectionException {
        Iterator<T> it = iteratorBFS(vertices[0]);
        int count = 0;
        while (it.hasNext()) {
            it.next();
            count++;
        }
        return count == numVertices;
    }

    @Override
    public int size() {
        return numVertices;
    }

}
