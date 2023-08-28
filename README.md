# 스프링 부트

## 자동 구성 애노테이션

- `@EnableMyAutoConfiguration`을 만든다. 
    ```java
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Import(MyAutoConfigImportSelector.class)
    public @interface EnableMyAutoConfiguration {
    }
    ```
- `@MySpringBootApplication`에 달았다.
  ```java
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @ComponentScan
    @Configuration
    @EnableMyAutoConfiguration // <--  
    public @interface MySpringBootApplication {
    }    
  ```
- `@EnableMyAutoConfiguration`에는 `@Import(MyAutoConfigImportSelector.class)`가 달려있다.

- `MyAutoConfigImportSelector`를 살펴본다.
    ```java
    public class MyAutoConfigImportSelector implements DeferredImportSelector {

        private final ClassLoader classLoader;

        public MyAutoConfigImportSelector(ClassLoader classLoader) {
            this.classLoader = classLoader;
        }

        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            List<String> autoConfigs = new ArrayList<>();
    
            ImportCandidates.load(MyAutoConfiguration.class, classLoader)
                    .forEach(autoConfigs::add);
    
            return autoConfigs.toArray(String[]::new);
        }

    }
    ```
  MyAutoConfigImportSelector를 Import하면 `resource/META-INF/spirng/com.sandro.config.MyAutoConfiguration.imports` 파일에 지정된 클래스를 빈으로 등록한다.
- `resource/META-INF/spirng/com.sandro.config.MyAutoConfiguration.imports`
    ```text
    com.sandro.config.autoconfig.DispatcherServletConfig
    com.sandro.config.autoconfig.TomcatWebServerConfig
    ```
- 정리 
  
    최종적으로는 `@MySpringBootApplication`에 `@EnableMyAutoConfiguration`를 달면 `DispatcherServletConfig`와 `TomcatWebServerConfig`가 빈으로 등록된다. 이런 식으로 애너테이션을 달면서 필요한 빈을 쉽게 등록할 수 있다. 

















