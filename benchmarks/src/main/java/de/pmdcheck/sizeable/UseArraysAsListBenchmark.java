package de.pmdcheck.sizeable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;

public class UseArraysAsListBenchmark {
   
   public static final int size = 2;
   
   @Benchmark
   public int testGood() {
      Integer[] ints = new Integer[size];
      for (int i = 0; i < size; i++) {
         ints[i] = i % 27;
      }

      List<Integer> l = Arrays.asList(ints);

      return l.size();
   }

   @Benchmark
   public int testBad() {
      Integer[] ints = new Integer[size];
      for (int i = 0; i < size; i++) {
         ints[i] = i % 27;
      }

      List<Integer> l = new ArrayList<Integer>(size);
      for (int i = 0; i < size; i++) {
         l.add(ints[i]);
      }

      return l.size();
   }
}