package de.pmdcheck.withoutjmh;

import org.openjdk.jmh.annotations.Benchmark;

import de.pmdcheck.factorial.eightSources.util.ArrayListLocal;
import de.pmdcheck.factorial.eightSources.util.Collection;
import de.pmdcheck.factorial.eightSources.util.Vector;

public class UseArrayListInsteadOfVectorBenchmarkLocal {

   public static final int size = 120;

   public static void main(String[] args) {

      SimpleBenchmark.IntFunction good = new SimpleBenchmark.IntFunction() {
         @Override
         public int getValue() {
            UseArrayListInsteadOfVectorBenchmarkLocal benchmark = new UseArrayListInsteadOfVectorBenchmarkLocal();
            return benchmark.testGood();
         }
      };
      SimpleBenchmark.IntFunction bad = new SimpleBenchmark.IntFunction() {
         @Override
         public int getValue() {
            UseArrayListInsteadOfVectorBenchmarkLocal benchmark = new UseArrayListInsteadOfVectorBenchmarkLocal();
            return benchmark.testBad();
         }
      };
      final int iterations = 10;
//      SimpleBenchmark.runBenchmark(iterations, 1000000, good, "ArrayList");
      Vector<Integer> myTest = new Vector<>(3);
      for (int i = 0; i < 1000; i++) {
         myTest.add(123);
      }
      SimpleBenchmark.runBenchmark(iterations, 1000000, bad, "Vector");
      System.out.println(myTest.get(15));

      // ArrayListLocal<Integer> myTest = new ArrayListLocal<>();
      // for (int i = 0; i < 1000; i++) {
      // myTest.add(123);
      // }
      SimpleBenchmark.runBenchmark(iterations, 1000000, good, "ArrayListAfterCreatingAnotherArrayList");
      // System.out.println(myTest.get(15));
   }

   public int testGood() {
      Collection<Integer> c1 = new ArrayListLocal<Integer>(size);
      fill(c1);
      return getSum(c1);
   }

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