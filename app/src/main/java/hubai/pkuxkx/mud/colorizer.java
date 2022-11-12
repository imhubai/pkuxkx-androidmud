package hubai.pkuxkx.mud;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;


public class colorizer {
    private static ForegroundColorSpan colsp;
    private static ForegroundColorSpan hcolsp;
    private static BackgroundColorSpan hbcolsp;
    private static BackgroundColorSpan bcolsp;


    public static CharSequence spantxt(String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (str.contains("\u001b")) {
            for (String str2 : str.split("\u001b")) {
                CharSequence takespan = takespan(str2);
                if (takespan != null) {
                    spannableStringBuilder.append(takespan);
                }
            }
        } else {
            CharSequence takespan2 = takespan(str);
            if (takespan2 != null) {
                spannableStringBuilder.append(takespan2);
            }
        }
        return spannableStringBuilder;
    }

    public static CharSequence takespan(String str) {
        String str2;
        String str3;
        boolean z;
        SpannableString spannableString;
        if (str.indexOf("[u") == 0) {
            str2 = str.substring(3, str.indexOf("]"));
            str3 = str;
        } else if (str.indexOf("[s") == 0) {
            str2 = "";
            str3 = str;
        } else {
            str2 = "";
            str3 = str;
            if (str.indexOf("[H") == 0) {
                str3 = str.replace("[H", "");
                str2 = "";
            }
        }
        String str4 = " ";
        if (str2.equals("")) {
            str4 = " ";
            if (str3.contains("m")) {
                str4 = str3.substring(0, str3.indexOf("m") + 1);
            }
        }
        if (!str2.equals("")) {
            z = true;
           // urlsp = new myUSpan(str2);
            str3 = str3.replace(str3.substring(0, str3.indexOf("]") + 1), "");
        }  else if (str4.equals("[0m") || str4.contains(";0m")) {
            colsp = null;
            hcolsp = null;
            bcolsp = null;
            hbcolsp = null;
            z = false;
        } else if (str4.equals("[1m")) { // 加粗
            z = false;
        } else if (str4.equals("[4m")){

        z = false;
    } else if (str4.equals("[1;5m")){

        z = false;
    } else if (str4.indexOf("[f#") == 0) { // 自定义颜色
            colsp = new ForegroundColorSpan(Color.parseColor(str4.substring(2, str4.indexOf("m"))));
            z = false;
        } else if (str4.indexOf("[b#") == 0) {
            bcolsp = new BackgroundColorSpan(Color.parseColor(str4.substring(2, str4.indexOf("m"))));
            z = false;
        } else {
            String night ="";
            switch (str4) {
                case "[30m":
                    colsp = new ForegroundColorSpan(Color.parseColor("#" + night + "000000"));
                    z = false;
                    break;
                case "[31m":
                    colsp = new ForegroundColorSpan(Color.parseColor("#" + night + "aa3300"));
                    z = false;
                    break;
                case "[32m":
                    colsp = new ForegroundColorSpan(Color.parseColor("#" + night + "00bb00"));
                    z = false;
                    break;
                case "[33m":
                    colsp = new ForegroundColorSpan(Color.parseColor("#" + night + "eeee00"));
                    z = false;
                    break;
                case "[34m":
                    colsp = new ForegroundColorSpan(Color.parseColor("#" + night + "0000aa"));
                    z = false;
                    break;
                case "[35m":
                    colsp = new ForegroundColorSpan(Color.parseColor("#" + night + "aa00aa"));
                    z = false;
                    break;
                case "[36m":
                    colsp = new ForegroundColorSpan(Color.parseColor("#" + night + "00bbbb"));
                    z = false;
                    break;
                case "[37m":
                    colsp = new ForegroundColorSpan(Color.parseColor("#" + night + "aaaaaa"));
                    z = false;
                    break;
                case "[1;30m":
                    hcolsp = new ForegroundColorSpan(Color.parseColor("#" + night + "000000"));
                    z = false;
                    break;
                case "[1;31m":
                    hcolsp = new ForegroundColorSpan(Color.parseColor("#" + night + "ff3300"));
                    z = false;
                    break;
                case "[1;32m":
                    hcolsp = new ForegroundColorSpan(Color.parseColor("#" + night + "88ff00"));
                    z = false;
                    break;
                case "[1;33m":
                    hcolsp = new ForegroundColorSpan(Color.parseColor("#" + night + "ffff00"));
                    z = false;
                    break;
                case "[1;34m":
                    hcolsp = new ForegroundColorSpan(Color.parseColor("#" + night + "0000ff"));
                    z = false;
                    break;
                case "[1;35m":
                    hcolsp = new ForegroundColorSpan(Color.parseColor("#" + night + "ff00ff"));
                    z = false;
                    break;
                case "[1;36m":
                    hcolsp = new ForegroundColorSpan(Color.parseColor("#" + night + "88ffff"));
                    z = false;
                    break;
                case "[1;37m":
                    hcolsp = new ForegroundColorSpan(Color.parseColor("#" + night + "ffffff"));
                    z = false;
                    break;
                case "[40m":
                    hbcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "222222"));
                    z = false;
                    break;
                case "[41m":
                    hbcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "aa0000"));
                    z = false;
                    break;
                case "[42m":
                    hbcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "00aa00"));
                    z = false;
                    break;
                case "[43m":
                    hbcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "aaaa00"));
                    z = false;
                    break;
                case "[44m":
                    hbcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "0000ff"));
                    z = false;
                    break;
                case "[45m":
                    hbcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "aa00aa"));
                    z = false;
                    break;
                case "[46m":
                    hbcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "00aaaa"));
                    z = false;
                    break;
                case "[47m":
                    hbcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "aaaaaa"));
                    z = false;
                    break;
                case "[40;1m":
                    bcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "000000"));
                    z = false;
                    break;
                case "[41;1m":
                    bcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "ff0000"));
                    z = false;
                    break;
                case "[42;1m":
                    bcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "00ff00"));
                    z = false;
                    break;
                case "[43;1m":
                    bcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "ffff00"));
                    z = false;
                    break;
                case "[44;1m":
                    bcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "0000ff"));
                    z = false;
                    break;
                case "[45;1m":
                    bcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "ff00ff"));
                    z = false;
                    break;
                case "[46;1m":
                    bcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "00ffff"));
                    z = false;
                    break;
                case "[47;1m":
                    bcolsp = new BackgroundColorSpan(Color.parseColor("#" + night + "ffffff"));
                    z = false;
                    break;
                default:
                    z = true;
                    break;
            }
        }
        if (str3.length() < 1) {
            spannableString = null;
        } else {
            String str6 = str3;
            if (!z) {
              /*  str3.length();
                str3.indexOf("m");*/
                str6 = str3.replace(str3.substring(0, str3.indexOf("m") + 1), "");
            }
            String str7 = str6;
            SpannableString spannableString2 = new SpannableString(str7);
            if (colsp != null) {
                spannableString2.setSpan(colsp, 0, spannableString2.length(), 33);
            }
            if (hcolsp != null) {
                spannableString2.setSpan(hcolsp, 0, spannableString2.length(), 33);
            }
            if (bcolsp != null) {
                spannableString2.setSpan(bcolsp, 0, spannableString2.length(), 33);
            }
            if (hbcolsp != null) {
                spannableString2.setSpan(hbcolsp, 0, spannableString2.length(), 33);
            }
                spannableString = spannableString2;

        }
        return spannableString;
    }}