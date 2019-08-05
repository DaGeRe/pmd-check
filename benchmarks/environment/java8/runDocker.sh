docker build -t "jdk8" .
echo "Mounting"$(pwd)"../../../"
ls $(pwd)
cp pom.xml ../../
git checkout -- ../../src/main/java/de/pmdcheck/AvoidFileStreamBenchmark.java
docker run --privileged -v $(pwd)"../../../":/home/validation_jmh -it jdk8
echo "Run update-alternatives --set java /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java"
