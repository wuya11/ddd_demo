package com.ddd.demo.configuration.mysql;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;


/**
 * 自定义的sqlSessionFactoryBean
 *
 * @author wl
 * @date 2021-3-9
 */
public class DemoSqlSessionFactoryBean extends SqlSessionFactoryBean implements EnvironmentAware {
    private Interceptor[] plugins;
    public static Configuration CONFIGURATION;
    private boolean slowSqlEnabled = false;

    public DemoSqlSessionFactoryBean() {
        this(null);
    }

    public DemoSqlSessionFactoryBean(Configuration configuration) {
        super();
        if (configuration == null) {
            configuration = new Configuration();
            configuration.setMapUnderscoreToCamelCase(true);
            configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
        }
        CONFIGURATION = configuration;
        setConfiguration(configuration);
    }

    @Override
    public void setPlugins(Interceptor[] plugins) {
        this.plugins = plugins;
    }

    /**
     * 真实执行设置插件,setPlugins只用于记录客户端自定义的plugin,便于后续拷贝
     */
    private void actualSetPlugins() {
    }

    @Override
    public void setEnvironment(Environment environment) {
        actualSetPlugins();
    }
}
