#!/bin/bash

if [ -f "/opt/enabledhosts.txt" ]; then

  (
    echo '<Proxy balancer://sakai>'
    echo '   ProxySet lbmethod=bybusyness stickysession=JSESSIONID nofailover=on'

    while read -r line
    do
      app_server="$line"
      #Check if the balance member is accessible
      if route=$(getent hosts ${app_server} | tr '[:space:]' '\n'| grep '^[0-9a-z]\{12\}$') ; then 
        echo BalancerMember ajp://${route}:8009 retry=0 route=$route
      fi
    done < "/opt/enabledhosts.txt"

    echo '</Proxy>'
  ) > /etc/apache2/conf-available/sakai-balancer.conf
  

else 
 echo File not found
fi
