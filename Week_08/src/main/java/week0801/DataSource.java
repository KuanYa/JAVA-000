package week0801;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DataSource {

    public static void main(String[] args) {
        // 创建 ShardingSphereDataSource
        File file = new File("D:\\Java-training-camp\\JAVA-000\\JAVA-000\\Week_08\\src\\main\\resources\\shardingsphere-jdbc.yml");
        DataSource dataSource;
        {
            try {
                dataSource = (DataSource) YamlShardingSphereDataSourceFactory.createDataSource(file);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


