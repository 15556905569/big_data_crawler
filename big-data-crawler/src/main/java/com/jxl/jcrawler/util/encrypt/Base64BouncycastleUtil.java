package com.jxl.jcrawler.util.encrypt;

import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by amosli on 16/4/1.
 */
public class Base64BouncycastleUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(Base64BouncycastleUtil.class);

    private Base64BouncycastleUtil() {
        throw new IllegalAccessError("Base64Util class");
    }

    //encode
    public static byte[] encodeAsBytes(String input, String charset) {
        byte[] encode = new byte[0];
        try {
            encode = Base64.encode(input.getBytes(charset));
            return encode;
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.toString(), e);
        }
        return encode;
    }

    public static String encode(String input, String charset) {
        byte[] bytes = encodeAsBytes(input, charset);
        return new String(bytes, Charset.forName(charset));
    }

    public static String encode(byte[] bytes, String charset) {
        byte[] encode = Base64.encode(bytes);
        return new String(bytes, Charset.forName(charset));
    }

    public static String encode(String input) {
        return encode(input, "utf8");
    }


    //Decode
    public static byte[] decodeAsBytes(String input, String charset) {
        byte[] encode = new byte[0];
        try {
            encode = Base64.decode(input.getBytes(charset));
            return encode;
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.toString(), e);
        }
        return encode;
    }

    public static String decode(String input, String charset) {
        byte[] bytes = decodeAsBytes(input, charset);
        return new String(bytes, Charset.forName(charset));
    }

    public static String decode(String input) {
        return decode(input, "utf8");
    }


}
