package self.aub.product.code.generator.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import self.aub.product.code.generator.bean.Column;
import self.aub.product.code.generator.config.GeneratorConfig;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MysqlDbReader extends AbstractDbReader {
    private static final Logger LOG = LoggerFactory.getLogger(MysqlDbReader.class);

    /**
     * <B>获取当前库所有表名</B><br>
     * 
     * @author aub
     * @return
     */
    @Override
    public List<String> getTableNames() {
        ArrayList<String> tableNames = new ArrayList<String>();
        String tablePrefix = GeneratorConfig.getTablePrefix();
        Connection conn = getConn();
        ResultSet tableNamesRS = null;
        String[] types = { "TABLE", "VIEW" };
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            tableNamesRS = dbmd.getTables(null, null, null, types);
            while (tableNamesRS.next()) {
                String tableName = tableNamesRS.getString("TABLE_NAME");
                if (tableName.startsWith(tablePrefix)) {
                    tableNames.add(tableName);
                    LOG.debug("get table : {}", tableName);
                }
            }
        } catch (SQLException e) {
            LOG.error("your log info, exception : ", e);
        } finally {
            closeRS(tableNamesRS);
            closeConn(conn);
        }
        return tableNames;
    }

    /**
     * <B>获取表的所有列</B><br>
     * 
     * @author aub
     * @param tableName
     * @return
     */
    @Override
    public Map<String, Column> getColumns(String tableName) {
        Map<String, Column> cloums = new LinkedHashMap<String, Column>();
        Connection conn = getConn();
        ResultSet columnNamesRS = null;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            columnNamesRS = dbmd.getColumns(null, null, tableName, null);
            while (columnNamesRS.next()) {
                String columnName = columnNamesRS.getString("COLUMN_NAME");
                int dateType = columnNamesRS.getInt("DATA_TYPE");
                Column column = new Column(columnName, dateType);
                cloums.put(columnName, column);
                LOG.debug("get table column, table : {}, column : {}", tableName, columnName);
            }
        } catch (SQLException e) {
            LOG.error("your log info, exception : ", e);
        } finally {
            closeRS(columnNamesRS);
            closeConn(conn);
        }
        return cloums;
    }

    /**
     * <B>获取表的主键</B><br>
     * 
     * @author aub
     * @param tableName
     * @return primary key name
     */
    @Override
    public String getPrimaryKeyName(String tableName) {
        Connection conn = getConn();
        ResultSet resultSet = null;
        try {
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            resultSet = databaseMetaData.getPrimaryKeys(null, null, tableName);
            while (resultSet.next()) {
                String primaryKeyName = resultSet.getString("COLUMN_NAME");
                LOG.debug("get table primaryKey, table : {}, primaryKey : {}", tableName, primaryKeyName);
                return primaryKeyName;
            }
        } catch (SQLException e) {
            LOG.error("your log info, exception : ", e);
        } finally {
            closeRS(resultSet);
            closeConn(conn);
        }
        return null;
    }
}
