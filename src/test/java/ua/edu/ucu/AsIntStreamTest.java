package ua.edu.ucu;

import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AsIntStreamTest {
    private IntStream intStream;
    private int[] array;

    @Before
    public void setUp() {
        array = new int[]{-5, 3, -1, 9, -11, 19, 68};
    }

    @Test
    public void testOf() {
        intStream = AsIntStream.of(array);
        int[] expected = new int[]{-5, 3, -1, 9, -11, 19, 68};
        assertArrayEquals(expected, intStream.toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmptyStream() {
        intStream = AsIntStream.of();
        intStream.average();
    }

    @Test
    public void testAverage() {
        intStream = AsIntStream.of(array);
        Double expected = 11.714285714285714;
        assertEquals(expected, intStream.average());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmptyStream() {
        intStream = AsIntStream.of();
        intStream.max();
    }

    @Test
    public void testMax() {
        intStream = AsIntStream.of(array);
        Integer expected = 68;
        assertEquals(expected, intStream.max());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinEmptyStream() {
        intStream = AsIntStream.of();
        intStream.min();
    }

    @Test
    public void testMin() {
        intStream = AsIntStream.of(array);
        Integer expected = -11;
        assertEquals(expected, intStream.min());
    }

    @Test
    public void count() {
        intStream = AsIntStream.of(array);
        long expected = 7;
        assertEquals(expected, intStream.count());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumEmptyStream() {
        intStream = AsIntStream.of();
        intStream.sum();
    }

    @Test
    public void testSum() {
        intStream = AsIntStream.of(array);
        Integer expected = 82;
        assertEquals(expected, intStream.sum());
    }

    @Test
    public void testForEach() {
        intStream = AsIntStream.of(array);
        StringBuilder str = new StringBuilder();
        intStream.forEach(str::append);
        String expected = "-53-19-111968";
        assertEquals(expected, str.toString());
    }

    @Test
    public void testReduce() {
        intStream = AsIntStream.of(array);
        int real = intStream.reduce(1, (mult, x) -> mult *= x);
        int expected = -1918620;
        assertEquals(expected, real);
    }

    @Test
    public void testToArray() {
        intStream = AsIntStream.of(array);
        int[] expected = {-5, 3, -1, 9, -11, 19, 68};
        assertArrayEquals(expected, intStream.toArray());
    }

    @Test
    public void testFilter() {
        intStream = AsIntStream.of(array);
        intStream = intStream.filter(x -> x > 0);
        int[] expected = new int[]{3, 9, 19, 68};
        assertArrayEquals(expected, intStream.toArray());
    }

    @Test
    public void testMap() {
        intStream = AsIntStream.of(array);
        intStream = intStream.map(x -> x * x);
        int[] expected = new int[]{25, 9, 1, 81, 121, 361, 4624};
        assertArrayEquals(expected, intStream.toArray());
    }

    @Test
    public void testFlatMap() {
        intStream = AsIntStream.of(array);
        intStream = intStream.flatMap(x -> AsIntStream.of(x - 1, x + 1));
        int[] expected = new int[]{-6, -4, 2, 4, -2, 0, 8, 10, -12, -10, 18, 20, 67, 69};
        assertArrayEquals(expected, intStream.toArray());
    }
}
