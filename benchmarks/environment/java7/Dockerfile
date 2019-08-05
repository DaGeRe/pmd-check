FROM ubuntu:14.04

RUN apt-get update && \
	apt-get install -y openjdk-7-jdk vim maven git wget

RUN apt-get install -y linux-tools-generic linux-cloud-tools-generic

RUN wget http://de.archive.ubuntu.com/ubuntu/pool/main/n/numactl/libnuma1_2.0.11-2.1_amd64.deb
RUN dpkg -i libnuma1_2.0.11-2.1_amd64.deb
RUN wget http://de.archive.ubuntu.com/ubuntu/pool/main/p/pciutils/libpci3_3.5.2-1ubuntu1_amd64.deb
RUN dpkg -i libpci3_3.5.2-1ubuntu1_amd64.deb
RUN wget http://de.archive.ubuntu.com/ubuntu/pool/main/l/linux/linux-cloud-tools-4.15.0-50_4.15.0-50.54_amd64.deb
RUN dpkg -i linux-cloud-tools-4.15.0-50_4.15.0-50.54_amd64.deb
RUN wget http://de.archive.ubuntu.com/ubuntu/pool/main/l/linux/linux-tools-4.15.0-50_4.15.0-50.54_amd64.deb
RUN dpkg -i linux-tools-4.15.0-50_4.15.0-50.54_amd64.deb
RUN wget http://security.ubuntu.com/ubuntu/pool/main/l/linux/linux-cloud-tools-4.15.0-50-generic_4.15.0-50.54_amd64.deb
RUN dpkg -i linux-cloud-tools-4.15.0-50-generic_4.15.0-50.54_amd64.deb
RUN wget http://security.ubuntu.com/ubuntu/pool/main/l/linux/linux-tools-4.15.0-50-generic_4.15.0-50.54_amd64.deb
RUN dpkg -i linux-tools-4.15.0-50-generic_4.15.0-50.54_amd64.deb

#RUN cd home/ && \
#        wget https://archive.apache.org/dist/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.tar.gz && \
#        tar -xvf apache-maven-3.2.5-bin.tar.gz

#ENV PATH "/home/apache-maven-3.2.5/bin/:$PATH"

ENV JAVA_VERSION 7

WORKDIR /home/validation_jmh
