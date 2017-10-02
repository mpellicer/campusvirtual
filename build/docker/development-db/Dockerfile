# This image creates an oracle instance database for sakai development 
FROM wnameless/oracle-xe-11g

MAINTAINER Alexandre Ballesté <alexandre.balleste@udl.cat>

#Adds the script needed to create sakai user
ADD init.sql /docker-entrypoint-initdb.d/
