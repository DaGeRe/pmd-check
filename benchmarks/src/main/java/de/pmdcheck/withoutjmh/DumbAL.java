package de.pmdcheck.withoutjmh;

import java.util.Arrays;
import java.util.Iterator;

public class DumbAL implements Iterable<Integer> {

   transient Integer[] elementData; // non-private to simplify nested class access

   private int size;

   public DumbAL() {
      this.elementData = new Integer[0];
   }

   public DumbAL(int initialCapacity) {
      if (initialCapacity > 0) {
         this.elementData = new Integer[initialCapacity];
      } else if (initialCapacity == 0) {
         this.elementData = new Integer[0];
      } else {
         throw new IllegalArgumentException("Illegal Capacity: " +
               initialCapacity);
      }
   }

   public void add(Integer e) {
      ensureCapacityInternal(size + 1);
      elementData[size++] = e;
   }
   
   private void ensureCapacityInternal(final int minCapacity) {
      final int calculateCapacity = minCapacity > 10 ? minCapacity : 10;
      if (minCapacity - elementData.length > 0) {
         final Integer[] grown = Arrays.copyOf(elementData, calculateCapacity);
         elementData = grown;
      }
   }

   public Integer get(int index) {
      return elementData[index];
   }

   @Override
   public Iterator<Integer> iterator() {
      Iterator<Integer> it = new Iterator<Integer>() {

         int current = 0;

         @Override
         public boolean hasNext() {
            return current < size;
         }

         @Override
         public Integer next() {
            Integer value = elementData[current];
            current++;
            return value;
         }
      };
      return it;
   }

}
