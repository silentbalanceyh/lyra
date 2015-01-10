## META -- Strategy Api Interface

### __*Basic Description:*__

The package `com.lyra.orb.strategy` defined published interface of current module, it includes following classes in current version:

- __[C]__ `com.lyra.orb.strategy.Context`
- __[I]__ `com.lyra.orb.strategy.Record` 
- __[I]__ `com.lyra.orb.strategy.Strategy`
- __[I]__ `com.lyra.orb.strategy.Value` 

### __*Classes:*__

#### *1 - Context*

This class is standard context class of "Strategy Pattern", other modules communicate with current ( Orb Module ) based on this class. The initialization code is as follows:

	Context context = new Context("role");

"role" is the model short name and it has been passed into `Context`'s constructor, once this parameter has been passed into constructor, this class built the context environment for "role" model only, different models could not share context data. 

__*Attention:*__

<font style="color:red">
*There is a static HashMap in class `Context`, it's called "Context Pool". The key is model short name and value is "Strategy", it means that every model should have only one strategy to be processed.* 
</font>

#### *2 - Record*

This class is an interface, it defined published Api of every model record ( entity record ).

#### *3 - Value*

This class is an interface, Lyra Framework defined custom data type system, every custom data type class must implement interface `com.lyra.orb.strategy.Value`. In this case, Lyra Framework could identify the data type as native data type, otherwise the data type is invalid and could not be used in Lyra. 

#### *4 - Strategy*

This class is an interface, it's standard strategy interface of "Strategy Pattern", there are three Apis in current version. 