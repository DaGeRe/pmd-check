package de.pmdcheck.sizeable;

import org.openjdk.jmh.annotations.Benchmark;

public class StringInstantiationBenchmark {
   
   private static final int size = 2;
   
   @Benchmark
   public String testGood() {
      String all = "";
      for (int i = 0; i < size; i++) {

         String bar = "bar" + i;
         String foo = "foo" + i;

         all += foo + bar;
      }
      return all;
   }

   @Benchmark
   public String testBad() {
      String all = "";
      for (int i = 0; i < size; i++) {

         String bar = new String("bar" + i);
         String foo = new String("foo" + i);
         all += foo + bar;
      }

      return all;
   }
}