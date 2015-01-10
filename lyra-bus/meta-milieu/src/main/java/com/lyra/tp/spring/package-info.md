## META -- Custom Spring Container

### __*Classes*__
Package: `com.lyra.tp.spring`

- __[PC]__ `com.lyra.tp.spring.BeanContainer`

### __*Basic Description:*__

#### __*1.BeanContainer:*__
The class __[PC]__ `com.lyra.tp.spring.BeanContainer` is a container class which is manually defined. In current framework, it takes following responsibilities: 

* Be initialized when a Vert.X Verticle deploying
* Help to create spring bean class in current framework

There are two parts about how spring container used in current framework:

1. When it's running in Vert.X Verticle, you must start Spring container by manual, because there is no context for spring bean class, the class `com.lyra.tp.spring.BeanContainer` could help you to start spring container here. 
2. Once spring container has been started in Vert.X, all the bean classes which have been managed by spring container could be used in *Spring Mode* directly, you could consider spring as IoC container here at that time.

### __*Attention:*__

<font style="color:red">
*When spring is starting, the class `com.lyra.tp.spring.BeanContainer` shouldn't be put in scanned classes set, if you put it in scanned classes set, the spring container will be in endless loop and cause MemoryOut Error.*
</font>

Please refer configuration file in project **meta-milieu**, the path is `/src/main/resources/schema/spring/spring-system.xml`. 

### __*Code Segment:*__

This class is in exclude list of spring scanning, please be careful about the expression `com.lyra.res.*`, all the classes in this package has been removed from spring container scanning.

	<context:component-scan base-package="com.lyra" use-default-filters="false">
		<!-- Include List -->
		<context:include-filter type="regex" expression="com.lyra.mod.def.*"/>
		<context:include-filter type="regex" expression="com.lyra.db.conn.*"/>
		<!-- Exclude List -->
		<context:exclude-filter type="regex" expression="com.lyra.res.*"/>
		<context:exclude-filter type="regex" expression="com.lyra.tp.spring.*"/>
	</context:component-scan>
