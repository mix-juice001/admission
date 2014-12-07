package admission;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Driver;


@Configuration
@MapperScan(basePackages = "admission.repository")
public class DataBaseConfig {

    @Bean
    public DataSource dataSource() {
        /* FIXME H2のPostgreSQLモードを使うためにURLにMODE=PostgreSQLを付与したいだけの割に大げさ */
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setDataSourceFactory(new DataSourceFactory() {
                    private SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

                    @Override
                    public ConnectionProperties getConnectionProperties() {
                        return new ConnectionProperties() {
                            @Override
                            public void setDriverClass(Class<? extends Driver> driverClass) {
                                dataSource.setDriverClass(driverClass);
                            }

                            @Override
                            public void setUrl(String url) {
                                dataSource.setUrl(url + ";MODE=PostgreSQL");
                            }

                            @Override
                            public void setUsername(String username) {
                                dataSource.setUsername(username);
                            }

                            @Override
                            public void setPassword(String password) {
                                dataSource.setPassword(password);
                            }
                        };
                    }

                    @Override
                    public DataSource getDataSource() {
                        return this.dataSource;
                    }
                })
                .addScript("classpath:/ddl/schema.sql")
                .addScript("classpath:/ddl/data.sql")
                .setScriptEncoding("UTF-8")
                .build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(new ClassPathResource("/META-INF/mybatis/configuration.xml"));
        return sessionFactory.getObject();
    }

}
