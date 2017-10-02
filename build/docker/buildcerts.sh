#!/bin/bash
# 
# This creates a self signed certificate which can be used by Apache for serving HTTPS and logstash connections
# 

mkdir private
mkdir certs

openssl req -sha256 -x509 -nodes -newkey rsa:2048 \
  -subj '/C=ES/ST=Lleida/L=Lleida/O=Universitat de Lleida/OU=ASIC Services/CN=your-host.udl.net' \
  -keyout private/ssl-private.key -out certs/ssl-public.crt -days 3650

openssl req -x509 -batch -nodes -subj "/CN=elk/" \
    -days 3650 -newkey rsa:2048 \
    -keyout private/logstash-beats.key -out certs/logstash-beats.crt
