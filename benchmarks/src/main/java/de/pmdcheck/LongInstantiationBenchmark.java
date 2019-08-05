package de.pmdcheck;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class LongInstantiationBenchmark {

   private static final int size = 100;
   
   @Benchmark
   public long testGood() {
      long sum = 0;
      Long[][] longs = new Long[size][256];
      for (int repetition = 0; repetition < size; repetition++) {
         for (int i = 0; i < 256; i++) {
            longs[repetition][i] = Long.valueOf(i - 128);
         }
      }
      sum = getLongSum(sum, longs);
      return sum;
   }

   @Benchmark
   public long testBad() {
      long sum = 0;
      Long[][] longs = new Long[size][256];
      for (int repetition = 0; repetition < size; repetition++) {
         for (int i = 0; i < 256; i++) {
            longs[repetition][i] = new Long(i - 128);
         }
      }
      sum = getLongSum(sum, longs);
      return sum;
   }
   
   public long getLongSum(long sum, Long[][] longs) {
      for (int repetition = 0; repetition < longs.length; repetition++) {
         for (int i = 0; i < longs[repetition].length; i++) {
            sum += longs[repetition][i];
         }
      }
      return sum;
   }
}