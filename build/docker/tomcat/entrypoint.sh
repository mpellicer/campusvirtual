#!/bin/bash

##set -e

su -s /bin/sh -c 'confd -onetime -backend env' sakai

# We do the su at the end so we can setup permissions throughout
echo "System configured"
cat /home/sakai/server/tomcat/bin/setenv.sh

# Announce the server presence in the shared volume
if [ -f "/opt/hosts" ]; then

  hostexists=$(grep $HOSTNAME /opt/hosts)

  if [ "$hostexists" != "$HOSTNAME" ]; then
     echo $HOSTNAME >> /opt/hosts
  fi
fi

# We do the su at the end so we can setup permissions throughout
exec su -s /bin/sh -c 'exec "$0" "$@"' sakai -- $@
