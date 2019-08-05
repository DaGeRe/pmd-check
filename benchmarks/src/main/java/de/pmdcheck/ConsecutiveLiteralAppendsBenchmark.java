package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

public class ConsecutiveLiteralAppendsBenchmark {

   @Benchmark
   public String testGood() {
      final StringBuilder buf = new StringBuilder(16);
      buf.append("Hello World"); // good
      buf.append("hello");
      return buf.toString();
   }

   @Benchmark
   public String testBad() {
      final StringBuilder buf = new StringBuilder(16);
      buf.append("Hello").append(" ").append("World"); // poor
      buf.append("h").append("e").append("l").append("l").append("o"); // poor
      return buf.toString();
   }
   
   public static void main(final String[] args) {
      new ConsecutiveLiteralAppendsBenchmark().testGood();
   }
}