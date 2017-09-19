package am.andranik.simplelist.utils;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

import am.andranik.simplelist.App;

/**
 * Created by andranik on 9/18/17.
 */

public class JsonUtils {

    @Nullable
    public static String read(String path) {
        String json = null;
        try {
            InputStream open = App.getInstance().getAssets().open(path);
            int available = open.available();
            byte[] buffer = new byte[available];
            open.read(buffer);
            open.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
