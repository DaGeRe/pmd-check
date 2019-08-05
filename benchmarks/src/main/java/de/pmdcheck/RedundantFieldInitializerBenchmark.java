package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

public class RedundantFieldInitializerBenchmark {

   private static final int size = 1000;

   class Bad {
      boolean b = false; // examples of redundant initializers
      byte by = 0;
      short s = 0;
      char c = 0;
      int i = 0;
      long l = 0;
   }

   // Good Variant
   class Good {
      boolean b; // examples of redundant initializers
      byte by;
      short s;
      char c;
      int i;
      long l;
   }

   @Benchmark
   public String testGood() {
      StringBuilder all = new StringBuilder();
      for (int i = 0; i < size; i++) {
         Good thing = new Good();
         all.append(thing.b + " " + thing.by + " " + thing.s + " " + thing.i + " " + thing.l);
      }
      return all.toString();
   }

   @Benchmark
   public String testBad() {
      StringBuilder all = new StringBuilder();
      for (int i = 0; i < size; i++) {
         Bad thing = new Bad();
         all.append(thing.b + " " + thing.by + " " + thing.s + " " + thing.i + " " + thing.l);
      }
      return all.toString();
   }
}