#!/bin/bash
echo "======================================================="
echo "      NOTE: before runing this script you have to      "
echo "make yourself sure of download and installing Java jdk"
echo "   after replace the JAVA_HOME variable (SYSTEM WIDE)  "
echo "======================================================="
echo "           Downloading and installing WildFly          "
echo "  WARNING: You gonna have to configure it by yourself  "
echo "======================================================="
wget http://download.jboss.org/wildfly/10.1.0.Final/wildfly-10.1.0.Final.zip -O /tmp/wildfly.zip
cd /tmp
sudo unzip wildfly.zip -d /opt/
cd /opt/wildfly-10.1.0.Final/bin
echo "JBOSS_HOME=\"/opt/wildfly-10.1.0.Final\"" >> standalone.conf
echo "JAVA_HOME=\"/usr/java/jdk1.8.0_161\"" >> standalone.conf
cd /etc
echo "JBOSS_HOME=\"/opt/wildfly-10.1.0.Final\"" >> profile
echo "======================================================="
echo "We are done with the installation of WildFly server..."
echo "======================================================="
echo "please follow the steps to configure from https://devops.profitbricks.com/tutorials/how-to-install-and-configure-wildfly-application-server-on-centos-7/"
