package de.pmdcheck.withoutjmh;

import org.openjdk.jmh.annotations.Benchmark;

import de.pmdcheck.factorial.eightSources.util.ArrayList;
import de.pmdcheck.factorial.eightSources.util.Collection;
import de.pmdcheck.factorial.eightSources.util.Vector;

public class UseArrayListInsteadOfVectorBenchmark8 {

   public static final int size = 100;

   public static void main(String[] args) {
      SimpleBenchmark.IntFunction good = new SimpleBenchmark.IntFunction() {
         @Override
         public int getValue() {
            UseArrayListInsteadOfVectorBenchmark benchmark = new UseArrayListInsteadOfVectorBenchmark();
            return benchmark.testGood();
         }
      };
      SimpleBenchmark.IntFunction bad = new SimpleBenchmark.IntFunction() {
         @Override
         public int getValue() {
            UseArrayListInsteadOfVectorBenchmark benchmark = new UseArrayListInsteadOfVectorBenchmark();
            return benchmark.testBad();
         }
      };
      SimpleBenchmark.runBenchmark(10, 1000000, good, "good");
      SimpleBenchmark.runBenchmark(10, 1000000, bad, "bad");
   }

   @Benchmark
   public int testGood() {
      Collection<Integer> c1 = new ArrayList<Integer>(size);
      fill(c1);
      return getSum(c1);
   }

   @Benchmark
   public int testBad() {
      Collection<Integer> c1 = new Vector<Integer>(size);
      fill(c1);
      return getSum(c1);
   }

   public void fill(Collection<Integer> c1) {
      for (int i = 0; i < size; i++) {
         c1.add(i);
      }
   }

   public int getSum(Collection<Integer> c1) {
      int sum = 0;
      for (Integer i : c1) {
         sum += i;
      }
      return sum;
   }
}