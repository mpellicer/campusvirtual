package cat.udl.asic.kernel.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.Properties;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;



public class SecurityProperties extends Properties {

	private static final Log log = LogFactory.getLog(SecurityProperties.class);	

    public static String encrypt(String key, String initVector, String value) {

        try {

            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));

            return Base64.encodeBase64String(encrypted);

        } catch (Exception ex) {
       		log.error ("Problems ecrypting ", ex);
        }

        return null;

    }
   
    private Resource securityPath;
    
    public void setSecurityPath(Resource sp) { securityPath = sp; }

    public static String decrypt(String key, String initVector, String encrypted) {

        try {

            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original,"UTF-8");

        } catch (Exception ex) {
       		log.error ("Problems decrypting ", ex);
        }

        return null;

    }


    public static void main (String args[]) {
	if (args.length != 4) {
		System.out.println ("Error passing argunment SecurityProperties [enc|dec] <16-digits key> <16-digits iv> <Message to encrypt>");
	}else {

		if ("enc".equals(args[0])) {
			System.out.println (encrypt(args[1],args[2],args[3]));
		} else if ("dec".equals(args[0])) {
			System.out.println (decrypt(args[1],args[2],args[3]));
		} else {
			System.out.println ("Error: First argument must be \"enc\" for encrypt or \"dec\" for decrypt");
		}
	}
    }

    public void init() {
	log.info ("Loading security keys...");
        String initVector = "RandomInitVector"; // 16 bytes IV
        try {
        	String securePath = System.getProperty("security.file.path", securityPath.getFile().getAbsolutePath());
		String securityKey = System.getProperty("security.token");  

		Properties prop = new Properties();
        	File securityFile = new File(securePath);
        	if (securityFile.exists()) {
       			log.info("Security keys file found...");
        		prop.load(new FileInputStream(securePath));
	        	
	            	for (String entryKey : prop.stringPropertyNames()) {
            			this.put(entryKey, decrypt(securityKey.substring(0,16), initVector, prop.getProperty(entryKey)));
	            	}
	        	
            		log.info("Loaded " + prop.stringPropertyNames().size() + " properties from security keys!!");
        	} else {
        			log.warn ("Security keys not found: "+securePath);
        	}
        } catch (Exception ex) {
        		log.error ("Problems loading security keys", ex);
        }
    }
}






