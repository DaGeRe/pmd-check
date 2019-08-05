package de.pmdcheck;

import java.util.Arrays;
import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;

public class OptimizableToArrayCallBenchmark {
   @Benchmark
   public boolean testGood() {
      List<String> foos = Arrays.asList(new String[] { "This", "is", "a", "test", "with", "some", "elements" });
      String[] fooArray = foos.toArray(new String[0]);

      return fooArray[6].equals("elements");
   }

   @Benchmark
   public boolean testBad() {
      List<String> foos = Arrays.asList(new String[] { "This", "is", "a", "test", "with", "some", "elements" });
      String[] fooArray = foos.toArray(new String[foos.size()]);

      return fooArray[6].equals("elements");
   }
}