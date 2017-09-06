#!/bin/bash
echo -n "" > /tmp/temp_file

while read -r line
do
 app_server="$line"
 if route=$(getent hosts ${app_server} | tr '[:space:]' '\n'| grep '^[0-9a-z]\{12\}$') ; then
    echo $app_server >> /tmp/temp_file
 else
    echo "Discarting $app_server" 
 fi
done < "/opt/hosts"
cat /tmp/temp_file > /opt/hosts	
