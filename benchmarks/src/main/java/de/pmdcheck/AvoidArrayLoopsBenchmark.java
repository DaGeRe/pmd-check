package de.pmdcheck;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class AvoidArrayLoopsBenchmark {

   static final Random random = new Random();
   
   private static final int size = 1000;
   
   Object[] a = new Object[size];
   Object[] b = new Object[size];
   

   @Setup(Level.Invocation)
   public void init() {
      for (int i = 0; i < size; i++) {
         a[i] = new String("Test: " + i);
      }
   }

   @Benchmark
   public void testGood() {
      System.arraycopy(a, 0, b, 0, size);
   }

   @Benchmark
   public void testBad() {
      for (int i = 0; i < size; i++) {
         b[i] = a[i];
      }
   }

}