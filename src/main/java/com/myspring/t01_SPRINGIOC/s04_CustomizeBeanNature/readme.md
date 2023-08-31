# Bean Customization 
- Bean lifecycle
- Application context aware and Bean name aware
- other Aware interfaces

### Bean LifeCycle:

In spring , bean lifecycle is divided into 2 parts. 
- initialization
- destroy

we have three ways to configure this.
1. with JSR annotation 
   1. @PostConstruct
   2. @PreDestroy
2. implementing bean with InitializingBean,DisposableBean
3. create custom init,destroy methods (using <bean>'s "init-method","destroy-method" attributes)

### Startup and Shutdown Callbacks
- LifeCycle (start,stop,isRunning)
  - A common interface defining methods for start/stop lifecycle control. The typical use case for this is to control asynchronous processing. NOTE: This interface does not imply specific auto-startup semantics. Consider implementing SmartLifecycle for that purpose.
    Can be implemented by both components (typically a Spring bean defined in a Spring context) and containers (typically a Spring ApplicationContext itself). Containers will propagate start/stop signals to all components that apply within each container, e.g. for a stop/restart scenario at runtime.
- LifeCycleProcessor (onRefresh,onClose and all lifecycle methods)
   - Strategy interface for processing Lifecycle beans within the ApplicationContext.

### Aware Interfaces
- <b>ApplicationContextAware</b>
- <b>ApplicationEventPublisherAware</b>
- <b>BeanClassLoaderAware</b>
- <b>BeanFactoryAware</b>
- <b>BeanNameAware</b>
- <b>LoadTimeWeaverAware</b>
- <b>MessageSourceAware</b>
- <b>NotificationPublisherAware</b>
- <b>ResourceLoaderAware</b>
- <b>ServletConfigAware</b>
- <b>ServletContextAware</b>

