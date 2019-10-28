package giegor.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public class WriterController {

   private static final int MAX_QUEUE_SIZE = 1000;

   private Queue<long[]> queue;

   private BufferedWriter writer;
   private Thread current;
   private boolean isFinished = false;

   public WriterController() throws IOException {
      writer = new BufferedWriter(new FileWriter(new File("giegor-20191101-01.csv")));
      queue = new ArrayDeque<>(MAX_QUEUE_SIZE);
      current = new Thread(new Runnable() {

         @Override
         public void run() {
            try {
               while (!isFinished) {
                  long[] durations = queue.poll();
                  while (durations != null && !isFinished) {
                     writer.write(Arrays.toString(durations));
                     durations = queue.poll();
                  }
                  Thread.sleep(5);
               }
               writer.close();
            } catch (InterruptedException e) {
               e.printStackTrace();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      });
      current.start();
   }

   void addWriteRecord(int id, long value) {
      CompletableFuture.runAsync(() -> {
         try {
            writer.write(id + ";" + value);
         } catch (IOException e) {
            e.printStackTrace();
         }
      });
   }

   public void finishMonitoring() throws Throwable {
      isFinished = true;
      Thread.sleep(1);
   }

   public void addValues(long[] durations) {
      if (queue.size() > MAX_QUEUE_SIZE) {
         queue.clear();
      }
      queue.add(durations);
   }
}