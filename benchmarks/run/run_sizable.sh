echo "Version: "$JAVA_VERSION
cd ../src/main/java/de/pmdcheck/ 
./createSmallSize.sh
cd ../../../../../run/
mvn -f ../pom.xml clean package
for benchmarkFile in $(ls ../src/main/java/de/pmdcheck/sizeable/)
do
	benchmark="${benchmarkFile%.java}"
	echo $benchmark
	java -jar ../target/jmh-pmd-benchmarks-1.0.jar -f 30 -wi 5 -i 5 -w 10 -r 10 de.pmdcheck.sizeable.$benchmark 2>&1 | tee sizeable_"$benchmark"_"$JAVA_VERSION".txt
done
