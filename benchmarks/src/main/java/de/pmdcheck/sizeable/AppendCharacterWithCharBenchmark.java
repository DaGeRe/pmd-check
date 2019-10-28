package de.pmdcheck.sizeable;

import org.openjdk.jmh.annotations.Benchmark;

public class AppendCharacterWithCharBenchmark {
   
   private static final int size = 2;

   @Benchmark
   public String testGood() {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < size; i++) {
         sb.append('a'); // use this instead
      }
      return sb.toString();
   }

   @Benchmark
   public String testBad() {
      StringBuffer sb = new StringBuffer(100);
      for (int i = 0; i < 100; i++) {
         sb.append("a"); // avoid this
      }
      return sb.toString();
   }
}