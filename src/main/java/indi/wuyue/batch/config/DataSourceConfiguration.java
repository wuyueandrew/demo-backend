package indi.wuyue.batch.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DataSourceConfiguration {

    @Value("${spring.datasource.local.url}")
    private String localUrl;

    @Value("${spring.datasource.local.username}")
    private String localUsername;

    @Value("${spring.datasource.local.password}")
    private String localPassword;

    private DruidDataSource createDefault() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setFilters("stat");
        druidDataSource.setMaxActive(20);
        druidDataSource.setInitialSize(1);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setMinIdle(1);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setValidationQuery("SELECT 'x'");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return druidDataSource;
    }

    @Bean(name = "localDataSource", destroyMethod = "close")
    public DruidDataSource sysDataSource() throws Exception {
        DruidDataSource druidDataSource = createDefault();

        druidDataSource.setUrl(localUrl);
        druidDataSource.setUsername(localUsername);
        druidDataSource.setPassword(localPassword);

        return druidDataSource;
    }


}
