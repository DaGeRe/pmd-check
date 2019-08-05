package de.pmdcheck.factorial;

import org.openjdk.jmh.annotations.Benchmark;

public class ConsecutiveLiteralSizeBenchmark {

   static final int SIZE = Integer.parseInt(System.getenv("SIZE"));

   @Benchmark
   public String testGood() {
      StringBuilder buf = new StringBuilder(16 * SIZE);
      for (int i = 0; i < SIZE; i++) {
         buf.append("Hello World"); // good
         buf.append("hello");
      }

      return buf.toString();
   }

   @Benchmark
   public String testBad() {
      StringBuilder buf = new StringBuilder(16 * SIZE);
      for (int i = 0; i < SIZE; i++) {
         buf.append("Hello").append(" ").append("World"); // poor
         buf.append("h").append("e").append("l").append("l").append("o"); // poor
      }
      return buf.toString();
   }
}
