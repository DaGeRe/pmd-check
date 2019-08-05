package de.pmdcheck.peassgeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import de.peass.vcs.GitUtils;

public class GenerateProject {
   public static void main(String[] args) throws IOException, InterruptedException {
      File goalFolder = new File("../validation_peass");
      if (goalFolder.exists()) {
         for (File child : goalFolder.listFiles()) {
            FileUtils.deleteQuietly(child);
         }
      }
      goalFolder.mkdirs();
      File pomOriginal = new File("src/main/resources/pom.xml");
      FileUtils.copyFile(pomOriginal, new File(goalFolder, "pom.xml"));

      File sourceFolder = new File(goalFolder, "src/test/java");
      sourceFolder.mkdirs();

      File packageFolder = new File(sourceFolder, "de/peass");
      packageFolder.mkdirs();

      File benchmarkFolder = new File("src/main/java/de/peass");

      
      init(goalFolder);
      createPeassableProject(packageFolder, benchmarkFolder, "testBad");
      createVersion(goalFolder, "Bad Version");
      
      createPeassableProject(packageFolder, benchmarkFolder, "testGood");
      createVersion(goalFolder, "Good Version");
      
      
   }

   public static void createPeassableProject(File packageFolder, File benchmarkFolder, String methodName) throws FileNotFoundException, IOException {
      for (File benchmarkFile : benchmarkFolder.listFiles((FilenameFilter) new WildcardFileFilter("*.java"))) {
         String clazzFileName = benchmarkFile.getName().replace("Benchmark", "Test");
         String clazzName = clazzFileName.substring(0, clazzFileName.indexOf("."));
         File testFile = new File(packageFolder, clazzFileName);

         
         new ClazzWriter(benchmarkFile, methodName).writeClazz(clazzName, testFile);
      }
   }

   public static void init(File goalFolder) throws InterruptedException, IOException {
      final ProcessBuilder builder = new ProcessBuilder("git", "init");
      builder.directory(goalFolder);
      builder.start().waitFor();
   }

   public static void createVersion(File goalFolder, String commitText) throws InterruptedException, IOException {
      final ProcessBuilder builderAdd = new ProcessBuilder("git", "add", "-A");
      builderAdd.directory(goalFolder);
      builderAdd.start().waitFor();
      
      final ProcessBuilder builderCommit = new ProcessBuilder("git", "commit", "-m", commitText);
      builderCommit.directory(goalFolder);
      builderCommit.start().waitFor();
   }

   

}
