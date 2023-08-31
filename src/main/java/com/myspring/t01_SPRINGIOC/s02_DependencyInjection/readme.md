# Dependency Injection

- when multiple class working together to achieve something, then definitely we need to take care of their dependency with each other.
- In spring , DI is implemented via ,
  - constructor injection
  - setter injection

### constructor injection
- constructor injection done by invoking the class's constructor with its arguments.
- example : 
```xml
<beans>
  <bean id="beanOne" class="x.y.ThingOne">
  <constructor-arg ref="beanTwo"/>
  <constructor-arg ref="beanThree"/>
  </bean>
  <bean id="beanTwo" class="x.y.ThingTwo"/>
  <bean id="beanThree" class="x.y.ThingThree"/>
</beans>
``` 
- we can pass constructor arguments in different ways 
  - by Name
  - by Type
  - using Index

#### byName
  ```xml
  <bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg name="years" value="7500000"/>
    <constructor-arg name="ultimateAnswer" value="42"/>
  </bean>
```
#### byType
```xml
  <bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg type="int" value="7500000"/>
    <constructor-arg type="java.lang.String" value="42"/>
  </bean>
```
#### using Index
```xml
<bean id="exampleBean" class="examples.ExampleBean">
  <constructor-arg index="0" value="7500000"/>
  <constructor-arg index="1" value="42"/>
</bean>
```

### setter injection
- setter injection based on the setter method of the dependent property

#### eg
```xml
<bean id="exampleBean" class="examples.ExampleBean">
  <property name="empId" value="100001"/>
  <property name="empName" value="Kuekuatsu"/>
</bean>
```

### when to use which ?
- when we want to define mandatory dependencies we will use constructor injection
- when we need to define optional dependencies then will go with setter injection
- (we can use @Required above setter method to make the setter injection mandatory)

### Collections in metadata
- list (List)
- set (Set)
- map (Map)
- props (Properties)

```xml
<bean id="moreComplexObject" class="example.ComplexObject">
  <!-- results in a setAdminEmails(java.util.Properties) call -->
  <property name="adminEmails">
  <props>
  <prop key="administrator">administrator@example.org</prop>
  <prop key="support">support@example.org</prop>
  <prop key="development">development@example.org</prop>
  </props>
  </property>
  <!-- results in a setSomeList(java.util.List) call -->
  <property name="someList">
  <list>
  <value>a list element followed by a reference</value>
  <ref bean="myDataSource" />
  </list>
  </property>
  <!-- results in a setSomeMap(java.util.Map) call -->
  <property name="someMap">
  <map>
  <entry key="an entry" value="just some string"/>
  <entry key="a ref" value-ref="myDataSource"/>
  </map>
  </property>
  <!-- results in a setSomeSet(java.util.Set) call -->
  <property name="someSet">
  <set>
  <value>just some string</value>
  <ref bean="myDataSource" />
  </set>
  </property>
</bean>
```

### merging 
- in collection property, we can override the parent class properties like below

```xml
<beans>
  <bean id="parent" abstract="true" class="example.ComplexObject">
  <property name="adminEmails">
  <props>
  <prop key="administrator">administrator@example.com</prop>
  <prop key="support">support@example.com</prop>
  </props>
  </property>
  </bean>
  <bean id="child" parent="parent">
  <property name="adminEmails">
  <!-- the merge is specified on the child collection definition -->
  <props merge="true">
  <prop key="sales">sales@example.com</prop>
  <prop key="support">support@example.co.uk</prop>
  </props>
  </property>
  </bean>
<beans>
```

### DependsOn
- The depends-on attribute can explicitly force one or more beans to be initialized before the bean using this element is initialized. 

```xml
<bean id="beanOne" class="ExampleBean" depends-on="manager"/>
<bean id="manager" class="ManagerBean" />
```

### lazy-initialized-bean
- by default beans are gets instantiated instantly whenever container starts. however using this "lazy-init" property on beans we can stop the instant instantiation. so that beans only instantiated when its called.

```xml
<bean id="exampleBean" lazy-init="true" class="examples.ExampleBean">
  <constructor-arg index="0" value="7500000"/>
  <constructor-arg index="1" value="42"/>
</bean>
```

### Autowiring 
- spring can automatically detect and autowire the dependencies from applicationContext for us.
- you can specify the
  autowire mode for a bean definition with the autowire attribute of the <bean/> element.
- the autowiring has four modes
  - no
    - no autowiring (.i.e. should not be autowired)
  - byname
    - match the autowiring property name with the available beans from the container (proper setter method should be defined)
  - byType
    - spring will try to match the exact type of autowiring property with the container.
    - if multiple beans found for the same type in the container then error will be thrown.
  - constructor 
    - check the single bean of the type which was passed in constructor. if multiple beans found for the same type in the container then error will be thrown.
  
Note : if we want we can disable autowiring for a particular bean by passing "autowire-candidate=false"

### Method Injection :
- in somecases , singleton beans contains prototype bean attribute.

```java
SomeSingletonClass {
    someprototype attribute;
}
```
- in this case no matter what , the prototype will have only one instance eventhough it has scope as "prototype" because of the container class is singleton
- we can use this one of the below way to inject the prototype bean manually

```java
public class CommandManager implements ApplicationContextAware {
  private ApplicationContext applicationContext;
  public Object process(Map commandState) {
  // grab a new instance of the appropriate Command
  Command command = createCommand();
  // set the state on the (hopefully brand new) Command instance
  command.setState(commandState);
  return command.execute();
  }
  protected Command createCommand() {
  // notice the Spring API dependency!
  return this.applicationContext.getBean("command", Command.class);
  }
  public void setApplicationContext(
  ApplicationContext applicationContext) throws BeansException {
  this.applicationContext = applicationContext;
  }
}
```

<span style="color:orange">The preceding is not desirable, because the business code is aware of and coupled to the Spring
Framework.</span>

### LookUp Method Injection 
- its kind of superpower to the container to override the method of beans for prototype dependency injection
- For this dynamic subclassing to work ,
  - subclasses and overridden methods cannot be final
  - Unit-testing a class that has an abstract method requires you to subclass the
     class yourself and to supply a stub implementation of the abstract method.
  - Concrete methods are also necessary for component scanning, which requires
     concrete classes to pick up.
  - A further key limitation is that lookup methods do not work with factory
     methods and in particular not with @Bean methods in configuration classes,
     since, in that case, the container is not in charge of creating the instance and
     therefore cannot create a runtime-generated subclass on the fly.

In the case of the CommandManager class in the previous code snippet, the Spring container
dynamically overrides the implementation of the createCommand() method. The CommandManager class
does not have any Spring dependencies, as the reworked example shows:

```java
public abstract class CommandManager {
  public Object process(Object commandState) {
  // grab a new instance of the appropriate Command interface
  Command command = createCommand();
  // set the state on the (hopefully brand new) Command instance
  command.setState(commandState);
  return command.execute();
  }
  // okay... but where is the implementation of this method?
  protected abstract Command createCommand();
}
```

if the abstract method doesn't have implementation class then it automatically resolves the required dependency from container based on bean metadata config.  

```xml
<!-- a stateful bean deployed as a prototype (non-singleton) -->
<bean id="myCommand" class="fiona.apple.AsyncCommand" scope="prototype">
  <!-- inject dependencies here as required -->
</bean>
<!-- commandProcessor uses statefulCommandHelper -->
<bean id="commandManager" class="fiona.apple.CommandManager">
  <lookup-method name="createCommand" bean="myCommand"/>
</bean>
```

otherwise it uses implemented child class's concrete method.

also we can use the below annotation to achieve lookup method injection

```java
@Lookup("myCommand")
protected abstract Command createCommand();
//or let spring to find and resolve the method

@Lookup
protected abstract Command createCommand();
```

### Arbitrary Method Replacement
- A less useful form of method injection than lookup method injection is the ability to replace
arbitrary methods in a managed bean with another method implementation.
- With XML-based configuration metadata, you can use the replaced-method element to replace an
  existing method implementation with another, for a deployed bean. Consider the following class,
  which has a method called computeValue that we want to override:

```java
public class MyValueCalculator {
  public String computeValue(String input) {
  // some real code...
  }
  // some other methods...
}
```

- A class that implements the org.springframework.beans.factory.support.MethodReplacer interface
  provides the new method definition, as the following example shows

```java
/**
 * meant to be used to override the existing computeValue(String)
 * implementation in MyValueCalculator
 */
public class ReplacementComputeValue implements MethodReplacer {
  public Object reimplement(Object o, Method m, Object[] args) throws Throwable {
  // get the input value, work with it, and return a computed result
  String input = (String) args[0];
  ...
  return ...;
  }
}

```

The bean definition to deploy the original class and specify the method override would resemble
the following example:

```xml
<bean id="myValueCalculator" class="x.y.z.MyValueCalculator">
  <!-- arbitrary method replacement -->
  <replaced-method name="computeValue" replacer="replacementComputeValue">
  <arg-type>String</arg-type>
  </replaced-method>
</bean>
<bean id="replacementComputeValue" class="a.b.c.ReplacementComputeValue"/>
```
