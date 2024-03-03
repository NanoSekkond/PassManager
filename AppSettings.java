import java.util.prefs.Preferences;

public class AppSettings {

    private static final String PASSWORD_KEY = "passwordSet";
    private static final String PASSWORD = "passwordSave";
    private static final Preferences preferences = Preferences.userNodeForPackage(AppSettings.class);

    public static boolean isPasswordSet() {
        return preferences.getBoolean(PASSWORD_KEY, false);
    }

    public static void setPassword(String password) {
        try {
            preferences.putBoolean(PASSWORD_KEY, true);
            preferences.put(PASSWORD, Storage.calculateSHA256(password));
        } catch (Exception e) {
        }
    }

    public static String getPassword() {
        return preferences.get(PASSWORD, "");
    }
}
