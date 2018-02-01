package com.jxl.jcrawler.util.common;

import com.jxl.jcrawler.enums.Encodings;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Created by amosli on 20/07/2017.
 */
public class Base64Util {

    private static final String DEFAULT_CODE = Encodings.UTF8.getValue();

    public static String encode(byte[] bytes) {
        return encode(bytes, DEFAULT_CODE);
    }

    public static String encode(byte[] bytes, String charset) {
        byte[] encode = Base64.getEncoder().encode(bytes);
        return new String(encode, Charset.forName(charset));
    }

    public static String decode(byte[] bytes) {
        return decode(bytes, DEFAULT_CODE);
    }

    public static String decode(byte[] bytes, String charset) {
        byte[] encode = Base64.getDecoder().decode(bytes);
        return new String(encode, Charset.forName(charset));
    }

    public static String encodeUrl(byte[] bytes) {
        return encodeUrl(bytes, DEFAULT_CODE);
    }


    public static String encodeUrl(byte[] bytes, String charset) {
        byte[] encode = Base64.getUrlEncoder().encode(bytes);
        return new String(encode, Charset.forName(charset));
    }

    public static String decodeUrl(byte[] bytes) {
        return decodeUrl(bytes, DEFAULT_CODE);
    }

    public static String decodeUrl(byte[] bytes, String charset) {
        byte[] encode = Base64.getUrlDecoder().decode(bytes);
        return new String(encode, Charset.forName(charset));
    }


}
