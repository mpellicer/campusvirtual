#!/bin/bash

if [ -f "/opt/hosts.txt" -a -f "/opt/enabledhosts.txt" ]; then

  #Look if the file already exists
  enabledhosts=$(md5sum /opt/enabledhosts.txt|awk '{print $1}')
  hosts=$(md5sum /opt/hosts.txt|awk '{print $1}')

  if [ "$enabledhosts" != "$hosts" ]; then
    echo "Found diferences in the hosts list. "
    #Discard unavailable hosts
    /opt/scripts/discardunavailablehosts.sh
    #Enable the hosts
    cat /opt/hosts.txt > /opt/enabledhosts.txt
    #Call script to build the apache balancer
    /opt/scripts/buildbalancer.sh
    #reload the service
    /usr/sbin/service apache2 reload
  fi

else 
 echo "Hosts control files not found"
fi
