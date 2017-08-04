#Variables d'entorn de java
JAVA_OPTS={{getenv "TOMCAT_JAVA_OPTS"}}

#Sortida del log de sakai
CATALINA_OUT="{{getenv "TOMCAT_LOGS_PATH"}}/catalina.out"
