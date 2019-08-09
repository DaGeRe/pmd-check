package de.pmdcheck.factorial;

import org.openjdk.jmh.annotations.Benchmark;

public class ConsecutiveLiteralSmall {

   @Benchmark
   public String testGood() {
      final StringBuilder buf = new StringBuilder(16);
      buf.append("Hello World"); // good
      return buf.toString();
   }

   @Benchmark
   public String testBad() {
      final StringBuilder buf = new StringBuilder(16);
      buf.append("Hello").append(" ").append("World"); // poor
      return buf.toString();
   }
}
