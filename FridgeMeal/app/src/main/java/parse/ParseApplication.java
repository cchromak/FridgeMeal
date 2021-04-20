package parse;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
        .applicationId("25HWFAlMYWFHlf77cYScUCm54RbLijH0oqjFcgen")
        .clientKey("YO65r8PckVJSlRQKwSF28V21HJnUIOGBzwZ8WOcy")
        .server("https://parseapi.back4app.com")
        .build());
    }
}
