package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {
    private final Iterator<Integer> iterator;
    private final IntToIntStreamFunction streamFunction;
    private AsIntStream currentStream;

    public FlatMapIterator(Iterator<Integer> iterator, IntToIntStreamFunction streamFunction) {
        this.iterator = iterator;
        this.streamFunction = streamFunction;
    }

    @Override
    public boolean hasNext() {
        if (currentStream == null || !currentStream.getIterator().hasNext()) {
            currentStream = null;
            return iterator.hasNext();
        }
        return true;
    }

    @Override
    public Integer next() {
        if (currentStream == null) {
            currentStream = (AsIntStream) streamFunction.applyAsIntStream(iterator.next());
        }
        return currentStream.getIterator().next();
    }
}
