## META - Constants, Resources

### __*Classes*__
Package: `com.lyra.res`

- __[DC]__ `com.lyra.res.Classes`
- __[PC]__ `com.lyra.res.Constants` 		
- __[DC]__ `com.lyra.res.PropKeys`
- __[PC]__ `com.lyra.res.Resources`

### __*Basic Description*__

All constant values are defined in the package `com.lyra.res` and they will be used in Lyra Framework. They are categorized into four as following:

* Constant full-qualified names of java classes, they are used by `com.lyra.res.Resources` only and provide default Metadata Builder class, default Database Dao class;
* Constant system symbols such as *Empty String, Null Pointer, Comma, Bracket,*  etc;
* Constant property keys, they are used by `com.lyra.res.Resources` only. `com.lyra.res.PropKeys` class manages all property keys in uniform one place;
* Readonly resource values, these values will be initialized when the classes are loaded by Java Class Loader, they could be configured in ( __meta-milieu__, `src/main/resources/schema/system/global.properties`);

### __*Constant & ReadOnly:*__

* Constant<br/>
The values of constant have been fixed by Java Compiler and they could not be changed;
* ReadOnly<br/>
The values of read only variables have been initialized when the classes are loaded by Class Loader, they could be configured. When `com.lyra.res.Resources` is loaded by Class Loader, the values will be changed if configuration information are changed before class loading;

<font style="color:red">*Both above values could not be changed in run time.*</font>

### __*Code Segement:*__

<font style="color:green">__*CORRECT*__</font>

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration("/schema/spring/spring-core.xml")
	public class SchemaTestCase { ... }

<font style="color:red">__*WRONG*__  Here Resources.SPRING_CONFIG is readonly, not constant.</font>

	@RunWith(SpringJUnit4ClassRunner.class) <br/>
	@ContextConfiguration(Resources.SPRING_CONFIG) <br/>
	public class SchemaTestCase { ... }