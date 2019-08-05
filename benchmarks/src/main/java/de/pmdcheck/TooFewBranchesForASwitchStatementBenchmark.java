package de.pmdcheck;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;

public class TooFewBranchesForASwitchStatementBenchmark {
   
   private static final int size = 1000;
   
   @Benchmark
   public boolean testGood() {
      int sum = 0;
      for (int i = 0; i < size; i++) {
         int condition = new Random().nextInt(5);

         if (condition == 1) {
            sum++;
         }
      }

      return sum < 6;

   }

   @Benchmark
   public boolean testBad() {
      int sum = 0;
      for (int i = 0; i < size; i++) {
         int condition = new Random().nextInt(5);

         switch (condition) {
         case 1:
            sum++;
            break;
         default:
            break; // not enough for a 'switch' stmt, a simple 'if' stmt would have been more appropriate
         }
      }

      return sum < 6;
   }
}