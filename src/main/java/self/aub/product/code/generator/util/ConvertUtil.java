package self.aub.product.code.generator.util;

import java.sql.Types;

public class ConvertUtil {

    /**
     * <B>将数据库命名转换为骆驼命名</B><br>
     * 例如，“sys_user” 转换为 “SysUser”
     *
     * @param nameInDb
     * @return
     * @author aub
     */
    public static String convert2CamelCase(String nameInDb) {
        StringBuilder camelCaseName = new StringBuilder();
        String[] camelCaseNamePartArr = nameInDb.split(Constant.SIGN_UNDERLINE);
        for (String enumNamePart : camelCaseNamePartArr) {
            if (enumNamePart.length() == 0) {
                continue;
            }
            char firstChar = enumNamePart.charAt(0);
            firstChar = toUpperCase(firstChar);
            camelCaseName.append(firstChar);
            if (enumNamePart.length() > 1) {
                camelCaseName.append(enumNamePart.substring(1));
            }
        }
        return camelCaseName.toString();
    }

    /**
     * <B>将骆驼命名转换为变量名</B><br>
     * 例如, “SysUser” 装换为 “sysUser”
     *
     * @param camelCaseName
     * @return
     * @author aub
     */
    public static String convert2VariableName(String camelCaseName) {
        char firstChar = camelCaseName.charAt(0);
        firstChar = toLowerCase(firstChar);
        StringBuilder result = new StringBuilder();
        result.append(firstChar);
        if (camelCaseName.length() > 1) {
            result.append(camelCaseName.substring(1));
        }
        return result.toString();
    }

    /**
     * <B>将数据库命名转换为url路径</B><br>
     * 例如, “sys_user” 装换为 “sys/user”
     *
     * @param nameInDb
     * @return
     * @author aub
     */
    public static String convert2RequestMapping(String nameInDb) {
        StringBuilder requestMapping = new StringBuilder();
        String[] requestMappingArr = nameInDb.split(Constant.SIGN_UNDERLINE);
        for (String requestMappingPart : requestMappingArr) {
            if (requestMappingPart.length() == 0) {
                continue;
            }
            requestMapping.append(requestMappingPart);
            requestMapping.append(Constant.SIGN_SLASH);
        }
        return requestMapping.toString();
    }

    /**
     * <B>将数据库类型转换为java类型</B><br>
     *
     * @param dbDateType
     * @return
     * @author aub
     */
    public static String convert2JavaType(int dbDateType) {
        String javaType;
        switch (dbDateType) {
            case Types.TINYINT:
                javaType = "Short";
                break;
            case Types.SMALLINT:
                javaType = "Integer";
                break;
            case Types.INTEGER:
                javaType = "Integer";
                break;
            case Types.BIGINT:
                javaType = "Long";
                break;
            case Types.FLOAT:
                javaType = "BigDecimal";
                break;
            case Types.DOUBLE:
                javaType = "BigDecimal";
                break;
            case Types.CHAR:
                javaType = "String";
                break;
            case Types.VARCHAR:
                javaType = "String";
                break;
            case Types.TIME:
                javaType = "LocalDateTime";
                break;
            case Types.DATE:
                javaType = "LocalDateTime";
                break;
            case Types.TIMESTAMP:
                javaType = "LocalDateTime";
                break;
            case Types.BOOLEAN:
                javaType = "Boolean";
                break;
            default:
                javaType = "String";
                break;
        }
        return javaType;
    }

    private static char toUpperCase(char c) {
        if (c >= 'a' && c <= 'z') {
            c = (char) (c - 32);
        }
        return c;
    }

    private static char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            c = (char) (c + 32);
        }
        return c;
    }
}
