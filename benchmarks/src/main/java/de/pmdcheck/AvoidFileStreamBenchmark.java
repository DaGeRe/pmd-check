package de.pmdcheck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class AvoidFileStreamBenchmark {

   private static final String fileName = "testfile";

   @Setup(Level.Trial)
   public static void initFile() throws IOException {
      Path path = Paths.get(fileName);
      try (BufferedWriter newBufferedWriter = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
         newBufferedWriter.write("Test");
         newBufferedWriter.flush();
      }
   }

   @Benchmark
   public int testGood() throws IOException {
      int result = 0;
      try (InputStream is = Files.newInputStream(Paths.get(fileName))) {
         result += is.read();
      }
      try (OutputStream os = Files.newOutputStream(Paths.get(fileName))) {
         os.write(result);
      }
//      try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
//         result += br.read();
//      }
//      try (BufferedWriter wr = Files.newBufferedWriter(Paths.get(fileName), StandardCharsets.UTF_8)) {
//         wr.write(result);
//      }
      return result;
   }

   @Benchmark
   public int testBad() throws IOException {
      int result = 0;
      try (InputStream is = new FileInputStream(fileName)) {
         result += is.read();
      }
      try (OutputStream os = new FileOutputStream(fileName)) {
         os.write(result);
      }
//      try (FileReader br = new FileReader(fileName)) {
//         result += br.read();
//      }
//      try (FileWriter wr = new FileWriter(fileName)) {
//         wr.write(result);
//      }
      return result;
   }

}