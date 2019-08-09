docker build -t "jdk11" .
echo "Mounting"$(pwd)"../../../"
ls $(pwd)
cp pom.xml ../../
git checkout -- ../../src/main/java/de/pmdcheck/AvoidFileStreamBenchmark.java
rm ../../src/main/java/de/pmdcheck/factorial/sevenSources/*
rm ../../src/main/java/de/pmdcheck/factorial/UseArrayListInsteadOfVectorBenchmarkSeven.java
docker run --privileged -v $(pwd)"../../../":/home/validation_jmh -it jdk11
