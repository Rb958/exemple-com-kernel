package com.afrikpay.security.utils;

import com.afrikpay.security.kernel.SecurityKernel;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import rkernel.KernelFactory;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private FileUtil(){}
    public static List<String> readEncrypted(Path filePath, String encryptKey, String iv) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<String> blackList = new ArrayList<>();
        if (Files.notExists(filePath)){
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try(FileInputStream fis = new FileInputStream(filePath.toFile())){
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(
                    Cipher.DECRYPT_MODE,
                    new SecretKeySpec(Hex.decodeHex(encryptKey), "AES"),
                    new IvParameterSpec(Hex.decodeHex(iv))
            );
            try(
                    CipherInputStream cis = new CipherInputStream(fis, cipher);
                    InputStreamReader isr = new InputStreamReader(cis);
                    BufferedReader buff = new BufferedReader(isr)
            ){
                buff.lines().forEach(blackList::add);
            }
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | DecoderException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            e.printStackTrace();
            KernelFactory.getInstance(SecurityKernel.class).dispatchLogException(e);
        }
        return blackList;
    }

    public static void writeEncryption(Path filePath, List<String> content, String encryptKey, String iv) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        try(FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(
                    Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(Hex.decodeHex(encryptKey), "AES"),
                    new IvParameterSpec(Hex.decodeHex(iv))
            );
            try (
                    CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            ) {
                cos.write(format(content).getBytes());
            }
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | DecoderException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            KernelFactory.getInstance(SecurityKernel.class).dispatchLogException(e);
        }
    }

    private static String format(List<String> content) {
        StringBuilder sb = new StringBuilder();
        content.forEach(str -> sb.append(str).append("\n"));
        return sb.toString();
    }
}
