package de.pmdcheck.factorial;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.Iterator;
import java.util.Vector;

import org.openjdk.jmh.annotations.Benchmark;


public class UseArrayListInsteadOfVectorBenchmarkFill {

   public static final int size = 100;

   @Benchmark
   public void testGood() {
      final Collection<Integer> c1 = new ArrayList<>(size);
      fill(c1);
   }

   @Benchmark
   public void testBad() {
      final Collection<Integer> c1 = new Vector<>(size);
      fill(c1);
   }

   public void fill(final Collection<Integer> c1) {
      for (int i = 0; i < size; i++) {
         c1.add(i);
      }
   }

}