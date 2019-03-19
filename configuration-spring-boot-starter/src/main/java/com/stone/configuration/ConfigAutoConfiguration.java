package com.stone.configuration;


import com.stone.configuration.impl.ConfigurationFactoryImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author honorstone
 * @date 2019/3/19 12:25
 */
@Configuration
public class ConfigAutoConfiguration {
    public ConfigAutoConfiguration(){

    }

    @Bean
    @ConditionalOnMissingBean(name ="xingqiaoConfigurationFactory" )
    public ConfigurationFactory configurationService(){
        return new ConfigurationFactoryImpl();
    }
}
