package com.kd.execute.test;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Adil
 * @date 2020-04-14 13:53:20
 * 属性读取
 */
@Component//注入springIOC容器
@ConfigurationProperties(prefix = "spring")//设置前缀与yml中配置的属性前缀一致
public class DBProperties {
    private Map<String, String> datasource = new HashMap<>();//Map集合的名称要与yml配置文件中配置的属性名保持一致

    public Map<String, String> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, String> datasource) {
        this.datasource = datasource;
    }
}
