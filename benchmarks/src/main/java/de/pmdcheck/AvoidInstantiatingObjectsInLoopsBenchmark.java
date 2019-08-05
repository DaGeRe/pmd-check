package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

public class AvoidInstantiatingObjectsInLoopsBenchmark {
   class Foo {
      String myValue;
      String shortenedString;

      public Foo(String myValue) {
         super();
         this.myValue = myValue;
         shortenedString = myValue.substring(0, 10) + "_short";
      }
   }

   @Benchmark
   public String testGood() {
      Foo object = new Foo("This object should only be initialized once");
      return object.shortenedString;
   }

   @Benchmark
   public String testBad() {
      Foo object = null;
      for (int i = 0; i < 100; i++) {
         object = new Foo("This object should only be initialized once");
      }
      return object.shortenedString;
   }
}