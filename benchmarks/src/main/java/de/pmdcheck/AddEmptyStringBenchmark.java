package de.pmdcheck;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class AddEmptyStringBenchmark {

   static final Random random = new Random();

   @Benchmark
   public String testGood() {
      int value = random.nextInt();
      String s = Integer.toString(value); // preferred approach
      return s;
   }

   @Benchmark
   public String testBad() {
      int value = random.nextInt();
      String s = "" + value; // inefficient
      return s;
   }
}