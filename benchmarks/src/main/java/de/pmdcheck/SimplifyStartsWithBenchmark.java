package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

public class SimplifyStartsWithBenchmark {
   
   private static final int size = 1000;
   
   @Benchmark
   public int testGood() {
      int sum = 0;
      for (int i = 0; i < size; i++) {
         String startWith = "a) this is test " + i;
         String startWith2 = "b) this is test " + i;
         if (startWith.charAt(0) == 'a' && startWith2.charAt(0) == 'b') {
            sum++;
         }
      }
      return sum;
   }

   @Benchmark
   public int testBad() {
      int sum = 0;
      for (int i = 0; i < size; i++) {
         String startWith = "a) this is test " + i;
         String startWith2 = "b) this is test " + i;
         if (startWith.startsWith("a") && startWith2.startsWith("b")) {
            sum++;
         }
      }
      return sum;
   }
}