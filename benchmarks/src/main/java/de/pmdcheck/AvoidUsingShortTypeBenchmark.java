package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

public class AvoidUsingShortTypeBenchmark {
   
   private static final int size = 100;
   
   @Benchmark
   public int testGood() {
      int doNotUseShort = 2;
      int shouldNotBeUsed = 7;

      for (int i = 0; i < size; i++) {
         doNotUseShort += shouldNotBeUsed;
      }

      return doNotUseShort;
   }

   @Benchmark
   public short testBad() {
      short doNotUseShort = 2;
      short shouldNotBeUsed = 7;

      for (int i = 0; i < size; i++) {
         doNotUseShort += shouldNotBeUsed;
      }
      return doNotUseShort;
   }
}