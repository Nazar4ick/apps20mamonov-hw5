package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterator implements Iterator<Integer> {
    private final IntUnaryOperator intUnaryOperator;
    private final Iterator<Integer> iterator;

    public MapIterator(Iterator<Integer> iterator, IntUnaryOperator intUnaryOperator) {
        this.iterator = iterator;
        this.intUnaryOperator = intUnaryOperator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Integer next() {
        int current = iterator.next();
        return intUnaryOperator.apply(current);
    }
}
