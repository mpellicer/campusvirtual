# This image copies the sakai files to the sakai tomcat container

FROM asicudl/tomcat-sakai-11:dev

# Copy all the files in
COPY sakai-bin/components /home/sakai/server/tomcat/components/
COPY sakai-bin/lib /home/sakai/server/tomcat/sakai-lib/
COPY sakai-bin/webapps /home/sakai/server/tomcat/webapps/

# Prepares the configuration files
RUN mkdir -p /home/sakai/server/tomcat/sakai/portlets && \
    chown -R sakai:sakai /home/sakai/server/tomcat/sakai
COPY conf/* /etc/confd/templates/conf/
COPY setup/* /etc/confd/conf.d/
   
