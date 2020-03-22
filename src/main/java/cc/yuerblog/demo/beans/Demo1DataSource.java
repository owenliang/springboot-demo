package cc.yuerblog.demo.beans;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(value="cc.yuerblog.demo.daos.demo1", sqlSessionFactoryRef = "demo1SqlSessionFactory")
public class Demo1DataSource {
    // 连接池bean已经就位
    @Bean(name="demo1DS")
    @ConfigurationProperties(prefix = "spring.datasource.demo1")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="demo1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("demo1DS") DataSource ds) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(ds);
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/demo1/*.xml"));
        return factory.getObject();
    }
}



