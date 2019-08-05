package de.pmdcheck;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;

public class BooleanInstantiationBenchmark {
   
   private static final Random r = new Random();
   
   @Benchmark
   public boolean testGood() {
      int size = r.nextInt(1000);

      boolean allAnd = false;
      for (int i = size; i < size + 10; i++) {
         Boolean current = i % 2 == 0 ? Boolean.TRUE : Boolean.FALSE; 
         allAnd = allAnd & current;
      }
      return allAnd;
   }

   @Benchmark
   public boolean testBad() {
      int size = r.nextInt(1000);

      boolean allAnd = false;
      for (int i = size; i < size + 10; i++) {
         Boolean current = i % 2 == 0 ? new Boolean("true") : new Boolean("false"); 
         allAnd = allAnd & current;
      }
      return allAnd;
   }
}