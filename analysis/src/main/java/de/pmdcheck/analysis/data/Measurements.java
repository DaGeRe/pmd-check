package de.pmdcheck.analysis.data;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues;

public class Measurements {
   Map<String, Benchmark> measurements = new TreeMap<>();

   public void addMeasurement(final String name, final double mean, final double standarddeviation, final int count, final boolean isGood, final int version) {
      final StatisticalSummary summary = new StatisticalSummaryValues(mean, standarddeviation, count, Double.MAX_VALUE, 0, count * mean);

      Benchmark benchmark = measurements.get(name);
      if (benchmark == null) {
         benchmark = new Benchmark();
         measurements.put(name, benchmark);
      }
      benchmark.addMeasurement(summary, isGood, version);
   }

   public void printResult() {
      String interesting = "";
      int count = 0;
      for (final Map.Entry<String, Benchmark> entry : measurements.entrySet()) {
         if (entry.getValue().getResultLine().contains(" W ")) {
            interesting += entry.getKey() + " ";
            count++;
         }
      }
      System.out.println("Interesting benchmarks: (" + count + ")");
      System.out.println(interesting);
      
      Benchmark first = measurements.values().iterator().next();
      String header = "Benchmark & ";
      for (Integer version : first.versions.keySet()) {
         header += version + " & ";
      }
      header = header.substring(0, header.length() - 2);
      System.out.println(header + "\\\\ \\hline");

      for (final Map.Entry<String, Benchmark> entry : measurements.entrySet()) {
         if (!entry.getKey().equals("VectorArrayListTweakedBenchmark")) {
            System.out.print(entry.getKey() + " & ");
            System.out.println(entry.getValue().getResultLine() + "\\\\");
         }
      }
   }
}