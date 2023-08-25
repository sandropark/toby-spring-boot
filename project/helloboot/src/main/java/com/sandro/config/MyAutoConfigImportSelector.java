package com.sandro.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "com.sandro.config.autoconfig.DispatcherServletConfig",
                "com.sandro.config.autoconfig.TomcatWebServerConfig",
        };
    }
}
