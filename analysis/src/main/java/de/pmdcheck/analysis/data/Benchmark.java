package de.pmdcheck.analysis.data;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.inference.TestUtils;

public class Benchmark {
   Map<Integer, Measurement> versions = new TreeMap<>();

   public void addMeasurement(final StatisticalSummary summary, final boolean isGood, final int version) {
      Measurement measurement = versions.get(version);
      if (measurement == null) {
         measurement = new Measurement();
         versions.put(version, measurement);
      }
      if (isGood) {
         measurement.good = summary;
      } else {
         measurement.bad = summary;
      }
   }

   public String getResultLine() {
      String result = "";
      for (final Map.Entry<Integer, Measurement> entry : versions.entrySet()) {
         final double relativeDiff = Math.abs(1 - (entry.getValue().bad.getMean() / entry.getValue().good.getMean()));
         final boolean change = TestUtils.tTest(entry.getValue().bad, entry.getValue().good, 0.001);
         // System.out.println(entry.getValue().bad.getMean() + " " + entry.getValue().good.getMean());
         // System.out.println(TestUtils.t(entry.getValue().bad, entry.getValue().good));
         // System.out.print(entry.getKey());
         // System.out.println(relativeDiff);
         if (change) {
            if (relativeDiff < 0.01) {
               result += "(";
            }
            if (entry.getValue().bad.getMean() > entry.getValue().good.getMean()) {
               result += "W";
               System.out.println(entry.getKey() + " " + entry.getValue().bad.getMean() 
                     + " " + entry.getValue().good.getMean() + " " 
                     + TestUtils.t(entry.getValue().bad, entry.getValue().good) + " " 
                     + relativeDiff);
            } else {
               result += "C";
            }
            if (relativeDiff < 0.01) {
               result += ")";
            }
         } else {
            result += "0";
         }
         result += " & ";
      }
      return result.substring(0, result.length() - 2);
   }
}