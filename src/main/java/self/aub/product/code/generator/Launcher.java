package self.aub.product.code.generator;

import org.apache.commons.io.FileUtils;
import self.aub.product.code.generator.bean.Table;
import self.aub.product.code.generator.config.GeneratorConfig;
import self.aub.product.code.generator.config.VelocityConfig;
import self.aub.product.code.generator.generator.db.MysqlGenerator;
import self.aub.product.code.generator.reader.DbReader;
import self.aub.product.code.generator.reader.DbReaderFactory;

import java.io.File;
import java.util.List;

public class Launcher {

    private Launcher() {
    }

    private static void launch() throws Exception {
        // 初始化配置
        GeneratorConfig.init();
        VelocityConfig.init();

        FileUtils.cleanDirectory(new File(GeneratorConfig.getOutputDir()));

        // 读取数据
        DbReader dbReader = DbReaderFactory.createDbReader();
        List<Table> tables = dbReader.getTableBeans();
        // 获取生成器
        MysqlGenerator mysqlGenerator = new MysqlGenerator();
        for (Table table : tables) {
            // 处理每个表
            mysqlGenerator.generate(table);
        }

        // dbGenerator.generateRes(tables);

        // 生成工具类
        // ScaffoldingGenerator.generateToolClass();
    }

    public static void main(String[] args) throws Exception {
        launch();
    }
}
