package de.pmdcheck;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ConsecutiveAppendsShouldReuseBenchmark {

   private static final Random r = new Random();

   private String from = "Alex";
   private String to = "Readers";
   private String subject = "Benchmarking with JMH";

   private int size = 16;

   @Benchmark
   public String testGood() {
      return new StringBuilder(size).append("From").append(from).append("To").append(to).append("Subject")
            .append(subject).toString();
   }

   @Benchmark
   public String testBad() {
      StringBuilder builder = new StringBuilder(size);
      builder.append("From");
      builder.append(from);
      builder.append("To");
      builder.append(to);
      builder.append("Subject");
      builder.append(subject);
      return builder.toString();
   }
}