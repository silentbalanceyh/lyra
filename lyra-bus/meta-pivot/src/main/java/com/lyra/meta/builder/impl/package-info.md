## META -- Pivot Module

### __*Basic Description:*__

The package `com.lyra.pivot.builder` contains the Builder for database meta-data especially for SQL database, it contains below classes:

- __[A]__ `com.lyra.pivot.builder.AbstractMetaBuilder`
- __[I]__ `com.lyra.pivot.builder.MetaBuilder`
- __[C]__ `com.lyra.pivot.builder.MsSqlBuilder` <font style="color:blue">(In Progress...)</font>
- __[C]__ `com.lyra.pivot.builder.MySqlBuilder` <font style="color:red">(Pending In Progress...)</font>
- __[C]__ `com.lyra.pivot.builder.OracleBuilder` <font style="color:red">(Pending In Progress...)</font>
- __[C]__ `com.lyra.pivot.builder.PgSqlBuilder` <font style="color:red">(Pending In Progress...)</font>
- __[C]__ `com.lyra.pivot.builder.MetaDirector`

This package provide a component to synchronize meta-data definition between schema files and SQL database, it's implemented with "Builder Design Pattern". There exist four implementations for Microsoft SQL Server, Postgre SQL, MySQL, Oracle database, these four classes must inherit abstract class __AbstractMetaBuilder__ and implement interface __MetaBuilder__. AbstractMetaBuilder is not public and its access control field is default package, it's only used to be inherited in current package, if other modules want to communicate with all builders, they can initialize these implementation classes' instances by MetaBuilder interface. 

#### *1 - MetaDirector*

The standard way to create builders here is used `MetaDirector`, this class acts "Director" in "Builder Design Pattern". In this package, all implementation classes could be configured in property file, it means that "Director" initialized the builder classes based on system property file as following. ( __meta-milieu__, `src/main/resources/schema/system/orb.properties` )

	orb.database=mssql
	orb.database.builder=com.lyra.pivot.builder.MsSqlBuilder

Property `orb.database.builder` is mapped to Resources.DATABASE_BUILDER which is Read-Only constant variable, the director class created the builder implementation class as follow code segment:

	public MetaDirector(final Context context){
		final String clazz = Resources.DATABASE_BUILDER;
		this.builder = this.reflector.createInstance(clazz,context);
	}

With Java Reflection, you could create custom builder implementation class and put the class name to property file as below:

	orb.database.builder=<Your custom class full name>

The best way to use "Builder" component is via "Director" class instead of MetaBuilder interface, it's standard way in "Builder Design Pattern". 

	Context ctx = new Context("role");
	MetaDirector director = new MetaDirector(ctx);
	boolean ret = director.syncDatabase();

<font style="color:red">
*MetaDirector is the only one "public implementation" class in current package, other builder implementation classes access control field are all "default package", you could not create any instances of all builders out of package `com.lyra.pivot.builder`.*
</font>

#### *2 - MetaBuilder*

Simple interface which should be implemented by `Builder` sub-class, in current framework design, each database ( Microsoft SQL Server, Postgre SQL, MySQL, Oracle ) owns one `Builder` independently, these builders must inherit abstract class `AbstractMetaBuilder` and implement `MetaBuilder` interface. 

This interface provides below methods which could build meta-data building inner database:

	/**
	 * Check if the table existed in database
	 * @return
	 */
	boolean existTable();
	/**
	 * Create table in database
	 * @return
	 */
	boolean createTable();
	/**
	 * Drop existed table
	 * @return
	 */
	boolean removeTable();
	/**
	 * Update existed table
	 * @return
	 */
	boolean updateTable();
	/**
	 * 
	 * @return
	 */
	boolean postValidate();
	/**
	 * 
	 * @return
	 */
	AbstractSchemaException getError();

<font style="color:red">
*All builders are for SQL databases' meta-data schema building only, some other databases such as MongoDB (No-SQL) do not require meta-data schema building because of Dynamic Schema Mechanism.*
</font>

#### *3 - AbstractMetaBuilder*

Abstract builder class which provides some common meta-data operations, these operations are suitable for all SQL databases. This class provides below three features:

1. Encapsulate common members such as `MetadataContext`, `Context`, `JdbcTemplate`, these members could be referenced by sub-classes with *public* methods `get*`;
2. Encapsulate some common methods, these methods often provide SQL DDL operations, also they execute the validation for schema files, all the meta-data schema files must be parsed correctly by *Lyra Framework*;
3. Provide abstract methods which should be implemented by sub-classes, they like placeholder to unite all common operations in different sub-classes;

#### *4 - MsSqlBuilder*

One builder implementation class for Microsoft SQL Server database. 