package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

public class UnnecessaryWrapperObjectCreationBenchmark {
   @Benchmark
   public int testGood() {
      int equal = 0;
      for (String s : new String[] { "1", "123", "12123" }) {
         int i, i2;
         String s3;

         i = Integer.parseInt(s); // this is better
         i2 = i; // this is better
         s3 = Integer.toString(i2); // this is better
         if (i == i2)
            equal++;
         if (s3 == s)
            equal++;
      }
      return equal;
   }

   @Benchmark
   public int testBad() {
      int equal = 0;
      for (String s : new String[] { "1", "123", "12123" }) {
         int i, i2;
         String s3;

         i = Integer.valueOf(s).intValue(); // this wastes an object
         i2 = Integer.valueOf(i).intValue(); // this wastes an object
         s3 = Integer.valueOf(i2).toString(); // this wastes an object

         if (i == i2)
            equal++;
         if (s3 == s)
            equal++;
      }
      return equal;
   }
}