package de.pmdcheck.withoutjmh;

public class BenchmarkDumbAL {
   
   public static final int size = 100;
   
   public static void main(String[] args) {
      SimpleBenchmark.IntFunction bad = new SimpleBenchmark.IntFunction() {
         @Override
         public int getValue() {
            BenchmarkDumbAL benchmark = new BenchmarkDumbAL();
            return benchmark.benchmarkDumbAL();
         }
      };
      final int iterations = 10;
//      SimpleBenchmark.runBenchmark(iterations, 1000000, bad, "DumbAL");
      DumbAL myTest = new DumbAL(95);
      for (int i = 0; i < 1000; i++) {
         myTest.add(123);
      }
      SimpleBenchmark.runBenchmark(iterations, 1000000, bad, "DumbAL");
      System.out.println(myTest.get(15));
//      System.out.println(DumbAL.grow);
   }
   
   public int benchmarkDumbAL() {
      DumbAL c1 = new DumbAL(size);
      fill(c1);
      return getSum(c1);
   }


   public void fill(DumbAL c1) {
      for (int i = 0; i < size; i++) {
         c1.add(i);
      }
   }

   public int getSum(DumbAL c1) {
      int sum = 0;
      for (Integer i : c1) {
         sum += i;
      }
      return sum;
   }
}
