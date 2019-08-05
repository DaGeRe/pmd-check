package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

public class InefficientStringBufferingBenchmark {
   @Benchmark
   public String testGood() {
      StringBuffer sb = new StringBuffer("tmp = ");
      sb.append(System.getProperty("java.io.tmpdir"));
      return sb.toString();
      
   }

   @Benchmark
   public String testBad() {
      StringBuffer sb = new StringBuffer("tmp = "+System.getProperty("java.io.tmpdir"));
      return sb.toString();
   }
}