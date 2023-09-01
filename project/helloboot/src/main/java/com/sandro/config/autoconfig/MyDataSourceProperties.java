package com.sandro.config.autoconfig;

import com.sandro.config.MyConfigurationProperties;
import lombok.Data;

@Data
@MyConfigurationProperties(prefix = "data")
public class MyDataSourceProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
