#!/bin/bash

##set -e

su -s /bin/sh -c 'confd -onetime -backend env' sakai

ln -s /home/sakai/server/tomcat/conf/log4j.properties /home/sakai/server/tomcat/lib/log4j.properties

# We do the su at the end so we can setup permissions throughout
echo "System configured"

# Announce the server presence in the shared volume
if [ -f "/opt/hosts" ]; then

  hostexists=$(grep $HOSTNAME /opt/hosts)

  if [ "$hostexists" != "$HOSTNAME" ]; then
     echo $HOSTNAME >> /opt/hosts
  fi
fi

service filebeat start

# We do the su at the end so we can setup permissions throughout
exec su -s /bin/sh -c 'exec "$0" "$@"' sakai -- $@
#exec "$@"
