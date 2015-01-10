## META -- Default "Json Modeling" Strategy Implementation

### __*Basic Description:*__

The package `com.lyra.orb.strategy.json` contains all implementations of default strategy, it includes following classes:

- __[C]__ `com.lyra.orb.strategy.json.GenericSchema`
- __[C]__ `com.lyra.orb.strategy.json.GenericRecord`
- __[C]__ `com.lyra.orb.strategy.json.GenericStrategy`
- __[C]__ `com.lyra.orb.strategy.json.GenericValidator`


### __*Classes:*__

#### *1 - GenericSchema*

This class is a caller of meta-data module ( __meta-milieu__ ), it calls system tool `ResourceLoader` to read all "JSON" modeling definition meta-data from meta-data folder. ( __meta-orb__, `src/main/resources/schema/json/active/model/`). Also this class will convert meta-data file to `com.lyra.milieu.model.EntitySchema` object so that these meta-data could be used by Java language. 

This class includes some public methods, these public methods could extract meta-data from `com.lyra.milieu.model.EntitySchema` object such as *FieldSchema*, *FullName*, *Default Value* etc.

#### *2 - GenericRecord*

This class implements the interface `com.lyra.orb.strategy.Record`, it represents entity record and it's default implementation of "Record" interface.

#### *3 - GenericStrategy*

This class implements the interface `com.lyra.orb.strategy.Strategy`, this class could load properties to switch strategy. It means that you could set strategy configuration information in property file( __meta-milieu__, `/src/main/resources/schema/system/orb.properties`). The strategy property key format is: <font style="color:blue">orb.context.${orb.schema}</font>.

	# Schema definition method
	orb.schema=json
	orb.context.json=com.lyra.orb.strategy.json.GenericStrategy

#### *4 - GenericValidator*

This class is a validator for schema definitions. Once `GenericSchema` have finished reading schema definition information from JSON file, the validator should verify whether the definitions is fine. 

	// Validation schema
	this.validator.validateSchema();

This class contains a member named "error" and its data type is `AbstractSchemaException`, the system could get validation error via method `getError()`. Please refer __Schema Validation Error Code__ in the file <font style="color:blue">package-info.md</font> of package `com.lyra.orb.strategy.exp.json` to check error code details.

### __*Attention:*__

<font style="color:red">
Now the class `com.lyra.orb.strategy.Context` could switch strategy dynamically, this class has two constructors and one of them could accept two parameters as below, the default strategy could be configured in system property file.
</font>

	/** Construct context object by model name. **/
	public Context(final String modelName) { ... }
	/** Construct context object by model name and strategy both **/
	public Context(final String modelName,final String strategy){

### __*Code Segment:*__

	/** Construct context object by model name. **/
	public Context(final String modelName) {
		this.modelName = modelName;
		if (CTX_POOL.containsKey(this.modelName)) {
			this.strategy = CTX_POOL.get(this.modelName);
		} else {
			// TODO: Add more schema method.
			switch (Resources.SCHEMA_MODE) {
			case "json":
				this.strategy = new GenericStrategy(modelName);
				break;
			case "avro":
				break;
			default:
				break;
			}
			CTX_POOL.put(this.modelName, this.strategy);
		}
	}