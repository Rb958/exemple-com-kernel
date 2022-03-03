package com.afrikpay.security.utils;

import com.afrikpay.security.service.encryption.EncryptionParameter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class EncryptionUtil {

    private EncryptionParameter builder;

    public void init(EncryptionParameter builder){
       this.builder = builder;
    }

    public String encrypt(byte[] message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(builder.buildCipher());
        if (builder.getIv() != null){
            cipher.init(Cipher.ENCRYPT_MODE, builder.getKey(), builder.getIv());
        }else {
            cipher.init(Cipher.ENCRYPT_MODE, builder.getKey());
        }
        byte[] encrypted = cipher.doFinal(message);
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(builder.buildCipher());
        if (builder.getIv() != null){
            cipher.init(Cipher.DECRYPT_MODE, builder.getKey(), builder.getIv());
        }else {
            cipher.init(Cipher.DECRYPT_MODE, builder.getKey());
        }
        byte[] encryptedByte = Base64.getDecoder().decode(plainText);
        byte[] decrypted = cipher.doFinal(encryptedByte);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}
