package hubai.pkuxkx.mud;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

class PreferencesUtil {

    public static void setPre(Activity activity, final String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        prefs.edit().putString(key, value).apply();
    }

    public static void setPre(Activity activity, final String key, int value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        prefs.edit().putInt(key, value).apply();
    }

    public static void setPre(Activity activity, final String key, long value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        prefs.edit().putLong(key, value).apply();
    }

    public static void setPre(Activity activity, final String key, boolean value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        prefs.edit().putBoolean(key, value).apply();
    }

    public static String getPre(Activity activity, final String key, final String defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        String value = prefs.getString(key, defaultValue);
        return value;
    }

    public static int getPre(Activity activity, final String key, final int defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        int value = prefs.getInt(key, defaultValue);
        return value;
    }

    public static boolean getPre(Activity activity, final String key, final boolean defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        boolean value = prefs.getBoolean(key, defaultValue);
        return value;
    }
}
class TextUtil{
    public static String ToDBC(String text) {
        char[] c = text.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {// 全角空格为12288，半角空格为32
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)// 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

}
