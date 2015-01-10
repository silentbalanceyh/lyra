## META - Model Schema Definition

### __*Basic Description:*__

The package `com.lyra.mod.def` contains all useful model's schema definitions, we have defined four critical schemata. 

- __[C]__ `com.lyra.milieu.model.KeyNames`
- __[C]__ `com.lyra.milieu.model.MetaSchema`
- __[C]__ `com.lyra.milieu.model.FieldSchema`
- __[C]__ `com.lyra.milieu.model.EntitySchema`

These schemata contain all definitions for an entity in Lyra Framework, Lyra does not contain any java POJOs ( Java Bean class with setter/getter. ). It's different from traditional system architecture and Lyra builds all java model dynamically, the system will generate basic model based on JSON files. You could modify the model definition in JSON files and then the system will synchronize the changes between database, application, user interface and JSON files. 

Current package `com.lyra.milieu.model` is meta-data container to store all the definitions in memory when Lyra Framework is running, please refer following comments to check every class details.

### __*Classes:*__

#### *1 - KeyNames*

This class defined four categories of Keys in entity: __Primary Key__, __Foreign Key__, __Unique Key__, __Not Null Key__, these keys are common used in application and it provides each rule for entity's field. 

- __Primary Key__ <br/>
  Primary key comes from database concept and it's the same as what's used in database, this key contains two rules: *unique & not null*. It is the identifier of every record in application and the system could distinguish each record by primary key.
- __Foreign Key__ <br/>
  Foreign key is used to describe relationship between one entity and another, it also comes from database concept. This key could be null for every record, otherwise once it's not null, you must be sure the related record of another entity exist in current system, it's the basic rule of foreign key, also the same as used in database.
- __Unique Key__ <br/>
  Unique key is related to database constraint rule instead of database Key concept, it's different from above two keys. This key describes the field rule as Unique in database, it's the same as UNIQUE constraint in database, it means that although this field is not identifier of every record, but the system also could distinguish each record by this field.This field can be null value, but the system only allow null value appear once. If existing two null values in this field, the situation will brake UNIQUE constraint, it's different from *not null* rule of primary key.
- __Not Null Key__ <br/>
  Not Null key is also related to database constraint rule, this key describes the field rule as Not Null in database, it's the same as NOT NULL constraint in database, it means that you couldn't put any null value into this field. 

#### *2 - MetaSchema*

This class is very simple, it describes the basic info of an Entity, it contains only four attributes for every entity.

- __pkg__ <br/>
  The package information of defined entity, it's the same concept as __namespace__ in many computer languages. 
- __name__ <br/>
  The entity short name, the system couldn't identify entity by short name because the system allows two entities existing in the system with same short name but different packages. 
- __fullName__ <br/>
  The entity full name in Lyra framework, the system could identify each entity by full name, it's format is: <font style="color:blue">package + "." + name</font>
- __table__ <br/>
  The entity's mapped table name in database.

#### *3 - FieldSchema*

This class is critical for all dynamic models, it defined every field's ( attribute ) detail rules and these rules could be used in different scenarios. 

- *It defined the database constraint rules of every field;*
- *It defined the validation rules of every field;*
- *It defined the data specification rules of every field ( Language Rule );*

<font style="color:red">*Above three classes are all formatted with java POJO style, they are different from `EntitySchema`. They only contain simple setter/getter members and this obey the specification rules of Java Bean, you could consider above three classes as Simple Java Bean.*</font>

There is a special field named "policy", this field has defined how to generate __Primary Key__ value in the system, it contains below values in current version:

- RANDOM: No rule defined for primary key, only reserve the basic database constraints ( UNIQUE && NOT NULL )
- AUTO: __[NumberType]__ Auto increasing ID number for primary key
- GUID: __[StringType]__ GUID format ID string for primary key
- MULTI: No rule defined for primary key but there should be at least more than one primary key in database

#### *4 - EntitySchema*

This class is special in package `com.lyra.milieu.model`, it's not java POJO style and it act two roles in current package.

- __Published Interface__ <br/>
  If other components in Lyra Framework want to communicate with all schemata information, they must use `EntitySchema` instead of above three ( *KeyNames*, *MetaSchema*, *FieldSchema* ), the components couldn't use above three schemata directly. 
- __Combination__ <br/>
  *EntitySchema* contains all above three schemata with different data structures. <br/>
  *1) It converted all key names into a HashMap, each element of this Map is: KeyName=FieldSchema.* <br/>
  *2) It converted all field schemata into a HashMap, each element of this Map is: FieldName=FieldSchema.* <br/>
  *3) It contains only one member named `metadata` which type is `MetaSchema`.*

### __*Code Segment:*__ 

Here we listed all the members definition in `FieldSchema` which have not been described above.

	@Component
	public class FieldSchema {
		/** Field name of current record. **/
		@JsonProperty("name")
		private String name;
		
		/** Field data type of current record. **/
		@JsonProperty("type")
		private String type;
		
		/** Mapped database column name.**/
		@JsonProperty("columnName")
		private String columnName;
		
		/** Mapped database column data type. **/
		@JsonProperty("columnType")
		private String columnType;
		
		/** Determine if current field should be NULL or NOT NULL. **/
		@JsonProperty("nullable")
		private boolean nullable;
		
		/** Determine if current field should be unique.**/
		@JsonProperty("unique")
		private boolean unique;
		
		/** ID Policy: {RANDOM,AUTO,GUID} **/
		@JsonProperty("policy")
		private String policy;
		
		/** Only for AUTO policy. **/
		@JsonProperty("seq-init")
		private int seqInit;
		
		/** Only for AUTO policy. **/
		@JsonProperty("seq-step")
		private int seqStep;
		
		/** Only for Oracle/PostgreSQL **/
		@JsonProperty("seq-name")
		private String seqName;
		
		/** Max length of current field **/
		@JsonProperty("length")
		private int length;
		...
	}