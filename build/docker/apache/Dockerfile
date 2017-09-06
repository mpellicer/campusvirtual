# This is a simple Apache setup for a balanced environment 

FROM ubuntu:16.04

MAINTAINER Alexandre Ballesté <alexandre.balleste@udl.cat>

# Install apache2
RUN \
  apt-get update && \
  DEBIAN_FRONTEND=noninteractive apt-get install -y apache2 vim cron
  #rm -rf /var/lib/apt/lists/* 

# Copy in the apache config
COPY sakai.conf /etc/apache2/sites-available/sakai.conf

#Enable the required modules and load the configuration as default
RUN \
  a2enmod proxy proxy_ajp proxy_balancer rewrite headers lbmethod_byrequests lbmethod_bybusyness && \
  a2ensite sakai && \
  a2dissite 000-default

# Create and the directories where we'll put the configuration files
RUN \
  mkdir -p /opt/files && \
  mkdir -p /opt/scripts

COPY *.sh /opt/scripts/
RUN chmod 755 /opt/scripts/*.sh

#Create a log file for cron job
COPY checkcrontask /opt/scripts
RUN touch /var/log/cron.log

# Expose the HTTP common ports
EXPOSE 443

# Execute the entry point to build the configuration of the balancers dinamically
ENTRYPOINT ["/opt/scripts/entrypoint.sh"]

CMD ["/usr/sbin/apache2ctl", "-D", "FOREGROUND", "-k", "start"]
