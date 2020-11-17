package examples.concurrency;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Consumer;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ForkJoin {
    static final String SENTENCE = "Bonjour Oussama Tahiri";
    public static void main(String[] args) {
        System.out.println("SideEffect parallel sum done is: " + sideEffectSum( 10_000_000L)  );

        //Fork-Join
        System.out.println("ForkJoin sum done is : " +  ForkJoinSumCalculator.forkJoinSum(10_000_000) );

        //Spliterator
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);

        System.out.println("Found " + countWords(stream) + " words");
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        //simuler le cas d'une mauvaise utilisation de la methode parallel avec une shared mutable variable
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }
}

class Accumulator {
    public long total = 0;
    public void add(long value) { total += value; }
}


class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start + length/2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
