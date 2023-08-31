package com.sandro.config.autoconfig;

import com.sandro.config.MyConfigurationProperties;
import lombok.Data;

@Data
@MyConfigurationProperties(prefix = "server")
public class ServerProperties {
    private String contextPath;
    private int port;
}
