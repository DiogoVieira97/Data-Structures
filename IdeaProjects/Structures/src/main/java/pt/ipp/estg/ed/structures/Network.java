/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.structures;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.exceptions.UnknownPathException;
import main.java.pt.ipp.estg.ed.interfaces.NetworkADT;

/**
 *
 * @author xavie
 * @param <T>
 */
public class Network<T> extends MatrixGraph<T> implements NetworkADT<T> {

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The graph is empty");
        }

        int index1 = indexIs(vertex1);
        int index2 = indexIs(vertex2);
        if (index1 == -1 || index2 == -1) {
            throw new IllegalArgumentException("One or more of the vertices does not exist in the graph");
        }

        edges[index1][index2] = (int) weight;
    }

    @Override
    public ArrayUnorderedList<T> shortestPathWeight(T vertex1, T vertex2) throws EmptyCollectionException, UnknownPathException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The graph is empty");
        }

        int index1 = indexIs(vertex1);
        int index2 = indexIs(vertex2);
        if (index1 == -1 || index2 == -1) {
            throw new IllegalArgumentException("One or more of the vertices does not exist in the graph");
        }

        int[] predecessor = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        LinkedQueue<Integer> traversalOrder = new LinkedQueue<>();

        boolean[] visited = new boolean[numVertices];
        visited[index1] = true;
        pathWeight[index1] = 0;
        traversalOrder.enqueue(index1);

        while (!traversalOrder.isEmpty()) {
            int currentIndex = traversalOrder.dequeue();
            if (currentIndex == index2) {
                break;
            }

            for (int i = 0; i < numVertices; i++) {
                if (edges[currentIndex][i] != 0 && !visited[i]) {
                    pathWeight[i] = pathWeight[currentIndex] + edges[currentIndex][i];
                    predecessor[i] = currentIndex;
                    visited[i] = true;
                    traversalOrder.enqueue(i);
                }
            }
        }

        if (!visited[index2]) {
            throw new UnknownPathException("There is no path from " + vertex1 + " to " + vertex2);
        }

        ArrayUnorderedList<T> path = new ArrayUnorderedList<>();
        int current = index2;
        path.addToRear(vertices[current]);
        while (current != index1) {
            current = predecessor[current];
            path.addToRear(vertices[current]);
        }

        return path;
    }

}
