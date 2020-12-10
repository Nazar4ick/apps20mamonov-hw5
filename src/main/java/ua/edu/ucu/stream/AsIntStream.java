package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.BasicIterator;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.FlatMapIterator;
import ua.edu.ucu.iterators.MapIterator;

import java.util.Arrays;
import java.util.Iterator;

public class AsIntStream implements IntStream {
    private final Iterator<Integer> iterator;

    private AsIntStream(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    public static IntStream of(int... values) {
        int[] items = new int[values.length];
        System.arraycopy(values, 0, items, 0, values.length);
        return new AsIntStream(new BasicIterator(items));
    }

    @Override
    public Double average() {
        return ((double) sum() / (double) count());
    }

    @Override
    public Integer max() {
         if (count() == 0) {
             throw new IllegalArgumentException();
         }
         int max = Integer.MIN_VALUE;
         while (iterator.hasNext()) {
             int item = iterator.next();
             if (max < item) {
                 max = item;
             }
         }
         return max;
    }

    @Override
    public Integer min() {
        if (count() == 0) {
            throw new IllegalArgumentException();
        }
        int min = Integer.MAX_VALUE;
        while (iterator.hasNext()) {
            int item = iterator.next();
            if (min > item) {
                min = item;
            }
        }
        return min;
    }

    @Override
    public long count() {
         long length = 0;
         while (iterator.hasNext()) {
             length++;
             iterator.next();
         }
         return length;
    }

    @Override
    public Integer sum() {
        if (count() == 0) {
            throw new IllegalArgumentException();
        }
        int sum = 0;
        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(iterator, predicate));
    }


    @Override
    public void forEach(IntConsumer action) {
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(iterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        while (iterator.hasNext()) {
            identity = op.apply(identity, iterator.next());
        }
        return identity;
    }

    @Override
    public int[] toArray() {
        int[] items = new int[(int) count()];
        int index = 0;
        while (iterator.hasNext()) {
            items[index] = iterator.next();
            index += 1;
        }
        return items;
    }

    public Iterator<Integer> getIterator() {
        return iterator;
    }

    public static void main(String[] args) {
        IntStream intStream = AsIntStream.of(-5, 3, -1, 9, -11, 19, 68);
        System.out.println(intStream.sum());
        System.out.println(intStream.count());
        System.out.println((double)intStream.sum() / (double)intStream.count());
//        System.out.println(intStream.sum());
//        System.out.println(intStream.count());
//        System.out.println(intStream.average());
//        System.out.println(intStream.max());
//        System.out.println(intStream.min());
//        System.out.println(Arrays.toString(intStream.toArray()));
//        System.out.println(intStream.reduce(1, (mult, x) -> mult *= x));
//        intStream = intStream.flatMap(x -> AsIntStream.of(x - 1, x, x+1));
//        System.out.println(Arrays.toString(intStream.toArray()));
    }
}
