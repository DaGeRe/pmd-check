echo "Version: "$JAVA_VERSION
mvn -f ../pom.xml clean package
benchmark=$1
java -jar ../target/jmh-pmd-benchmarks-1.0.jar -prof perfasm "$benchmark" &> "$benchmark"_"$JAVA_VERSION".txt
