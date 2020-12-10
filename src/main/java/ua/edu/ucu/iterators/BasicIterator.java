package ua.edu.ucu.iterators;

import java.util.Iterator;

public class BasicIterator implements Iterator<Integer> {
    private final int[] items;
    private int current = 0;

    public BasicIterator(int[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        if (current >= items.length) {
            current = 0;
            return false;
        }
        return true;
    }

    @Override
    public Integer next() {
        int next = current;
        current += 1;
        return items[next];
    }
}
