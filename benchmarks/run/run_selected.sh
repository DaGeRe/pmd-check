echo "Version: "$JAVA_VERSION
mvn -f ../pom.xml clean package
profile=$1
for benchmark in ConsecutiveLiteralAppendsBenchmark RedundantFieldInitializerBenchmark ShortInstantiationBenchmark SimplifyStartsWithBenchmark StringInstantiationBenchmark TooFewBranchesForASwitchStatementBenchmark UseArrayListInsteadOfVectorBenchmark
#for benchmark in RedundantFieldInitializerBenchmark RedundantFieldInitializerBenchmark RedundantFieldInitializerBenchmark TooFewBranchesForASwitchStatementBenchmark
#for benchmark in AddEmptyStringBenchmark AppendCharacterWithCharBenchmark AvoidFileStreamBenchmark ByteInstantiationBenchmark ConsecutiveLiteralAppendsBenchmark IntegerInstantiationBenchmark RedundantFieldInitializerBenchmark ShortInstantiationBenchmark SimplifyStartsWithBenchmark TooFewBranchesForASwitchStatementBenchmark UseArrayListInsteadOfVectorBenchmark UseIndexOfCharBenchmark
#for benchmark in AddEmptyStringBenchmark AvoidFileStreamBenchmark ShortInstantiationBenchmark RedundantFieldInitializerBenchmark SimplifyStartsWithBenchmark
do
	echo $benchmark
	if [ $profile ]
	then
		./profile.sh $benchmark
	else
		java -jar ../target/jmh-pmd-benchmarks-1.0.jar -f 15 -wi 5 -i 5 -w 10 -r 10 de.pmdcheck.$benchmark 2>&1 | tee result_"$benchmark"_"$JAVA_VERSION".txt
		#java -jar target/jmh-pmd-benchmarks-1.0.jar -f 30 -wi 5 -i 5 -w 10 -r 10 de.peass.$benchmark 2>&1 | tee result_"$benchmark"_"$JAVA_VERSION".txt
	fi
done
