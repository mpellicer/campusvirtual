-- Create the temporary database user to use in sakai development

-- USER SQL
CREATE USER sakai IDENTIFIED BY sakai;

-- ROLES
GRANT "RESOURCE" TO sakai;
GRANT "CONNECT" TO sakai;
