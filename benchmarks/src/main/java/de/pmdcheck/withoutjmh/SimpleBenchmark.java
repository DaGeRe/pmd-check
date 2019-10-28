package de.pmdcheck.withoutjmh;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;

public class SimpleBenchmark {
   
   static interface IntFunction {
      int getValue();
   }
   
   public static void runBenchmark(final int iterations, final int repetitions, IntFunction good, String name) {
      System.gc();
      double[] values = new double[iterations];
      for (int iteration = 0; iteration < iterations; iteration++) {
         long start = System.nanoTime();
         int sum = 0;
         for (int i = 0; i < repetitions; i++) {
            sum += good.getValue();
         }
         System.out.println(sum);
         long duration = System.nanoTime() - start;
         double durationInSeconds = duration / 10E8;
         double operationsPerSecond = repetitions / durationInSeconds;
         System.out.println(name + " Score " + iteration + ": " + operationsPerSecond + " ops/s");
         values[iteration] = operationsPerSecond;
      }
      StatisticalSummary statistic = new DescriptiveStatistics(values);
      System.out.println(statistic.getMean() + " ops/s +-" + statistic.getStandardDeviation());
   }
}
