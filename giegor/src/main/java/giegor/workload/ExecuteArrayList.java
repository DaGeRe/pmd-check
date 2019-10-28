package giegor.workload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;

public class ExecuteArrayList {

   public static void main(String[] args) throws IOException {
      List<Long> myList = new ArrayList<>();
      for (int j = 0; j < Parameters.otherLists; j++) {
         myList = new ArrayList<>();
         for (long i = 0; i < Parameters.otherListParts; i++) {
            myList.add(i);
         }
      }

      double[] values = new double[Parameters.iterations];
      for (int iteration = 0; iteration < Parameters.iterations; iteration++) {
         long start = System.nanoTime();
         WorkloadCreator.createWorkload();
         long duration = System.nanoTime() - start;
         double durationInSeconds = duration / 10E8;
         System.out.println("Duration " + iteration + ": " + durationInSeconds + " s");
         values[iteration] = durationInSeconds;
      }
      StatisticalSummary statistic = new DescriptiveStatistics(values);
      System.out.println(statistic.getMean() + " s +-" + statistic.getStandardDeviation());

      System.out.println(myList.get(15));
   }
}
