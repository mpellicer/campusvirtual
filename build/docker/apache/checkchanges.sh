#!/bin/bash

if [ -f "/opt/hosts" -a -f "/opt/enabledhosts" ]; then

  #Look if the file already exists
  enabledhosts=$(md5sum /opt/enabledhosts|awk '{print $1}')
  hosts=$(md5sum /opt/hosts|awk '{print $1}')

  if [ "$enabledhosts" != "$hosts" ]; then
    echo "Found diferences in the hosts list. "
    #Discard unavailable hosts
    /opt/scripts/discardunavailablehosts.sh
    #Enable the hosts
    cat /opt/hosts > /opt/enabledhosts
    #Call script to build the apache balancer
    /opt/scripts/buildbalancer.sh
    #reload the service
    /usr/sbin/service apache2 reload
  fi

else 
 echo "Hosts control files not found"
fi
