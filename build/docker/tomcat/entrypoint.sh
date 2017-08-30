#!/bin/bash

##set -e

su -s /bin/sh -c 'confd -onetime -backend env' sakai

# We do the su at the end so we can setup permissions throughout
echo "System configured"
cat /home/sakai/server/tomcat/bin/setenv.sh

# We do the su at the end so we can setup permissions throughout
exec su -s /bin/sh -c 'exec "$0" "$@"' sakai -- $@
wait 3
cat /home/sakai/server/logs/catalina.out

