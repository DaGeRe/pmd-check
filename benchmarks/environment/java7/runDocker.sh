docker build -t "jdk7" .
echo "Mounting"$(pwd)"../../../"
ls $(pwd)
cp pom.xml ../../
git checkout -- ../../src/main/java/de/pmdcheck/AvoidFileStreamBenchmark.java
rm -rf ../../src/main/java/de/pmdcheck/factorial/
docker run --privileged -v $(pwd)"../../../":/home/validation_jmh -it jdk7
