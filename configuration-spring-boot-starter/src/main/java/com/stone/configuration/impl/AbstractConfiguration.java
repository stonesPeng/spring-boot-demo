package com.stone.configuration.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.configuration.Configuration;
import com.stone.configuration.exception.ConfigurationException;
import com.stone.configuration.utils.Assert;
import com.stone.configuration.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

/**
 * @author yyang
 * @date 2019/3/18 22:21
 */
public abstract class AbstractConfiguration implements Configuration {
    private static final Logger log = LoggerFactory.getLogger(AbstractConfiguration.class);

    private String dateFormat = "yyyy-MM-dd";
    private String prefix = "";

    protected Hashtable<String, String> hTable;

    private PropertiesFileUtils pfu = new PropertiesFileUtils("utf-8");

    private ObjectMapper mapper = new ObjectMapper();


    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * 激活配置前缀功能
     *
     * @param prefix 如"org.dayatang.mes."
     */
    public void usePrefix(final String prefix) {
        if (StringUtils.isNotBlank(prefix)) {
            this.prefix = prefix.endsWith(".") ? prefix : prefix + ".";
        }
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.Configuration#getString(java.lang.String, java.lang.String)
     */
    @Override
    public String getString(String key, String defaultValue) {
        Assert.notBlank(key, "Key is null or empty!");
        String result = getHashtable().get(key);
        if (result == null) {
            result = (String) getHashtable().get(prefix + key);
        }
        return result == null ? defaultValue : result;
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.Configuration#getString(java.lang.String)
     */
    @Override
    public String getString(String key) {
        return getString(key, "");
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.WritableConfiguration#setString(java.lang.String, java.lang.String)
     */
    @Override
    public void setString(String key, String value) {
        Assert.notBlank(key, "Key is null or empty!");
        if (StringUtils.isBlank(value)) {
            getHashtable().remove(key);
            return;
        }
        getHashtable().put(key, StringPropertyReplacer.replaceProperties(value));
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.Configuration#getInt(java.lang.String, int)
     */
    @Override
    public int getInt(String key, int defaultValue) {
        String result = getString(key, String.valueOf(defaultValue));
        return StringUtils.isBlank(result) ? defaultValue : Integer.parseInt(result);
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.Configuration#getInt(java.lang.String)
     */
    @Override
    public int getInt(String key) {
        return getInt(key, 0);
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.WritableConfiguration#setInt(java.lang.String, int)
     */
    @Override
    public void setInt(String key, int value) {
        setString(key, String.valueOf(value));
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.Configuration#getLong(java.lang.String, long)
     */
    @Override
    public long getLong(String key, long defaultValue) {
        String result = getString(key, String.valueOf(defaultValue));
        return StringUtils.isBlank(result) ? defaultValue : Long.parseLong(result);
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.Configuration#getLong(java.lang.String)
     */
    @Override
    public long getLong(String key) {
        return getLong(key, 0);
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.WritableConfiguration#setLong(java.lang.String, long)
     */
    @Override
    public void setLong(String key, long value) {
        setString(key, String.valueOf(value));
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.Configuration#getDouble(java.lang.String, double)
     */
    @Override
    public double getDouble(String key, double defaultValue) {
        String result = getString(key, String.valueOf(defaultValue));
        return StringUtils.isBlank(result) ? defaultValue : Double.parseDouble(result);
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.Configuration#getDouble(java.lang.String)
     */
    @Override
    public double getDouble(String key) {
        return getDouble(key, 0);
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.WritableConfiguration#setDouble(java.lang.String, double)
     */
    @Override
    public void setDouble(String key, double value) {
        setString(key, String.valueOf(value));
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.Configuration#getBoolean(java.lang.String, boolean)
     */
    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        String result = getString(key, String.valueOf(defaultValue));
        return StringUtils.isBlank(result) ? defaultValue : Boolean.parseBoolean(result);
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.Configuration#getBoolean(java.lang.String)
     */
    @Override
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.WritableConfiguration#setBoolean(java.lang.String, boolean)
     */
    @Override
    public void setBoolean(String key, boolean value) {
        setString(key, String.valueOf(value));
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.Configuration#getDate(java.lang.String, java.util.Date)
     */
    @Override
    public Date getDate(String key, Date defaultValue) {
        String result = getString(key);
        try {
            return StringUtils.isBlank(result) ? defaultValue : new SimpleDateFormat(dateFormat).parse(result);
        } catch (ParseException e) {
            throw new ConfigurationException("日期解析错误！日期格式是：" + dateFormat + ", 日期：" + result, e);
        }
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.Configuration#getDate(java.lang.String)
     */
    @Override
    public Date getDate(String key) {
        return getDate(key, (Date) null);
    }

    /* (non-Javadoc)
     * @see org.dayatang.configuration.WritableConfiguration#setDate(java.lang.String, java.util.Date)
     */
    @Override
    public void setDate(String key, Date value) {
        if (value == null) {
            setString(key, "");
        }
        setString(key, new SimpleDateFormat(dateFormat).format(value));
    }

    @Override
    public <T> T getObject(String key, Class<T> objectClass, T defaultValue) {
        try {
            T result = mapper.readValue(getString(key), objectClass);
            return result == null ? defaultValue : result;
        } catch (IOException e) {
            log.error("json to object fail when using jackson");
        }
        return defaultValue;
    }

    @Override
    public <T> T getObject(String key, Class<T> objectClass) {
        return getObject(key, objectClass, null);
    }

    @Override
    public void setObject(String key, Object value) {
        try {
            setString(key, mapper.writeValueAsString(value));
        } catch (JsonProcessingException e) {
            log.error("object to json fail when using jackson");
        }
    }

    @Override
    public Properties getProperties() {
        return pfu.unRectifyProperties(getHashtable());
    }

    private Hashtable<String, String> getHashtable() {
        if (hTable == null) {
            load();
        }
        return hTable;
    }
}
