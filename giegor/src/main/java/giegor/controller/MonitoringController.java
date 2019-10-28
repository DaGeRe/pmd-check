package giegor.controller;

import java.util.ArrayList;
import java.util.List;

import giegor.OperationExecutionRecord;
import giegor.workload.Parameters;

public class MonitoringController {

   private List<OperationExecutionRecord> records;
   private int id = 1;

   public long getValue() {
      long duration = 0;
      for (int i = 0; i < Parameters.size; i++) {
         duration += records.get(i).getDuration();
      }
      return (int) duration;
   }

   public void addRecord(String name, long duration) {
      final OperationExecutionRecord record = new OperationExecutionRecord(id, name, duration / 1000);
      records.add(record);
   }

   public void reset(int id) {
      this.id = id;
      records = new ArrayList<>(Parameters.size);
   }
}