package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

public class UseIndexOfCharBenchmark {
   @Benchmark
   public int testGood() {
      int sumIndex = 0;
      for (String test : new String[] { "this", "is", "a", "test" }) {
         // int index = test.indexOf("i");
         int index = test.indexOf('i');

         sumIndex += index;
      }
      return sumIndex;
   }

   @Benchmark
   public int testBad() {
      int sumIndex = 0;
      for (String test : new String[] { "this", "is", "a", "test" }) {
         int index = test.indexOf("i");

         sumIndex += index;
      }
      return sumIndex;
   }
}