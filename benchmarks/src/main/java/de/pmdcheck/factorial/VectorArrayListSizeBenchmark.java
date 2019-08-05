package de.pmdcheck.factorial;

public class VectorArrayListSizeBenchmark {

   

//   public static final int SIZE = Integer.parseInt(System.getenv("SIZE"));
//
//   @Benchmark
//   public int testGood() {
//      Collection<Integer> c1 = new ArrayList<Integer>(SIZE);
//      fill(c1);
//      return getSum(c1);
//   }
//
//   @Benchmark
//   public int testBad() {
//      Collection<Integer> c1 = new Vector<Integer>(SIZE);
//      fill(c1);
//      return getSum(c1);
//   }
//
//   public void fill(Collection<Integer> c1) {
//      for (int i = 0; i < SIZE; i++) {
//         c1.add(i);
//      }
//   }
//
//   public int getSum(Collection<Integer> c1) {
//      int sum = 0;
//      for (Integer i : c1) {
//         sum += i;
//      }
//      return sum;
//   }
}
