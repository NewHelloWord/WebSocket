package com.yl;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by JerryMouse on 2016/11/1.
 */
public class SystemConfig {

    //private Logger log = LoggerFactory.getLogger(SystemConfig.class);

    private String configFile;
    private Properties property;

    public void init() {
        try {
            this.property = PropertiesLoaderUtils.loadAllProperties(this.configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBasePath() {
        String host = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getHeader("host");

        String basePath = null;
        if (StringUtils.isEmpty(host)) {
            //this.log.info("host isEmpty");
            basePath = this.property.getProperty("basePath");
        } else if ((host.contains("192.168.")) || (host.contains("127.0.0.1"))|| (host.contains("localhost"))) {
            basePath = this.property.getProperty("basePath_internal");
        } else {
            basePath = this.property.getProperty("basePath");
        }
        return basePath;
    }

    public String getString(String key) {
        return this.property.getProperty(key);
    }

    public Integer getInt(String key) {
        String value = this.property.getProperty(key);
        return StringUtils.isEmpty(value) ? null : Integer.valueOf(value);
    }

    public Double getDouble(String key) {
        String value = this.property.getProperty(key);
        return StringUtils.isEmpty(value) ? null : Double.valueOf(value);
    }

    public void setValue(String key, String value) {
        this.property.setProperty(key, value);
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

}
