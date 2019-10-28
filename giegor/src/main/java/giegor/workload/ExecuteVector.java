package giegor.workload;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;

public class ExecuteVector {

   public static void main(String[] args) throws IOException {
      List<Long> myList = new Vector<>();
      for (int j = 0; j < Parameters.otherLists; j++) {
         myList = new Vector<>();
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
