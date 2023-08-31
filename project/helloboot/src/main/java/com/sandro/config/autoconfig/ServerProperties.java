package com.sandro.config.autoconfig;

import lombok.Data;

@Data
public class ServerProperties {
    private String contextPath;
    private int port;
}
