package giegor;

public class OperationExecutionRecord {
   private int id;
   private String name;
   private long duration;

   public OperationExecutionRecord(int id, String name, long duration) {
      super();
      this.id = id;
      this.name = name;
      this.duration = duration;
   }

   public String getCSV() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(id);
      buffer.append(name);
      buffer.append(duration);
      return buffer.toString();
   }

   public int getId() {
      return id;
   }

   public long getDuration() {
      return duration;
   }

}
