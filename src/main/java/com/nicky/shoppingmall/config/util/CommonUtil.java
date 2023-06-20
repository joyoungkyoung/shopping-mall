package com.nicky.shoppingmall.config.util;

public class CommonUtil {
    public static String makeTag(String... tags) {
        StringBuilder sb = new StringBuilder();
        for(String tag : tags) {
            sb.append("|"+tag+"|");
        }

        return sb.toString();
    }
}
