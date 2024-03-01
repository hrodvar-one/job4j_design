package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int numLists = nodes.size();
        int index = 0;
        while (source.hasNext()) {
            Integer data = source.next();
            ArrayList<Integer> targetArray = nodes.get(index);
            targetArray.add(data);
            index = (index + 1) % numLists;
        }
    }
}
