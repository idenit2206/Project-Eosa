package com.eosa.admin.encode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * packageName    : idenit.ansim
 * fileName       : SHA256
 * author         : Jihun Kim
 * date           : 2022-09-08
 * description    : SHA-256 암호화 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-08        Jihun Kim       최초 생성
 */
public class SHA256 {

    /**
     * 암호화 메서드
     *
     * @param text
     * @return String
     * @throws NoSuchAlgorithmException
     */
    public String encrypt(String text) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(text.getBytes());

        return bytesToHex(messageDigest.digest());
    }

    /**
     * 16진수 변환 메서드
     *
     * @param bytes
     * @return String
     */
    private String bytesToHex(byte[] bytes) {

        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }

        return stringBuilder.toString();
    }

}
