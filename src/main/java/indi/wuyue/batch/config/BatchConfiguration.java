package indi.wuyue.batch.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    public BatchConfigurer batchConfigurer(DruidDataSource localDatasource,
                                           PlatformTransactionManager transactionManager) {
        return new DefaultBatchConfigurer() {
            @Override
            protected JobRepository createJobRepository() throws Exception {
                JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
                factory.setDataSource(localDatasource);
                factory.setDatabaseType("MySQL");
                factory.setTransactionManager(transactionManager);
                factory.setIsolationLevelForCreate("ISOLATION_SERIALIZABLE");
                factory.setTablePrefix("BATCH_");
                factory.setMaxVarCharLength(1000);
                return factory.getObject();
            }

        };
    }

}
