#!/bin/bash

sudo rm /etc/tomcat7/tomcat-users.xml
sudo -u tomcat7 sh -c "wget https://raw.github.com/dodecaedro/spring/master/vm_scripts/tomcat-users.xml -O /etc/tomcat7/tomcat-users.xml"
