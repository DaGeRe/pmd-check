docker build -t "jdk12" .
echo "Mounting"$(pwd)"../../../"
ls $(pwd)
cp pom.xml ../../
git checkout -- ../../src/main/java/de/pmdcheck/AvoidFileStreamBenchmark.java
docker run --privileged -v $(pwd)"../../../":/home/validation_jmh -it jdk12
