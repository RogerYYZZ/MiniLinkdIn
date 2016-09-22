package com.example.yangzheming.minilinkedin_viewpager.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzheming on 9/15/16.
 */

public class StyleUtils {


    public static String formatItems(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (String item: items) {
            sb.append(' ').append('-').append(' ').append(item).append('\n');
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
