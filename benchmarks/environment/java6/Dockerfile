FROM ubuntu:12.04

RUN apt-get update && \
	apt-get install -y openjdk-6-jdk vim maven git

#RUN apt-get install -y linux-tools-generic linux-cloud-tools-generic
#RUN apt-get install -y linux-cloud-tools-`uname -r` linux-tools-`uname -r`

#RUN cd home/ && \
#        wget https://archive.apache.org/dist/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.tar.gz && \
#        tar -xvf apache-maven-3.2.5-bin.tar.gz

#ENV PATH "/home/apache-maven-3.2.5/bin/:$PATH"

ENV JAVA_VERSION 6

WORKDIR /home/validation_jmh
