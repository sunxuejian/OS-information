package common.tool.os.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

/**
 * @author like
 * @description AesEncrypt
 * @data 2018/10/19
 */
@Component
public class AesEncryptUtil {

//    @Value("${encryption.method}")
    private String encryptionMethod="AES/CBC/PKCS5PADDING";

//    @Value("${encryption.key}")
    private String encryptionKey="kafang0123456789";

//    @Value("${encryption.initVector}")
    private String encryptionInitVector="0000000000000000";

    /**
     * 十六进制转换字符串
     */

    public static byte[] hexStr2Bytes(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for(int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return bytes;
    }

    /**
     * bytes转换成十六进制字符串
     */
    public static String byte2HexStr(byte[] b) {
        String hs = "";
        String stmp = "";
        for(int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if(stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 加密
     *
     * @param value
     * @return
     */
    public String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(encryptionInitVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(encryptionMethod);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));

            return byte2HexStr(encrypted);
        } catch (Exception ex) {
            System.out.println("aes encrypt value={}  error:"+ ex);
        }

        return value;
    }

    /**
     * 解密
     *
     * @param encrypted
     * @return
     */
    public String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(encryptionInitVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(encryptionMethod);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(hexStr2Bytes(encrypted));

            return new String(original, Charset.forName("UTF-8"));
        } catch (Exception ex) {
            System.out.println("aes encrypted value={}  error:"+ ex);
        }

        return encrypted;
    }

    public static void main(String[] args) {
        AesEncryptUtil config = new AesEncryptUtil();
        config.encryptionMethod = "AES/CBC/PKCS5PADDING";
        config.encryptionKey = "kafang0123456789"; // 128 bit key
        config.encryptionInitVector = "0000000000000000"; // 16 bytes IV

        String en = config.encrypt("Hh123!@#_111111");
        System.out.println(en);
        System.out.println(config.decrypt("C2F9C56EB1542CA668370A9E6C6E50425EE3809AB0A6FF01DB4193DA670F97378907FEAA9F84F4037F1EFDF7594C38EB73239D1F1A2BC3173438E4B4349C891A85A49C5B6303847BE26CECAF19E449ECAFB16B35C601F1F07CED214C15870C9515161E43D6E591A88B75BFC113354B73"));
    }
}
