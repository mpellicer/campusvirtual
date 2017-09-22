#!/bin/bash

set -e

# This entrypoint sets up the certification files and configures the appropiate balancer members
if [ -f "/opt/hosts" ]; then
   echo 'Mounting hosts from partition'
   #Discard unavailable hosts
   /opt/scripts/discardunavailablehosts.sh
   #Enable the hosts
   cat /opt/hosts > /opt/enabledhosts
   #Call script to build the apache balancer
   /opt/scripts/buildbalancer.sh
   service cron start
   crontab /opt/scripts/checkcrontask
else
  (
  echo '<Proxy balancer://sakai>'
  echo '   ProxySet lbmethod=bybusyness stickysession=JSESSIONID nofailover=on'

  # In docker-compose v2 file format there's no nice way to find all the linked containers so we just jump through all the
  # IDs until we find one that isn't there.
  # https://github.com/jwilder/docker-gen - This looks to be a better way to generate config
  count=1
  while true
  do
    app_server=app_${count}
    # Look for the hostname which is used in routing
    if route=$(getent hosts ${app_server} | tr '[:space:]' '\n'|  grep '^[0-9a-z]*_[0-9]*$') ; then 
      echo BalancerMember ajp://${app_server}:8009 retry=0 route=$route
    else
      echo Failed to find the ID of app server $app_server >&2
      break;
    fi
    count=$(($count + 1))
  done
  echo '</Proxy>'
  ) > /etc/apache2/conf-available/sakai-balancer.conf
fi

echo 'Added balancer members'

a2enconf sakai-balancer

# Copy in the ssl public and private keys
if [ -f "/opt/files/ssl-private" -a -f "/opt/files/ssl-public" ]; then

  cp /opt/files/ssl-private /etc/ssl/private/ssl-private.key
  chown root:www-data /etc/ssl/private/ssl-private.key
  chmod 640 /etc/ssl/private/ssl-private.key

  cp /opt/files/ssl-public /etc/ssl/certs/ssl-public.crt
  chown root:www-data /etc/ssl/certs/ssl-public.crt
  chmod 644 /etc/ssl/certs/ssl-public.crt

  if [ -f "/opt/files/ssl-chain" ]; then
    cp /opt/files/ssl-chain /etc/ssl/certs/ssl-chain.crt.pem
    chown root:www-data /etc/ssl/certs/ssl-chain.crt.pem
    chmod 644 /etc/ssl/certs/ssl-chain.crt.pem
  else
    # If we don't have a chain file remove the config for it.
    sed -i '/SSLCertificateChainFile/d' /etc/apache2/sites-available/sakai.conf
    sed -i '/SSLCertificateChainFile/d' /etc/apache2/sites-available/kibana.conf
  fi
  a2enmod -q ssl
  if [ -z "$SERVERNAME" ]; then
    servername=$(openssl x509 -noout -subject -in /etc/ssl/certs/ssl-public.crt | sed -n '/^subject/s/^.*CN=//p')
  else
    servername="$SERVERNAME"
  fi
  sed -i "/ServerName/c\\
ServerName $servername" /etc/apache2/sites-available/sakai.conf
sed -i "/ServerName/c\\
ServerName $servername" /etc/apache2/sites-available/kibana.conf

#Add a username a username and password to authenticate kibana console

htpasswd -bc /etc/apache2/.htpasswd $KIBANA_USERNAME $KIBANA_PASSWORD

fi

#Starting the filebeat service
service filebeat start

# apache2ctl would set these but we're calling apache directly to get better docker signals.
source /etc/apache2/envvars

exec "$@"
