/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.pt.ipp.estg.ed.interfaces;

import main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException;
import main.java.pt.ipp.estg.ed.exceptions.UnknownPathException;
import main.java.pt.ipp.estg.ed.structures.ArrayUnorderedList;

/**
 *
 * @author xavie
 * @param <T>
 */
public interface NetworkADT<T> extends GraphADT<T> {

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight the weight
     * @throws main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException
     */
    public void addEdge(T vertex1, T vertex2, double weight) throws EmptyCollectionException;

    /**
     * Returns the weight of the shortest path in this network.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the weight of the shortest path in this network
     * @throws main.java.pt.ipp.estg.ed.exceptions.EmptyCollectionException
     * @throws main.java.pt.ipp.estg.ed.exceptions.UnknownPathException
     */
    public ArrayUnorderedList<T> shortestPathWeight(T vertex1, T vertex2) throws EmptyCollectionException, UnknownPathException;
}
