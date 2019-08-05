package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

public class UselessStringValueOfBenchmark {
   
   public static final int size = 100;
   
   @Benchmark
   public String testGood() {
      String all = "";
      for (int i = 0; i < size; i++) {
         String s;
         s = "a" + i;
         all += s;
      }
      return all;
   }

   @Benchmark
   public String testBad() {
      String all = "";
      for (int i = 0; i < size; i++) {
         String s;
         s = "a" + String.valueOf(i);
         all += s;
      }
      return all;
   }
}