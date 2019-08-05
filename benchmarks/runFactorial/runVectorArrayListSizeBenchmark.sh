mvn -f .. clean package
for size in {1..5}
do
	export SIZE=$size
	java -jar ../target/jmh-pmd-benchmarks-1.0.jar -f 30 -wi 5 -i 5 -w 10 -r 10 de.pmdbench.factorial.VectorArrayListSizeBenchmark | tee result_VectorArrayListSizeBenchmark_$size.txt
done

