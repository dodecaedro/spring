#!/bin/bash

sudo rm /etc/tomcat7/tomcat-users.xml
sudo sh -c "wget https://raw.github.com/dodecaedro/spring/master/vm_scripts/tomcat-users.xml -O /etc/tomcat7/tomcat-users.xml"
sudo chown tomcat7:tomcat7 /etc/tomcat7/tomcat-users.xml