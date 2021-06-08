package util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void showLong(Context context, String message) {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }

    public static void showShort(Context context, String message) {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }

    private static void showToast(Context context, String message, int duration) {
        Toast.makeText(context,message,duration).show();
    }
}
