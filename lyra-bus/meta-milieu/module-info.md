## META -- Milieu Module

### __*Basic Description:*__

__Group Id:__ `com.lyra` <br/>
__Artifact Id:__ `meta-milieu` <br/>
__Version:__ `0.1` <br/>
__Description:__ <br/>
This module provides all configuration, resources, schemata information of whole environment such as resource files, configuration files, property files and database connection pool. In current version, this module provides following features:

- `com.lyra.milieu.conn`:*BoneCP connection pool configuration and database interface ( meta-data & data ) which could be called by other modules, It provides common database accessing interfaces.*
- `com.lyra.milieu.exp`:*Abstract exception definition information in Lyra Framework.* 
- `com.lyra.milieu.exp.sys`:*Exception information of 'system' category which have been defined by default, this package is sub-package of `com.lyra.milieu.exp` package.*
- `com.lyra.milieu.model`:*System schemata which is critical data structure to process all schema definitions in JSON files.*
- `com.lyra.milieu.res`:*Resource constant components to store constant value list of Lyra Framework.*
- `com.lyra.milieu.spring`:*Spring container class to enable Spring IoC Container in Lyra Framework.*
- `com.lyra.milieu.sys`:*Model Configurator, a component which could access SYS\_MODEL table in database. This system table contains initialized definition data of Lyra Framework.<font style="color:red">There is no definition files ( Schema Json, Data Csv, Mapping Properties ) which are related to SYS\_MODEL and this table must be created before any modules deploy to Lyra Framework.</font>*

### __*Dependency Module:*__

- ( System Tools ) Util Comet : util-comet.jar