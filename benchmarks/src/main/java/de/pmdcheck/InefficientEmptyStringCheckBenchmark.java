package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

public class InefficientEmptyStringCheckBenchmark {
   @Benchmark
   public boolean testGood() {
      boolean hasEmptyString = false;
      for (String test : new String[] { "This is a string", "notEmpty", "  Empty  ", "  ", "" }) {
         hasEmptyString &= checkTrimEmpty(test);
      }

      return hasEmptyString;
   }

   private boolean checkTrimEmpty(String str) {
      for (int i = 0; i < str.length(); i++) {
         if (!Character.isWhitespace(str.charAt(i))) {
            return false;
         }
      }
      return true;
   }

   @Benchmark
   public boolean testBad() {
      boolean hasEmptyString = false;
      for (String test : new String[] { "This is a string", "notEmpty", "  Empty  ", "  ", "" }) {
         hasEmptyString &= (test.trim().length() == 0);
      }
      return hasEmptyString;
   }
}