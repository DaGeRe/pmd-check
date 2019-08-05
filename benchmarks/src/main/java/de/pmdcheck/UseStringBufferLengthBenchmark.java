package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

public class UseStringBufferLengthBenchmark {
   @Benchmark
   public int testGood() {
      StringBuffer sb = new StringBuffer("This is a test");
      sb.append("i = 100");

      return sb.length();
   }

   @Benchmark
   public int testBad() {
      StringBuffer sb = new StringBuffer("This is a test");
      sb.append("i = 100");

      return sb.toString().length();
   }
}