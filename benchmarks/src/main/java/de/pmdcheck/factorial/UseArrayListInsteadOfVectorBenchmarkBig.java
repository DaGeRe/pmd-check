package de.pmdcheck.factorial;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.Iterator;
import java.util.Vector;

import org.openjdk.jmh.annotations.Benchmark;


public class UseArrayListInsteadOfVectorBenchmarkBig {

   public static final int size = 100000;

   @Benchmark
   public int testGood() {
      final Collection<Integer> c1 = new ArrayList<>(size);
      fill(c1);
      return getSum(c1);
   }

   @Benchmark
   public int testBad() {
      final Collection<Integer> c1 = new Vector<>(size);
      fill(c1);
      return getSum(c1);
   }

   public void fill(final Collection<Integer> c1) {
      for (int i = 0; i < size; i++) {
         c1.add(i);
      }
   }

   public int getSum(final Collection<Integer> c1) {
      int sum = 0;
      for (final Integer i : c1) {
         sum += i;
      }
      return sum;
   }
}