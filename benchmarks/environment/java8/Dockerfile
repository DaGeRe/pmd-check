FROM ubuntu:18.04

RUN apt-get update && \
	apt-get install -y openjdk-8-jdk vim maven git

# This installs things necessary for perfasm
RUN apt-get install -y linux-tools-generic linux-cloud-tools-generic
RUN apt-get install -y libhsdis0-fcml
# Hard-coded kernel version is not best practice, but works as long as this kernel is used..
#RUN apt-get install -y linux-cloud-tools-`uname -r` linux-tools-`uname -r`

ENV JAVA_VERSION 8

RUN update-alternatives --set java /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java

WORKDIR /home/validation_jmh
