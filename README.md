# PMD Validation Benchmark Suite

This repository contains benchmarks and analysis utilities to validate Java performance antipatterns documented in PMD (https://pmd.github.io/latest/pmd_rules_java_performance.html). Currently, only two (AvoidFileStream and OptimizableToArrayCall) contain version information. Antipatterns at code level exist since underlying implementations, either libraries or the JVM itself, are non-optimal if used in a defined way. They contain a solution which should heal the antipattern, i.e. improve the performance for any workload. By this benchmark suite, it can be checked whether antipatterns are valid for certain Java versions. If an implementation of the solution of an antipattern is slower than the antipattern itself, than this is not an antipattern. Nevertheless, it still might be valid for a specific use case. If an implementation of the solution is faster than the antipattern, the antipattern still might be wrong, since there might be a workload where the antipattern does not hold. 

## Benchmark Execution

In order to execute the benchmarks, first install the version of Java you would like to use. If you want to use a Docker image, go to benchmarks/environment/java[6,7,8,11] and execute ./runDocker.sh.

Afterwards, the following commands can be used to execute tests:
- cd run && ./run.sh - Execute all tests
- cd runFactorial && .. - Run analysis of factors influencing the measurement, e.g. VectorArrayListSizeBenchmark in order to test when Vector becomes slower than ArrayList

## Benchmark Analysis

The benchmarks can be analyzed by de.peass.analysis.AnalyzeFiles. This reads the all files from a given result folder, which should be passed. It returns the content of a latex-printable table.

## PeASS Validation

PeASS, an tool for measuring the performance of unit tests in different Java versions, should be able to identify performance changes at code level. Therefore, it should be able to distinguish performance of an antipattern to that of the solution. If you would like to create a project that can be used to validate PeASS, use de.peass.generation.GenerateProject.
