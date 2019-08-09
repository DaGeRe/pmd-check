package de.pmdcheck.factorial;

import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ArrayCopyBenchmark {
   
   byte[] hello = new byte[5];
   byte[] world = new byte[5];
   
   {
      final Random random = new Random();
      random.nextBytes(hello);
      random.nextBytes(world);
   }
   
   @Benchmark
   public byte[] testGood() {
      final byte[] dest = new byte[16];
      
      final byte[] source = new byte[11];
      source[0] = hello[0];
      source[1] = hello[1];
      source[2] = hello[2];
      source[3] = hello[3];
      source[4] = hello[4];
      source[5] = ' ';
      source[6] = world[0];
      source[7] = world[1];
      source[8] = world[2];
      source[9] = world[3];
      source[10] = world[4];
      
      System.arraycopy(source, 0, dest, 0, 11);
      
      return dest;
   }

   @Benchmark
   public byte[] testBad() {
      final byte[] dest = new byte[16];
      
      final byte[] source1 = new byte[5];
      final byte[] source2 = new byte[1];
      final byte[] source3 = new byte[5];
      source1[0] = hello[0];
      source1[1] = hello[1];
      source1[2] = hello[2];
      source1[3] = hello[3];
      source1[4] = world[4];
      source2[0] = ' ';
      source3[0] = world[0];
      source3[1] = world[1];
      source3[2] = world[2];
      source3[3] = world[3];
      source3[4] = hello[4];
      
      System.arraycopy(source1, 0, dest, 0, 5);
      System.arraycopy(source2, 0, dest, 5, 1);
      System.arraycopy(source3, 0, dest, 6, 5);
      return dest;
   }
   
//   @Benchmark
//   public byte[] testBaseline() {
//      final byte[] dest = new byte[16];
//      return dest;
//   }
}
