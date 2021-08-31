package asadal.jaeyoung.security;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import egovframework.rte.fdl.idgnr.impl.Base64;

public class SHA512 {
	public String getSha512(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = plainText.getBytes(Charset.forName("UTF-8"));
            md.update(bytes);
            return Base64.encode(md.digest());
        } catch (Exception e) {
            System.out.println("Sha512 error.");
            e.printStackTrace();
            return null;
        }
    }

}
