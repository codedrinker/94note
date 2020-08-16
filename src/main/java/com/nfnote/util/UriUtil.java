package com.nfnote.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by codedrinker on 2020/08/16.
 */
public class UriUtil {
    public static void main(String[] args) {
        System.out.println(randomUris());
        System.out.println(randomUris());
        System.out.println(randomUris());
        System.out.println(randomUris());
        System.out.println(randomUris());
        System.out.println(randomUris());
    }

    public static List<String> randomUris() {
        String suffix = String.valueOf(new Random().nextInt(1000000));
        String key = "start";
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };
        String sMD5EncryptResult = MD5Util.string2MD5(key + suffix);
        String hex = sMD5EncryptResult;

        List<String> uris = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < 6; j++) {
                long index = 0x0000003D & lHexLong;
                outChars += chars[(int) index];
                lHexLong = lHexLong >> 5;
            }
            uris.add(outChars);
        }
        return uris;
    }
}
