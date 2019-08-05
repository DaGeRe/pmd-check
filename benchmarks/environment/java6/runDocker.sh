docker build -t "jdk6" .
echo "Mounting"$(pwd)"../../../"
ls $(pwd)
cp pom.xml ../../
rm ../../src/main/java/de/pmdcheck/AvoidFileStreamBenchmark.java
rm -rf ../../src/main/java/de/pmdcheck/factorial/
docker run --privileged -v $(pwd)"../../../":/home/validation_jmh -it jdk6
