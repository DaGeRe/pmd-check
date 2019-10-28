package de.pmdcheck.sizeable;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class IntegerInstantiationBenchmark {

   private static final int size = 2;
   
   @Benchmark
   public int testGood() {
      int sum = 0;
      Integer[][] ints = new Integer[size][256];
      for (int repetition = 0; repetition < size; repetition++) {
         for (int i = 0; i < 256; i++) {
            ints[repetition][i] = Integer.valueOf(i - 128);
         }
      }
      sum = getIntSum(sum, ints);
      return sum;
   }

   @Benchmark
   public int testBad() {
      int sum = 0;
      Integer[][] ints = new Integer[size][256];
      for (int repetition = 0; repetition < size; repetition++) {
         for (int i = 0; i < 256; i++) {
            ints[repetition][i] = new Integer(i - 128);
         }
      }
      sum = getIntSum(sum, ints);
      return sum;
   }
   
   public int getIntSum(int sum, Integer[][] ints) {
      for (int repetition = 0; repetition < 100; repetition++) {
         for (int i = 0; i < 256; i++) {
            sum += ints[repetition][i];
         }
      }
      return sum;
   }
}