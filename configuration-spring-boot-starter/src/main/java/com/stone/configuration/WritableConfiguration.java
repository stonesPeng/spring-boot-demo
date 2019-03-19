package com.stone.configuration;


/**
 * 用于读写全局性配置信息的接口
 * @author yyang
 * @date 2019/3/18 22:24
 *
 */
public interface WritableConfiguration extends Configuration {
	/**
	 * 持久化配置信息。
	 */
	void save();

}