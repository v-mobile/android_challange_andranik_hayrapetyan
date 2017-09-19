package am.andranik.simplelist;

import android.app.Application;

/**
 * Created by andranik on 9/18/17.
 */

public class App extends Application {

    private static App instance;

    public App() {
        super();
    }

    public static App getInstance() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
