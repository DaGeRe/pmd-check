package de.pmdcheck.sizeable;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ShortInstantiationBenchmark {

   private static final int size = 2;
   
   private static final Random r = new Random();

   @Benchmark
   public long testGood() {
      short sum = 0;
      Short[][] shorts = new Short[size][256];
      for (int repetition = 0; repetition < size; repetition++) {
         for (int i = 0; i < 256; i++) {
            final short val = (short) i;
            shorts[repetition][i] = Short.valueOf(val);
         }
      }
      sum = getShortSum(sum, shorts);
      return sum;
   }

   @Benchmark
   public short testBad() {
      short sum = 0;
      Short[][] shorts = new Short[size][256];
      for (int repetition = 0; repetition < size; repetition++) {
         for (int i = 0; i < 256; i++) {
            final short val = (short) i;
            shorts[repetition][i] = new Short(val);
         }
      }
      sum = getShortSum(sum, shorts);
      return sum;
   }

   public short getShortSum(short sum, Short[][] shorts) {
      for (int repetition = 0; repetition < size; repetition++) {
         for (int i = 0; i < 256; i++) {
            sum += shorts[repetition][i];
         }
      }
      return sum;
   }
}