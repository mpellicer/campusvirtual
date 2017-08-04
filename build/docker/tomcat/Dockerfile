# This is the dockerfile for deploy a tomcat where Sakai will be executed.

# Use the OpenJDK image
# This builds on debian jessie
FROM java:openjdk-8u66

MAINTAINER Alexandre Ballesté  <alexandre.balleste@udl.cat>

WORKDIR /tmp

RUN apt-get clean && apt-get update
RUN apt-get install locales
RUN apt-get install locales

# Create user and group sakai. It will be the user that will execute the service
RUN groupadd --gid 10000 sakai && \
    useradd --uid 10000 --gid 10000 --system sakai 

# Download and install confd
RUN curl -sSL -o /usr/local/bin/confd https://github.com/kelseyhightower/confd/releases/download/v0.7.1/confd-0.7.1-linux-amd64 && \
    chmod +x /usr/local/bin/confd 

# Download and prepare the tomcat
RUN mkdir -p /home/sakai/server/tomcat/sakai && \
    mkdir -p /home/sakai/server/tomcat/sakai-bin && \
    mkdir -p /home/sakai/server/logs && \
    mkdir -p /home/sakai/scripts && \
    mkdir -p /etc/confd/conf.d && \
    mkdir -p /etc/confd/templates/conf &&\
    mkdir -p /etc/confd/templates/bin &&\
    #Download and extract tomcat
    curl -s https://archive.apache.org/dist/tomcat/tomcat-8/v8.0.41/bin/apache-tomcat-8.0.41.tar.gz | \
    tar zxf - --strip-components 1 -C /home/sakai/server/tomcat && \
    #Clean the default demo data
    cd /home/sakai/server/tomcat && \
    rm -r webapps && \
    mkdir webapps

# Copy all the tomcat's configuration files to serve sakai
COPY conf/* /etc/confd/templates/conf/
COPY bin/*  /etc/confd/templates/bin/
COPY setup/* /etc/confd/conf.d/

# Copy the entrypoint script to be able to set up all the configuration
COPY ./entrypoint.sh /home/sakai/scripts/entrypoint.sh

#Add custom static webapps not properly of the sakai's context
COPY webapps/* /home/sakai/server/tomcat/webapps/

COPY ./entrypoint.sh /home/sakai/scripts/entrypoint.sh

# Set the custom permission to execute
RUN chown -R sakai:sakai /home/sakai && \
    find /home/sakai/server/tomcat/conf/ -type f| xargs chmod 640 && \
    find /home/sakai/server/tomcat/bin/*.sh -type f| xargs chmod 740 && \
    chmod +x /home/sakai/scripts/entrypoint.sh


# Copy in the JCE unlimited strength policy files
RUN curl -sLO --cookie 'oraclelicense=accept-securebackup-cookie;'  http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip && \
  jar xf jce_policy-8.zip && \
  cp UnlimitedJCEPolicyJDK8/*.jar $JAVA_HOME/jre/lib/security && \
  rm -r jce_policy-8.zip UnlimitedJCEPolicyJDK8

# This sets the default locale and gets it to work correctly in Java
ENV LANG=ca_ES.UTF-8
RUN locale-gen $LANG


ENTRYPOINT ["/home/sakai/scripts/entrypoint.sh"]

CMD ["/home/sakai/server/tomcat/bin/catalina.sh","run"]
