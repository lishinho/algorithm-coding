package com.lzb.fund.JavaSE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReplace {
    public static void main(String[] args) {
        String str="Hello World";
        String regEx= "[abcdH]";
        String reStr;
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        reStr = matcher.replaceAll("tt").trim();// 如替换 a、b、c、d、H 为空，即删除这几个字母
        System.out.println( reStr );
        System.out.println(str.substring(str.lastIndexOf("or")+2));
    }
}
