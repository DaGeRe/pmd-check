package de.pmdcheck.withoutjmh;

import java.util.Arrays;

class ExtendableIntArray {
   private Integer[] elementData;
   private int size;

   public ExtendableIntArray(int size) {
      elementData = new Integer[size];
   }

   public void add(Integer e) {
      if (size >= elementData.length) {
         final int newSize = elementData.length * 2;
         Integer[] copied = new Integer[newSize];
         System.arraycopy(elementData, 0, copied, 0, elementData.length);
         elementData = copied;
      }
      elementData[size++] = e;
   }

   public Integer get(int index) {
      return elementData[index];
   }
}

class ExtendableIntArray2 {
   private Integer[] elementData;
   private int size;

   public ExtendableIntArray2(int size) {
      elementData = new Integer[size];
   }

   public void add(Integer e) {
      if (size >= elementData.length) {
         final int newSize = elementData.length * 2;
         Integer[] copied = new Integer[newSize];
         System.arraycopy(elementData, 0, copied, 0, elementData.length);
         elementData = copied;
      }
      elementData[size++] = e;
   }

   public Integer get(int index) {
      return elementData[index];
   }
}

public class BenchmarkSimpleArrayExtension {

   public static final int size = 100;

   public static void main(String[] args) {
      SimpleBenchmark.IntFunction createObjects = new SimpleBenchmark.IntFunction() {
         @Override
         public int getValue() {
            // DumbAL al = new DumbAL();
            final ExtendableIntArray al = new ExtendableIntArray(size);
            for (int i = 0; i < size; i++) {
               al.add(i);
            }
            return al.get(58);
         }
      };

      ExtendableIntArray secondArray = new ExtendableIntArray(5);
      for (int i = 0; i < 800; i++) {
         secondArray.add(14);
      }
      SimpleBenchmark.runBenchmark(10, 1000000, createObjects, "SimplyCreate");
      System.out.println(secondArray.get(2));
   }
}
