package de.pmdcheck.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import de.pmdcheck.analysis.data.Measurements;

public class AnalyzeFiles {
   
   String currentBenchmark;
   String method;
   int count;

	public static void main(final String[] args) throws FileNotFoundException, IOException {
		final File folder = new File(args[0]);

		final Measurements measurements = new Measurements();

		final AnalyzeFiles analyzer = new AnalyzeFiles();
		for (final File benchmarkFile : folder.listFiles()) {
         analyzer.readFile(measurements, benchmarkFile);
		}

		measurements.printResult();

	}

	private void readFile(final Measurements measurements, final File benchmarkFile) throws IOException, FileNotFoundException {
		try (BufferedReader reader = new BufferedReader(new FileReader(benchmarkFile))) {
			System.out.println("Reading: " + benchmarkFile);
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("# Benchmark:")) {
					final String measurementName = line.substring("# Benchmark:".length(), line.length()).trim();
					currentBenchmark = measurementName.substring(0, measurementName.lastIndexOf("."));
					currentBenchmark = currentBenchmark.substring(currentBenchmark.lastIndexOf('.')+1);
					method = measurementName.substring(measurementName.lastIndexOf(".") + 1);
				}
				
				if (line.startsWith("# Fork:")) {
				   final String countString = line.substring(line.lastIndexOf(' ')+1);
				   count = Integer.parseInt(countString);
				}

				if (line.startsWith("Result")) {
					readResult(measurements, benchmarkFile, reader, line);
				}
			}
		}
	}

	private void readResult(final Measurements measurements, final File benchmarkFile, final BufferedReader reader, final String line)
			throws IOException {
		final String measurementName = line.split(" ")[1].replaceAll("\"", "").replaceAll(":", "");
		if (!measurementName.contains("baseline")) {
			// final String benchmark = benchmarkFile.getName().substring(4, benchmarkFile.getName().lastIndexOf("_"));
			// if (benchmarkFile.getName().matches("run_[0-9]*.txt")) {
			//
			// } else {
			// }

			final String versionString = benchmarkFile.getName().substring(benchmarkFile.getName().lastIndexOf("_") + 1, benchmarkFile.getName().lastIndexOf("."));
			final int version = Integer.parseInt(versionString);
			boolean isGood;
			if (measurementName.contains(".")) {
				System.out.println("C: " + currentBenchmark + " " + method);
				isGood = method.equals("testGood");
			} else {
				System.out.println(method);
				isGood = method.equals("testGood");
			}

			reader.readLine(); // Skip Average line
			final String avgDeviationLine = reader.readLine();
			final String[] parts = avgDeviationLine.split(" ");
			final String meanString = parts[7].substring(0, parts[7].length() - 1);
			final String standarddeviationString = parts[parts.length - 1];
			System.out.println(meanString + " " + standarddeviationString);

			final double mean = Double.parseDouble(meanString);
			final double standarddeviation = Double.parseDouble(standarddeviationString);

			System.out.println(version);

			measurements.addMeasurement(currentBenchmark, mean, standarddeviation, count, isGood, version);
		}
	}
}
