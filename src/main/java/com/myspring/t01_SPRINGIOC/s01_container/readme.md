## Spring IOC Container 

- also known as IoC / DI
- it is a process where objects define their dependencies
- <span style="color:skyblue;">org.springframework.beans</span> and 
 <span style="color:skyblue;">org.springframework.context </span> are the packages mainly used in this IOC container.
- [BeanFactory](https://docs.spring.io/spring-framework/docs/5.3.22/javadoc-api/org/springframework/beans/factory/BeanFactory.html) 
and [ApplicationContext](https://docs.spring.io/spring-framework/docs/5.3.22/javadoc-api/org/springframework/context/ApplicationContext.html) are the object that are used to create IOC
- The BeanFactory interface provides an advanced configuration
  mechanism capable of managing any type of object. 
- ApplicationContext is a sub-interface of
  BeanFactory. 
  - It adds:  
    • Easier integration with Spring’s AOP features  
    • Message resource handling (for use in internationalization)  
    • Event publication  
    • Application-layer specific contexts such as the WebApplicationContext for use in web
    applications.
- BeanFactory provides configuration framework and basic functionality
- ApplicationContext provides enterprise specific functionality
- The Bean is managed , assembled , instantiated by the container
- beans and its dependencies are provided in configuration metadata which used by container to manage the bean

### container overview 
- ApplicationContext is responsible to manage the beans in IOC
- the container gets its instruction from configuration metadata
- the configuration can be java config , annotation config or java code.

### configuration metadata
- configuration metadata is used by IoC to manage the beans
- it can be as xml configuration , annotation / java configuration.
  - sample xml config :
      ```xml
    <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">
        <bean id="..." class="..."> ① ②
          <!-- collaborators and configuration for this bean go here -->
        </bean>
        <bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
        </bean>
        <!-- more bean definitions go here -->
     </beans>
    ```
1.The id attribute is a string that identifies the individual bean definition  
2.The class attribute defines the type of the bean and uses the fully qualified classname   

### instantiating container

```java
ApplicationContext context = new ClassPathXmlApplicationContext("services.xml",
"daos.xml");
```   

### using the container

- application context is holding our all of the beans and we can retrieve using T getBean(String name, Class<T> classType).

```java
// create and configure beans
ApplicationContext context = new ClassPathXmlApplicationContext("services.xml",
"daos.xml");
// retrieve configured instance
PetStoreService service = context.getBean("petStore", PetStoreService.class);
// use configured instance
List<String> userList = service.getUsernameList();
```

### Bean overview 

with in the container itself bean object are represented as BeanDefinitions which contains the below data

- package qualified class name
- bean behavioural config elements (scope, lifecycle methods,)
- dependency details
- other config settings

<b>meta data properties </b> : class , name , scope , constructor-args , properties , auto wiring mode, lazy initialization , initialization method , destruction method

#### registering beans from outside container

<div style="background-color:seagreen;padding:12px;border-radius:5px;color:white;">
<code>
  //1.access the application context's beanfactory <br/>
  DefaultListableBeanFactory dlbf = applicationContext.getBeanFactory();
  <br>

  //2.create bean definition <br>
  GenericBeanDefinition gbd = new GenericBeanDefinition();
  gbd.setBeanClass(SomeBean.class); // set bean class
  <br>

  MutablePropertyValues mpv = new MutablePropertyValues();
  mpv.add("date", new Date());

  //alternatively we can use: <br>
  // gbd.getPropertyValues().addPropertyValue("date", new Date()); <br>
  gbd.setPropertyValues(mpv); <br>
  
  //3.register the bean with context
  dlbf.registerBeanDefinition("someBean",gbd);
  
  //4.getting bean from context (testing)
  SomeBean someBean = dlbf.getBean(SomeBean.class);
  someBean.doSomething();

</code>
</div>

this also can be done via <b>BeanFactoryPostProcessor , BeanDefinitionRegistryPostProcessor</b>

Note : for singleton beans we have to use <b>registerSingletonBean</b> method and at the same time if we want to remove the bean from context , then we can use <b>removeBeanDefinition(),destroySingleton()</b>.

### instantiating bean
  we can instantiate beans in multiple ways.
  - constructor (default)
  - factory method (initialized by static method which resides in bean class e.g createInstance())
  - factory bean (initialized from factory class factory method)
<hr/>

### API Section

### 1.[BeanFactory](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/BeanFactory.html) <interface>
- primary interface
- doesn't have any extends / implements
- beanfactory interface just have the abstract methods like checking something about bean name , type , etc.
- it has only one direct implementation class - XmlBeanFactory
- #### <s>[XMLBeanFactory](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/xml/XmlBeanFactory.html)</s> - depreciated
  - it is extending DefaultListableBeanFactory
  - also it contains one property called XMLBeanDefinitionReader and this property triggers loadBeanDefinitions method to load the beans from XMl file

### 2.ApplicationContext
- it is a sub interface of BeanFactory
- it is extending below interfaces (6)
  - [EnvironmentCapable](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/env/EnvironmentCapable.html)
    - it is used to check the Environment Object which is associated with the context
  - [ListableBeanFactory](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/ListableBeanFactory.html)
    - used to check the beans from context using some methods
  - [HierarchicalBeanFactory](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/HierarchicalBeanFactory.html)
    - Sub-interface implemented by bean factories that can be part of a hierarchy
  - [MessageSource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/MessageSource.html)
    - Strategy interface for resolving messages, with support for the parameterization and internationalization of such messages.
  - [ApplicationEventPublisher](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationEventPublisher.html)
    - Interface that encapsulates event publication functionality
  - [ResourcePatternResolver](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/io/support/ResourcePatternResolver.html)
    - Strategy interface for resolving a location pattern (for example, an Ant-style path pattern)


- ApplicationContext has 12 implementation classes and below are some of them.
  - AnnotationConfigApplicationContext
  - AnnotationConfigWebApplicationContext
  - ClassPathXmlApplicationContext
  - FileSystemXmlApplicationContext
  - GenericApplicationContext
  - GenericWebApplicationContext
  - GenericWebApplicationContext
  - XmlApplicationContext

- Also application context have some sub interfaces
  - [ConfigurableApplicationContext](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ConfigurableApplicationContext.html)
  - ConfigurableWebApplicationContext
  - WebApplicationContext
    - this is used inside web application. it inherits all from application context and additionally it provides servletContext method
    

