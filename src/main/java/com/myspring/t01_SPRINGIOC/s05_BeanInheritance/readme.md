### Bean Inheritance

A bean definition can contain a lot of configuration information, including constructor arguments,
property values, and container-specific information, such as the initialization method, a static
factory method name, and so on. A child bean definition inherits configuration data from a parent
definition. The child definition can override some values or add others as needed. Using parent and
child bean definitions can save a lot of typing. Effectively, this is a form of templating.
If you work with an ApplicationContext interface programmatically, child bean definitions are
represented by the ChildBeanDefinition class. Most users do not work with them on this level.
Instead, they configure bean definitions declaratively in a class such as the
ClassPathXmlApplicationContext. When you use XML-based configuration metadata, you can
indicate a child bean definition by using the parent attribute, specifying the parent bean as the
value of this attribute. The following example shows how to do so:

```xml
<bean id="inheritedTestBean" abstract="true"
  class="org.springframework.beans.TestBean">
  <property name="name" value="parent"/>
  <property name="age" value="1"/>
</bean>
<bean id="inheritsWithDifferentClass"
  class="org.springframework.beans.DerivedTestBean"
  parent="inheritedTestBean" init-method="initialize"> ①
  <property name="name" value="override"/>
  <!-- the age property value of 1 will be inherited from parent -->
</bean>
```