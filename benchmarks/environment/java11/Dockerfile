FROM ubuntu:18.04

RUN apt-get update && \
	apt-get install -y openjdk-11-jdk vim maven git

# This installs things necessary for perfasm
RUN apt-get install -y linux-tools-generic linux-cloud-tools-generic
RUN apt-get install -y libhsdis0-fcml
# Hard-coded kernel version is not best practice, but works as long as this kernel is used..
#RUN apt-get install -y linux-cloud-tools-`uname -r` linux-tools-`uname -r`

# For Java 11, the package currently does not create the link..
RUN ln -s /usr/lib/x86_64-linux-gnu/libhsdis.so.0 /usr/lib/jvm/java-11-openjdk-amd64/lib/hsdis-amd64.so

ENV JAVA_VERSION 11

WORKDIR /home/validation_jmh
