mvn -f .. clean package
java -jar ../target/jmh-pmd-benchmarks-1.0.jar -f 30 -wi 5 -i 5 -w 10 -r 10 de.pmdbench.factorial.VectorArrayListTweakedBenchmark | tee result_VectorArrayListTweakedBenchmark.txt

