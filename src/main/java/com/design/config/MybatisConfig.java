package com.design.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Properties;

public class MybatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        //配置分页插件，详情请查阅官方文档
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("pageSizeZero", "true");//分页尺寸为0时查询所有纪录不再执行分页
        properties.setProperty("reasonable", "true");//页码<=0 查询第一页，页码>=总页数查询最后一页
        properties.setProperty("supportMethodsArguments", "true");//支持通过 Mapper 接口参数来传递分页参数
        pageHelper.setProperties(properties);

        //添加插件
        factory.setPlugins(new Interceptor[]{(Interceptor) pageHelper});
        factory.setTypeAliasesPackage("com.design.domain");
        return factory.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.design.dao");
        return msc;
    }



}
