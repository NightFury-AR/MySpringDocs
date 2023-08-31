# Bean Scopes

- Singleton (default)
- Prototype
- request
- session
- application

**singleton** : no matter what, only one instance created for the bean   
**prototype** : each time new prototype created for that instance   
**request** : new bean for each HTTP request   
**session** : new bean for each HTTP session

### How do we define bean scope ?

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class DBService
```

but when we want to have the parent class as singleton and its child as different scope,
then we need to use some additional param with @scope

```java

//parent class

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class DBService {
    //dependency
    @Autowired
    private JDBCConnection jdbc;
}

//dependency class
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
class JDBCConnection {
    
}
```

## @ComponentScan

## @PostConstruct

## @PreDestroy

## CDI