echo "Version: "$JAVA_VERSION
mvn -f ../pom.xml clean package
java -jar ../target/jmh-pmd-benchmarks-1.0.jar -f 30 -wi 5 -i 5 -w 10 -r 10 2>&1 | tee result_"$JAVA_VERSION".txt
