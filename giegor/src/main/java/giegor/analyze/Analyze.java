package giegor.analyze;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Analyze {
   
   Map<String, List<Double>> vectorValues = new TreeMap<>();
   Map<String, List<Double>> arraylistValues = new HashMap<>();
   
   public static void main(String[] args) throws IOException {

      Analyze analyzer = new Analyze();
      
      for (File potentialFolder : new File(".").listFiles()){
         if (potentialFolder.isDirectory()) {
            analyzer.analyzeFolder(potentialFolder);
         }
      }
      
      analyzer.analyzeFolder(new File("."));
      analyzer.printResults();
   }
   
   private void printResults() {
      for (Map.Entry<String, List<Double>> value : vectorValues.entrySet()) {
         List<Double> other = arraylistValues.get(value.getKey().replace("v", "al"));
         DescriptiveStatistics statVector = new DescriptiveStatistics(value.getValue().stream().mapToDouble(val -> val).toArray());
         DescriptiveStatistics statAL = new DescriptiveStatistics(other.stream().mapToDouble(val -> val).toArray());
         double relation = statVector.getMean() / statAL.getMean();
         relation = twoDigits(relation);
         System.out.println(value.getKey() + " " + twoDigits(statVector.getMean()) + " " + twoDigits(statAL.getMean()) + " " + relation);
      }
   }

   private void analyzeFolder(final File folder) throws IOException {
      final File[] allFiles = folder.listFiles(new FilenameFilter() {
         @Override
         public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
         }
      });

      for (File file : allFiles) {
         int prefixLength = file.getName().lastIndexOf("_");
         String prefix = file.getName().substring(0, prefixLength);
         if (prefix.startsWith("v")) {
            addValue(vectorValues, file, prefix);
         } else if (prefix.startsWith("al")) {
            addValue(arraylistValues, file, prefix);
         }

      }
   }

   private static double twoDigits(double relation) {
      return Math.floor(relation * 100) / 100;
   }

   private static void addValue(Map<String, List<Double>> values, File file, String prefix) throws IOException {
      List<Double> prefixValues = values.get(prefix);
      if (prefixValues == null) {
         prefixValues = new LinkedList<>();
         values.put(prefix, prefixValues);
      }
      String lastLine = getLastLine(file);
      if (lastLine.indexOf(" ") != -1) {
         double value = Double.parseDouble(lastLine.substring(0, lastLine.indexOf(" ")));
         prefixValues.add(value);
      }
   }

   private static String getLastLine(File file) throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
         String prevPrevLine = "";
         String prevLine = "";
         String lastLine = "";
         while ((lastLine = reader.readLine()) != null) {
            prevPrevLine = prevLine;
            prevLine = lastLine;
         }
         return prevPrevLine;
      }
   }
}
