package giegor.workload;

public class Parameters {

   public static int size;
   public static int ids;
   public static int iterations;
   public static int repetitions;
   public static int otherLists;
   public static long otherListParts;

   static {
      size = System.getenv("size") != null ? Integer.parseInt(System.getenv("size")) : 250;
      ids = System.getenv("ids") != null ? Integer.parseInt(System.getenv("ids")) : 30;
      iterations = System.getenv("iterations") != null ? Integer.parseInt(System.getenv("iterations")) : 10;
      repetitions = System.getenv("repetitions") != null ? Integer.parseInt(System.getenv("repetitions")) : 200000;
      otherLists = System.getenv("otherLists") != null ? Integer.parseInt(System.getenv("otherLists")) : 5;
      otherListParts = System.getenv("otherListParts") != null ? Integer.parseInt(System.getenv("otherListParts")) : 1000;
   }
}
