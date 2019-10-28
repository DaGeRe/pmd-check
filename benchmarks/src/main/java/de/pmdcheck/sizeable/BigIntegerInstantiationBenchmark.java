package de.pmdcheck.sizeable;

import java.math.BigInteger;

import org.openjdk.jmh.annotations.Benchmark;

public class BigIntegerInstantiationBenchmark {
   
   private static final int size = 2;
   
   private BigInteger calculate(BigInteger first, BigInteger second) {
      BigInteger sum = first;
      for (int i = 0; i < size; i++) {
         sum = sum.add(second);
      }
      return sum;
   }

   @Benchmark
   public BigInteger testGood() {
      BigInteger one = BigInteger.ONE;
      BigInteger zero = BigInteger.ZERO;

      return calculate(zero, one);
   }

   @Benchmark
   public BigInteger testBad() {
      BigInteger one = new BigInteger("1");
      BigInteger zero = new BigInteger("0");

      return calculate(zero, one);
   }
}