package com.stone.configuration.utils;

/**
 * @author honorstone
 * @date 2019/3/19 14:58
 */
public class StringUtils {

    private StringUtils(){
    }
    public static boolean isBlank(String str){
        return null==str || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str){
        return null!=str || !str.trim().isEmpty();
    }

    public static boolean isNotEmpty(String str){
        return !str.isEmpty();
    }

    public static boolean isEmpty(CharSequence cs){
        return null == cs || 0 == cs.length();
    }

    public static boolean isNotEmpty(CharSequence cs){
        return !isEmpty(cs);
    }

    public static boolean isBlank(CharSequence cs){
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }
    public static boolean isNotBlank(CharSequence cs){
        return !isBlank(cs);
    }
}
