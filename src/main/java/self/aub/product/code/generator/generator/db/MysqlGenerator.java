package self.aub.product.code.generator.generator.db;

import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import self.aub.product.code.generator.bean.Table;
import self.aub.product.code.generator.config.GeneratorConfig;
import self.aub.product.code.generator.generator.Generator;

import java.io.File;

import static self.aub.product.code.generator.util.Constant.*;
import static self.aub.product.code.generator.util.Constant.LayerInfo.*;

public class MysqlGenerator extends Generator {
    private static final Logger LOG = LoggerFactory.getLogger(MysqlGenerator.class);

    public void generate(Table table) {

        String tableNameDb = table.getTableNameDb();
        LOG.info("generate code file for table : {} ", tableNameDb);

        VelocityContext context = new VelocityContext();
        context.put("table", table);

        generateLayer(table, context, ENTITY);
        generateLayer(table, context, REPOSITORY);
        generateLayer(table, context, SERVICE);
        generateLayer(table, context, CONTROLLER);

        generateRequestDtoLayer(table, context);
        generateResponseDtoLayer(table, context);
    }

    public void generateLayer(Table table, VelocityContext context, LayerInfo layerInfo) {
        String packageName = GeneratorConfig.getBasePackage() + SIGN_DOT + layerInfo.getPackageName() + SIGN_DOT + table.getModelName();

        context.put("packageName", packageName);
        context.put("className", table.getTableNameClass() + layerInfo.getSuffix());

        // 输出路径
        String outputDir = GeneratorConfig.getOutputDir() + SOURCE_JAVA + packageName.replace(SIGN_DOT, SIGN_SLASH) + SIGN_SLASH;
        new File(outputDir).mkdirs();
        String filePath = outputDir + table.getTableNameClass() + layerInfo.getSuffix() + JAVA_FILE_SUFFIX;
        write2FileBySchema(SIGN_SLASH + layerInfo.getPackageFilePath() + SIGN_SLASH + layerInfo.getSuffix() + "Temp.vm", context, filePath);
    }

    public void generateRequestDtoLayer(Table table, VelocityContext context) {
        String packageName = GeneratorConfig.getBasePackage() + SIGN_DOT + DTO_REQUEST.getPackageName() + SIGN_DOT + table.getModelName();

        context.put("packageName", packageName);
        context.put("className", table.getTableNameClass() + DTO_REQUEST.getSuffix());

        // 输出路径
        String outputDir = GeneratorConfig.getOutputDir() + SOURCE_JAVA + packageName.replace(SIGN_DOT, SIGN_SLASH) + SIGN_SLASH;
        new File(outputDir).mkdirs();
        String filePath = outputDir + "Create" + table.getTableNameClass() + DTO_REQUEST.getSuffix() + JAVA_FILE_SUFFIX;
        write2FileBySchema(SIGN_SLASH + DTO_REQUEST.getPackageFilePath() + SIGN_SLASH + "Create" + DTO_REQUEST.getSuffix() + "Temp.vm", context, filePath);

        filePath = outputDir + "Update" + table.getTableNameClass() + DTO_REQUEST.getSuffix() + JAVA_FILE_SUFFIX;
        write2FileBySchema(SIGN_SLASH + DTO_REQUEST.getPackageFilePath() + SIGN_SLASH + "Update" + DTO_REQUEST.getSuffix() + "Temp.vm", context, filePath);

        filePath = outputDir + "List" + table.getTableNameClass() + DTO_REQUEST.getSuffix() + JAVA_FILE_SUFFIX;
        write2FileBySchema(SIGN_SLASH + DTO_REQUEST.getPackageFilePath() + SIGN_SLASH + "List" + DTO_REQUEST.getSuffix() + "Temp.vm", context, filePath);
    }

    public void generateResponseDtoLayer(Table table, VelocityContext context) {
        String packageName = GeneratorConfig.getBasePackage() + SIGN_DOT + DTO_RESPONSE.getPackageName() + SIGN_DOT + table.getModelName();

        context.put("packageName", packageName);
        context.put("className", table.getTableNameClass() + DTO_RESPONSE.getSuffix());

        // 输出路径
        String outputDir = GeneratorConfig.getOutputDir() + SOURCE_JAVA + packageName.replace(SIGN_DOT, SIGN_SLASH) + SIGN_SLASH;
        new File(outputDir).mkdirs();

        String filePath = outputDir + "Get" + table.getTableNameClass() + DTO_RESPONSE.getSuffix() + JAVA_FILE_SUFFIX;
        write2FileBySchema(SIGN_SLASH + DTO_RESPONSE.getPackageFilePath() + SIGN_SLASH + "Get" + DTO_RESPONSE.getSuffix() + "Temp.vm", context, filePath);

        filePath = outputDir + "List" + table.getTableNameClass() + DTO_RESPONSE.getSuffix() + JAVA_FILE_SUFFIX;
        write2FileBySchema(SIGN_SLASH + DTO_RESPONSE.getPackageFilePath() + SIGN_SLASH + "List" + DTO_RESPONSE.getSuffix() + "Temp.vm", context, filePath);
    }

}
