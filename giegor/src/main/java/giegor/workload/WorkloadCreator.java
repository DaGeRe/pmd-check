package giegor.workload;

import java.io.IOException;

import giegor.controller.MonitoringController;
import giegor.controller.WriterController;

public class WorkloadCreator {

   public static void createWorkload() {
      try {
         WriterController writer = new WriterController();
         for (int i = 0; i < Parameters.repetitions * 1000; i++) {
            long durations[] = new long[Parameters.ids];
            for (int id = 0; id < Parameters.ids; id++) {
               final MonitoringController recordHandler = new MonitoringController();
               recordHandler.reset(id);
               for (long duration = 0; duration < Parameters.size; duration++) {
                  recordHandler.addRecord("Class#method", 1000 * duration);
               }
               durations[id] = recordHandler.getValue();
            }
            writer.addValues(durations);
         }
         writer.finishMonitoring();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (Throwable e) {
         e.printStackTrace();
      }
   }
}
