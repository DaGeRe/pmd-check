# PMD Validation Benchmark Suite

This repository contains benchmarks and analysis utilities to validate Java performance antipatterns documented in PMD (https://pmd.github.io/latest/pmd_rules_java_performance.html). Currently, only two (AvoidFileStream and OptimizableToArrayCall) contain version information. Antipatterns at code level exist since underlying implementations, either libraries or the JVM itself, are non-optimal regarding performance if used in a defined way. They contain a solution which should heal the antipattern, i.e. improve the performance for any workload. By this benchmark suite, it can be checked whether antipatterns are valid for certain Java versions. If an implementation of the solution of an antipattern is slower than the antipattern itself, than this is not an antipattern. Nevertheless, it still might be valid for some (may many) specific use cases. If an implementation of the solution is faster than the antipattern, the antipattern still might be wrong, since there might be a workload where the antipattern does not hold. 

## Benchmark Execution

In order to get reprodicubly the same environment, go to benchmarks/environment/java[6,7,8,11,12] and execute ./runDocker.sh. You could also install your JDK directly - in this case, you may need to delete parts of the source (e.g. delete AvoidFileStreamBenchmark.java, if you use Java 6, since it uses try-with-resources).

Afterwards, the following commands can be used to execute tests:
- cd run && ./run.sh - Execute all tests
- cd runFactorial && .. - Run analysis of factors influencing the measurement, e.g. UseArrayListInsteadOfVectorBenchmarkSeven in order to test whether Vector is also faster than ArrayList if the sources from 1.7 are used.

## Benchmark Analysis

The benchmarks can be analyzed by de.peass.analysis.AnalyzeFiles. This reads the all files from a given result folder, which should be passed. It returns the content of a latex-printable table.

## Peass Validation

Peass, an tool for measuring the performance of unit tests in different Java versions (https://github.com/DaGeRe/peass), should be able to identify performance changes at code level. Therefore, it should be able to distinguish performance of an antipattern to that of the solution. If you would like to create a project that can be used to validate Peass, use de.pmdcheck.peassgeneration.GenerateProject.
