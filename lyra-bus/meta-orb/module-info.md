## META -- Orb Module

### __*Basic Description:*__

__Group Id:__ `com.lyra` <br/>
__Artifact Id:__ `meta-orb` <br/>
__Version:__ `0.1` <br/>
__Description:__ <br/>
This module provides basic data structure to process every model, it's called "Process Model". It's different from schema module ( Milieu Module ), their relationships are as follows:

1. *Milieu Module plays a role as definition and meta-data in Lyra Framework;*
2. *Orb Module plays a role as processing and data in Lyra Framework;*

Also this module implements the "Strategy Pattern", you could switch meta-data method based on "Strategy", the default schema definition is JSON files. This design reserves a placeholder to extend Lyra Framework in future. 

Here list some common modeling methods:

- JSON files ( Dynamic ) : <font style="color:red">It's used in Lyra Framework</font>
- XML/XML Schema files ( Dynamic )
- Apache Avro Specification ( Dynamic )
- Java POJOs ( Static )
- Database Tables ( Static )

This module should work as following steps:

1. Read modeling definition meta-data from meta-data folder; ( __meta-orb__, `src/main/resources/schema/json/active/model/`)<br/>
   <font style="color:red">
   *Be careful about the meta-data folder value, it could be configured in SYS\_MODEL table for each model.*
   </font>
2. Create java object which contains meta-data in memory;

### __*Dependency Module:*__

- ( Environment ) Meta Milieu : meta-milieu.jar