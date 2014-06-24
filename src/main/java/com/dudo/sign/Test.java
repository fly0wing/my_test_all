package com.dudo.sign;

import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * User: zk
 * Date: 13-7-17
 * Time: 上午10:33
 */
public class Test {
    public static void main(String[] args) throws Exception {










        Test rsa = new Test();

        String str = IOUtils.toString(new FileInputStream("D:\\test\\abc.txt") );
        System.out.println("str:"+str);

        String signStr = IOUtils.toString(new FileInputStream("D:\\test\\abc.sig") );
        System.out.println("signStr:"+signStr);

        String publickKeyStr = IOUtils.toString(new FileInputStream("D:\\test\\LakalaMall.key") );
        System.out.println("publickKeyStr:"+publickKeyStr);

        RSAPublicKey publickKey = rsa.loadPublicKey(publickKeyStr);
        System.out.println("publickKey:"+publickKey);
        if (rsa.verifySign(str, signStr, publickKey)) {
            System.out.println("rsa sign check success");
        } else {
            System.out.println("rsa sign check failure");
        }
    }
    public RSAPublicKey loadPublicKey(String publicKeyStr) throws Exception{
//        try {
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(publicKeyStr.getBytes());
            return  (RSAPublicKey) keyFactory.generatePublic(keySpec);
//        } catch (NoSuchAlgorithmException e) {
//            throw new Exception("无此算法");
//        } catch (InvalidKeySpecException e) {
//            throw new Exception("公钥非法");
//        } catch (NullPointerException e) {
//            throw new Exception("公钥数据为空");
//        }
    }
    /**
     * 加密,key可以是公钥，也可以是私钥
     *
     * @param message
     * @return
     * @throws Exception
     */
    public byte[] encrypt(String message, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(message.getBytes());
    }

    /**
     * 解密，key可以是公钥，也可以是私钥，如果是公钥加密就用私钥解密，反之亦然
     *
     * @param message
     * @return
     * @throws Exception
     */
    public byte[] decrypt(String message, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(toBytes(message));
    }

    /**
     * 用私钥签名
     *
     * @param message
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] sign(String message, PrivateKey key) throws Exception {
        Signature signetcheck = Signature.getInstance("NONEwithRSA");
        signetcheck.initSign(key);
        signetcheck.update(message.getBytes("ISO-8859-1"));
        return signetcheck.sign();
    }

    /**
     * 用公钥验证签名的正确性
     *
     * @param message
     * @param signStr
     * @return
     * @throws Exception
     */
    public boolean verifySign(String message, String signStr, PublicKey key)
            throws Exception {
        if (message == null || signStr == null || key == null) {
            return false;
        }
        Signature signetcheck = Signature.getInstance("NONEwithRSA");
        signetcheck.initVerify(key);
        signetcheck.update(message.getBytes("ISO-8859-1"));
        return signetcheck.verify(toBytes(signStr));
    }

    /**
     * 从文件读取object
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    private Object readFromFile(String fileName) throws Exception {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(
                fileName));
        Object obj = input.readObject();
        input.close();
        return obj;
    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEXCHAR[(b[i] & 0xf0) >>> 4]);
            sb.append(HEXCHAR[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static final byte[] toBytes(String s) {
        byte[] bytes;
        bytes = new byte[s.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),
                    16);
        }
        return bytes;
    }

    private static char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

}
