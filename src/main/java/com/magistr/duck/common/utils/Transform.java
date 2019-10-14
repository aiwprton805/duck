package com.magistr.duck.common.utils;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.entity.Term;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Transform {

    public static String IdToSubDirPath(final Integer id){
        final char separator = File.separatorChar;
        final int capacity = 13;
        final int num = id;
        return new StringBuilder(capacity)
                .append(separator)
                .append(Integer.toHexString(num / 1_000_000))
                .append(separator)
                .append(Integer.toHexString(num / 1_000 % 1_000))
                .append(separator)
                .append(Integer.toHexString(num % 1_000))
                .append(separator).toString();
    }

    public static Integer dirPathToId(final String dirPath){
        final String separator = File.separator;
        final String[] dirs = dirPath.split(separator);
        final int size = dirs.length;
        final int radix = 16;
        return Integer.parseInt(dirs[size - 2], radix)
                + Integer.parseInt(dirs[size-3], radix) * 1_000
                + Integer.parseInt(dirs[size-4], radix) * 1_000_000;
    }

    public static Lang toLang(final String lang){
        Lang langEnum;
        switch (lang){
            case "RU":{
                langEnum = Lang.RU;
                break;
            }
            case "DE":{
                langEnum = Lang.DE;
                break;
            }
            case "EN":{
                langEnum = Lang.EN;
            }
            default:{
                langEnum = null;
            }
        }
        return langEnum;
    }
}
//class
