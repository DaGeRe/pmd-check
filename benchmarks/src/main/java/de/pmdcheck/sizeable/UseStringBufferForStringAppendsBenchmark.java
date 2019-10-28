package de.pmdcheck.sizeable;

import org.openjdk.jmh.annotations.Benchmark;

public class UseStringBufferForStringAppendsBenchmark {
   
   public static final int size = 2;
   
   @Benchmark
   public String testGood() {
      // String a = "foo";
      StringBuilder builder = new StringBuilder("foo");

      for (int i = 0; i < size; i++) {
         String myString = " bar" + i;
         // a += myString;
         builder.append(myString);
      }
      String a = builder.toString();

      return a.toString();
   }

   @Benchmark
   public String testBad() {
      String a = "foo";

      for (int i = 0; i < size; i++) {
         String myString = " bar" + i;
         a += myString;
      }

      return a;
   }
}