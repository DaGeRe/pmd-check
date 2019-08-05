package de.pmdcheck.factorial;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.Iterator;
import java.util.Vector;

import org.openjdk.jmh.annotations.Benchmark;


public class UseArrayListInsteadOfVectorBenchmarkInit {

   public static final int size = 100;

   @Benchmark
   public int testGood() {
      final Collection<Integer> c1 = new ArrayList<>(size);
      return c1.size();
   }

   @Benchmark
   public int testBad() {
      final Collection<Integer> c1 = new Vector<>(size);
      return c1.size();
   }

}