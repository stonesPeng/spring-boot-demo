package com.stone.configuration.impl;

import com.stone.configuration.Configuration;
import com.stone.configuration.ConfigurationFactory;
import com.stone.configuration.WritableConfiguration;

import com.stone.configuration.exception.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author yyang
 * @date 2019/3/18 22:24
 */
public class ConfigurationFactoryImpl extends ConfigurationFactory {

    private static final Logger log = LoggerFactory.getLogger(ConfigurationFactoryImpl.class);

    /**
     * 从JDBC数据源中读取配置
     * @param dataSource 数据源
     * @return 可写的配置
     */
    @Override
    public WritableConfiguration fromDatabase(DataSource dataSource) {
        return new ConfigurationDbImpl(dataSource);
    }

    /**
     * 从JDBC数据源中读取配置
     * @param dataSource 数据源
     * @param tableName 存放配置信息的数据表名
     * @param keyColumn 存放配置键的列名
     * @param valueColumn 存放配置值的列名
     * @return 可写的配置
     */
    @Override
    public WritableConfiguration fromDatabase(DataSource dataSource, String tableName, String keyColumn, String valueColumn) {
        return new ConfigurationDbImpl(dataSource, tableName, keyColumn, valueColumn);
    }

    /**
     * 从类路径文件中读取配置
     * @param fileName 类路径资源文件名
     * @return 可写的配置
     */
    @Override
    public Configuration fromClasspath(String fileName) {
        InputStream in = getClass().getResourceAsStream(fileName);
        return new ConfigurationInputStreamImpl(in);
    }

    /**
     * 从磁盘文件中读取配置
     * @param fileName 磁盘文件名
     * @return 可写的配置
     */
    @Override
    public WritableConfiguration fromFileSystem(String fileName) {
        return new ConfigurationFileImpl(fileName);
    }

    /**
     * 从磁盘文件中读取配置
     * @param dirPath 文件所属的目录
     * @param fileName 文件名
     * @return 可写的配置
     */
    @Override
    public WritableConfiguration fromFileSystem(String dirPath, String fileName) {
        return new ConfigurationFileImpl(dirPath, fileName);
    }

    /**
     * 从磁盘文件中读取配置
     * @param file 包含配置信息的文件
     * @return 可写的配置
     */
    @Override
    public WritableConfiguration fromFileSystem(File file) {
        return ConfigurationFileImpl.fromFile(file);
    }

    /**
     * 从远程url读取配置
     * @param url 包含配置信息的url
     * @return 只读的配置
     */
    @Override
    public Configuration fromUrl(String url) {
        try {
            return fromUrl(new URL(url));
        } catch (MalformedURLException e) {
            log.error("url is not correct!");
            throw new ConfigurationException("url is not correct!");
        }

    }

    /**
     * 从远程url读取配置
     * @param url 包含配置信息的url
     * @return 只读的配置
     */
    @Override
    public Configuration fromUrl(URL url) {
        if (url == null) {
            throw new ConfigurationException("url is null!");
        }
        try {
            return new ConfigurationInputStreamImpl(url.openStream());
        } catch (IOException e) {
            log.error("read url failure!");
            throw new ConfigurationException("read url failure!");
        }
    }


    @Override
    public Configuration fromInputStream(InputStream in) {
        return new ConfigurationInputStreamImpl(in);
    }
}
