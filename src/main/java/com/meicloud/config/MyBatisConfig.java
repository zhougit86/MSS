package com.meicloud.config;

import com.github.pagehelper.PageHelper;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.meicloud.dao"})
public class MyBatisConfig implements TransactionManagementConfigurer {

   private static Logger LOG = Logger.getLogger("MyBatisConfig");
   @Autowired
   DataSource dataSource;


   @Bean(
      name = {"sqlSessionFactory"}
   )
   public SqlSessionFactory sqlSessionFactoryBean() {
      SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
      bean.setDataSource(this.dataSource);
      bean.setTypeAliasesPackage("com.meicloud.model");
      PageHelper pageHelper = new PageHelper();
      Properties props = new Properties();
      props.setProperty("reasonable", "true");
      props.setProperty("supportMethodsArguments", "true");
      props.setProperty("returnPageInfo", "check");
      props.setProperty("params", "count=countSql");
      pageHelper.setProperties(props);
      bean.setPlugins(new Interceptor[]{pageHelper});
      PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

      try {
         bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
         return bean.getObject();
      } catch (Exception var6) {
         LOG.error(var6.getMessage());
         throw new RuntimeException(var6);
      }
   }

   @Bean
   public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
      return new SqlSessionTemplate(sqlSessionFactory);
   }

   @Bean
   public PlatformTransactionManager annotationDrivenTransactionManager() {
      return new DataSourceTransactionManager(this.dataSource);
   }
}
