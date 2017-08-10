package self.aub.product.code.generator.config;

import self.aub.product.code.generator.util.Constant.DbType;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class GeneratorConfig {
    private static final HashMap<String, String> CONFIG = new HashMap<>();

    private static final String CONFIG_FILE_PATH = "config";

    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_TYPE = "db.type";
    private static final String DB_DRIVER = "db.driver";

    private static final String TABLE_PREFIX = "table.prefix";
    private static final String BASE_PACKAGE = "package.base";

    private static final String SCHEMA = "template";

    private static final String OUTPUT_DIR = "output.dir";

    public static void init() {
        ResourceBundle resourceBundle = PropertyResourceBundle.getBundle(CONFIG_FILE_PATH);
        for (String key : resourceBundle.keySet()) {
            String value = resourceBundle.getString(key);
            CONFIG.put(key, value);
        }
    }

    public static DbType getDbType() {
        String dbTypeStr = CONFIG.get(DB_TYPE);
        return DbType.valueOf(dbTypeStr);
    }

    public static String getDbDriver() {
        return CONFIG.get(DB_DRIVER);
    }

    public static String getDbUrl() {
        return CONFIG.get(DB_URL);
    }

    public static String getDbUserName() {
        return CONFIG.get(DB_USERNAME);
    }

    public static String getDbPassword() {
        return CONFIG.get(DB_PASSWORD);
    }

    public static String getTablePrefix() {
        return CONFIG.get(TABLE_PREFIX);
    }

    public static String getBasePackage() {
        return CONFIG.get(BASE_PACKAGE);
    }

    public static String getOutputDir() {
        return CONFIG.get(OUTPUT_DIR);
    }

    public static String getSchema() {
        return CONFIG.get(SCHEMA);
    }

    public static void set(String key, String value) {
        CONFIG.put(key, value);
    }

    public static void main(String[] args) {
        init();
        for (Entry<String, String> entry : CONFIG.entrySet()) {
            System.out.print("[" + entry.getKey() + "] = ");
            System.out.println("[" + entry.getValue() + "]");
        }
    }
}
