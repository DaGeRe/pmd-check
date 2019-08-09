package de.pmdcheck.factorial;

import org.openjdk.jmh.annotations.Benchmark;

public class ConsecutiveLiteralInit {

   @Benchmark
   public String testGood() {
      final StringBuilder buf = new StringBuilder(16);
      return buf.toString();
   }

   @Benchmark
   public String testBad() {
      final StringBuilder buf = new StringBuilder(16);
      return buf.toString();
   }
}
