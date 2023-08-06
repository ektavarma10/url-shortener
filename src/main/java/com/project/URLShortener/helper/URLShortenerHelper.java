package com.project.URLShortener.helper;


public class URLShortenerHelper {
    final static String charString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    final static int CHAR_LIMIT = 62;
    public static String getShortURL(long id) {
        return convertIdToHashValue(id);
    }

    private static String convertIdToHashValue(long id) {
        StringBuilder str = new StringBuilder();
        while(id >0) {
            str.append(String.valueOf(charString.charAt((int) (id %CHAR_LIMIT))));
            id = id /CHAR_LIMIT;
        }
        str.reverse();
        return str.toString();
    }
}
