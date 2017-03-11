package com.realaicy.prod.jc.modules.wx.lib;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 提供基于PKCS7算法的加解密接口.
 */
class PKCS7Encoder {
    private static Charset charset = Charset.forName("utf-8");
    private static int blockSize = 32;

    static byte[] encode(int count) {
        int amountToPad = blockSize - (count % blockSize);
        if (amountToPad == 0) {
            amountToPad = blockSize;
        }
        char padChr = chr(amountToPad);
        String tmp = "";
        for (int index = 0; index < amountToPad; index++) {
            tmp += padChr;
        }
        return tmp.getBytes(charset);
    }

    static byte[] decode(byte[] decrypted) {
        int pad = (int) decrypted[decrypted.length - 1];
        if (pad < 1 || pad > 32) {
            pad = 0;
        }
        return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
    }

    static char chr(int a) {
        byte target = (byte) (a & 0xFF);
        return (char) target;
    }

}
