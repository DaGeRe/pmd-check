# This installs everything needed for perf on Linux kernel 4.15.0-54 on Ubuntu. This works in the Docker containers of pmdcheck, if the host system runs on 4.15.0-54. For other kernels (or other distributions), different packages may be needed. 
version="4.15.0-54_4.15.0-54.58_amd64"
versionGeneric="4.15.0-54-generic_4.15.0-54.58_amd64"
apt-get install wget
wget http://de.archive.ubuntu.com/ubuntu/pool/main/n/numactl/libnuma1_2.0.11-2.1_amd64.deb
dpkg -i libnuma1_2.0.11-2.1_amd64.deb
wget http://de.archive.ubuntu.com/ubuntu/pool/main/p/pciutils/libpci3_3.5.2-1ubuntu1_amd64.deb
dpkg -i libpci3_3.5.2-1ubuntu1_amd64.deb
wget http://de.archive.ubuntu.com/ubuntu/pool/main/l/linux/linux-cloud-tools-$version.deb
dpkg -i linux-cloud-tools-$version.deb
wget http://de.archive.ubuntu.com/ubuntu/pool/main/l/linux/linux-tools-$version.deb
dpkg -i linux-tools-$version.deb
wget http://security.ubuntu.com/ubuntu/pool/main/l/linux/linux-cloud-tools-$versionGeneric.deb
dpkg -i linux-cloud-tools-$versionGeneric.deb
wget http://security.ubuntu.com/ubuntu/pool/main/l/linux/linux-tools-$versionGeneric.deb
dpkg -i linux-tools-$versionGeneric.deb
