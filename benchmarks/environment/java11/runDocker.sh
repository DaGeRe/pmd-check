docker build -t "jdk11" .
echo "Mounting"$(pwd)"../../../"
ls $(pwd)
cp pom.xml ../../
git checkout -- ../../src/main/java/de/pmdcheck/AvoidFileStreamBenchmark.java
rm ../../src/main/java/de/pmdcheck/factorial/sevenSources/*
rm ../../src/main/java/de/pmdcheck/factorial/eightSources/* -r
rm ../../src/main/java/de/pmdcheck/factorial/UseArrayListInsteadOfVectorBenchmark7.java
rm ../../src/main/java/de/pmdcheck/factorial/UseArrayListInsteadOfVectorBenchmark8.java
rm ../../src/main/java/de/pmdcheck/withoutjmh/UseArrayListInsteadOfVectorBenchmark8.java
rm ../../src/main/java/de/pmdcheck/withoutjmh/UseArrayListInsteadOfVectorBenchmarkLocal.java
docker run --privileged -v $(pwd)"../../../":/home/validation_jmh -it jdk11
