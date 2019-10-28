package de.pmdcheck.sizeable;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ByteInstantiationBenchmark {

   private static final int size = 2;
   
   @Benchmark
   public byte testGood() {
      byte sum = 0;
      Byte[][] ints = new Byte[size][256];
      for (int repetition = 0; repetition < size; repetition++) {
         for (int i = 0; i < 256; i++) {
            byte val = (byte) (i-128);
            ints[repetition][i] = Byte.valueOf(val);
         }
      }
      sum = getByteSum(sum, ints);
      return sum;
   }
   
   @Benchmark
   public byte testBad() {
      byte sum = 0;
      Byte[][] ints = new Byte[size][256];
      for (int repetition = 0; repetition < size; repetition++) {
         for (int i = 0; i < 256; i++) {
            byte val = (byte) (i-128);
            ints[repetition][i] = new Byte(val);
         }
      }
      sum = getByteSum(sum, ints);
      return sum;
   }

   public byte getByteSum(byte sum, Byte[][] ints) {
      for (int repetition = 0; repetition < 100; repetition++) {
         for (int i = 0; i < 256; i++) {
            sum += ints[repetition][i];
         }
      }
      return sum;
   }
}