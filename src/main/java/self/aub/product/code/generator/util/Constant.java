package self.aub.product.code.generator.util;

public class Constant {

    public static final String NOT = "0";
    public static final String YES = "1";

    public static final String SIGN_DOT = ".";
    public static final String SIGN_SLASH = "/";
    public static final String SIGN_UNDERLINE = "_";
    public static final String JAVA_FILE_SUFFIX = ".java";


    public static final String SOURCE_JAVA = "src/main/java/";
    public static final String SOURCE_RESOURCE = "src/main/resources/";
    public static final String SOURCE_RESOURCE_MAPPER = "src/main/resources/mapper/";


    public enum DbType {
        MYSQL, ORACLE
    }

    public enum LayerInfo {
        CONTROLLER("web", "Controller"),
        SERVICE("service", "Service"),
        DTO_REQUEST("dto.request", "RequestDto"),
        DTO_RESPONSE("dto.response", "ResponseDto"),
        ENTITY("domain", ""),
        REPOSITORY("domain", "Repository");

        private final String packageName;
        private final String suffix;
        LayerInfo(String packageName, String suffix) {
            this.packageName = packageName;
            this.suffix = suffix;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public String getPackageFilePath() {
            return this.packageName.replace(SIGN_DOT, SIGN_SLASH);
        }

        public String getSuffix() {
            return this.suffix;
        }
    }
}
