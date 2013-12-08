#!/bin/bash

sudo -u tomcat7 sh -c "echo > /var/log/tomcat7/catalina.out"
sudo rm /var/log/tomcat7/localhost* > /dev/null 2>&1
sudo rm /var/log/tomcat7/catalina.*.log > /dev/null 2>&1
sudo -u postgres sh -c "echo > /var/log/postgresql/postgresql-9.1-main.log"
