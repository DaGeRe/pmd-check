package de.pmdcheck;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class UseArrayListInsteadOfVectorBenchmark {

   public static final int size = 100;

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