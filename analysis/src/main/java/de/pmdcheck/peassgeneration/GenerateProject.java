package de.pmdcheck.peassgeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import de.dagere.peass.utils.StreamGobbler;

public class GenerateProject {
   public static void main(final String[] args) throws IOException, InterruptedException {
      final File goalFolder = new File("../validation_peass");
      final File packageFolder = initEmptyProject(goalFolder, "pom.xml");

      final File benchmarkFolder = new File("src/main/java/de/peass");

      init(goalFolder);
      createPeassableProject(packageFolder, benchmarkFolder, "testBad");
      createVersion(goalFolder, "Bad Version");

      createPeassableProject(packageFolder, benchmarkFolder, "testGood");
      createVersion(goalFolder, "Good Version");

   }

   public static File initEmptyProject(final File goalFolder, String originalPomLocation) throws IOException {
      if (goalFolder.exists()) {
         for (final File child : goalFolder.listFiles()) {
            FileUtils.deleteQuietly(child);
         }
      }
      goalFolder.mkdirs();
      URL pomResource = GenerateProject.class.getClassLoader().getResource(originalPomLocation);
      FileUtils.copyURLToFile(pomResource, new File(goalFolder, "pom.xml"));

      final File sourceFolder = new File(goalFolder, "src/test/java");
      sourceFolder.mkdirs();

      final File packageFolder = new File(sourceFolder, "de/peass");
      packageFolder.mkdirs();
      return packageFolder;
   }

   public static void createPeassableProject(final File packageFolder, final File benchmarkFolder, final String methodName) throws FileNotFoundException, IOException {
      for (final File benchmarkFile : benchmarkFolder.listFiles((FilenameFilter) new WildcardFileFilter("*.java"))) {
         final String clazzFileName = benchmarkFile.getName().replace("Benchmark", "Test");
         final String clazzName = clazzFileName.substring(0, clazzFileName.indexOf("."));
         final File testFile = new File(packageFolder, clazzFileName);

         new ClazzWriter(benchmarkFile, methodName).writeClazz(clazzName, testFile);
      }
   }

   public static void init(final File goalFolder) throws InterruptedException, IOException {
      final ProcessBuilder builder = new ProcessBuilder("git", "init");
      builder.directory(goalFolder);
      builder.start().waitFor();
   }

   public static void createVersion(final File goalFolder, final String commitText) throws InterruptedException, IOException {
      final ProcessBuilder builderAdd = new ProcessBuilder("git", "add", "-A");
      builderAdd.directory(goalFolder);
      builderAdd.start().waitFor();

      final ProcessBuilder builderCommit = new ProcessBuilder("git", "-c", "user.name='Anonym'",
            "-c", "user.email='anonym@generated.org'",
            "commit", "-m", commitText);
      builderCommit.directory(goalFolder);
      final Process process = builderCommit.start();
      StreamGobbler.getFullProcess(process, true);
      int returncode = process.waitFor();
      if (returncode != 0) {
         throw new RuntimeException("Unexpected return code of git commit: " + returncode);
      }
   }

}
