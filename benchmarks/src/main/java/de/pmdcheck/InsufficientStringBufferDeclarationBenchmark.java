package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

public class InsufficientStringBufferDeclarationBenchmark {
   @Benchmark
   public String testGood() {
      StringBuffer content = new StringBuffer(67);
      content.append("This is a long string ");
      content.append("that will exceed the default 16 characters");
      return content.toString();
   }

   @Benchmark
   public String testBad() {
      StringBuffer content = new StringBuffer();
      content.append("This is a long string ");
      content.append("that will exceed the default 16 characters");
      return content.toString();
   }
}