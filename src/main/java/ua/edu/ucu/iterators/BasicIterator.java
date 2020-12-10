package ua.edu.ucu.iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BasicIterator implements Iterator<Integer> {
    private final int[] items;
    private int current = 0;

    public BasicIterator(int[] items) {
        this.items = Arrays.copyOf(items, items.length);
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
    public Integer next() throws NoSuchElementException {
        int next = current;
        current += 1;
        return items[next];
    }
}
