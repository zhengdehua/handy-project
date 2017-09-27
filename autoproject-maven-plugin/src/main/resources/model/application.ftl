server:
  context-path: /${appContext}
  port: <#noparse>${SERVER_PORT:8080}</#noparse>

env:
  datasource-url: ${url!"# you can write database connection url here"}
  datasource-username: ${userId!"# you can write database username here"}
  datasource-password: ${password!"# you can write database password here"}

spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driverClassName: ${driverClass!"# you can write database driver class type here"}
    url: <#noparse>${env.datasource-url}</#noparse>
    username: <#noparse>${env.datasource-username}</#noparse>
    password: <#noparse>${env.datasource-password}</#noparse>

mybatis:
  typeAliasesPackage: ${typeAliasesPackage!"# you can write mybatis domain package here"}
  mapperLocations: classpath:sqlmap/**/*.xml
  configLocation: classpath:mybatis.xml
