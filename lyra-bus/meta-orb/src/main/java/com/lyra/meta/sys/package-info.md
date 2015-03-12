## META -- System Data Deployer

### __*Basic Description:*__

The class `com.lyra.orb.strategy.sys.SystemDataDeployer` is a special meta-data class to initialize database table SYS_MODEL, it's working as following steps:

1. Read sql script content from `<meta-milieu>/src/main/resources/system/schema/data/*` of different database;
2. Execute sql script to create SYS_MODEL table in SQL database;
3. Insert initialization data into SYS_MODEL table;
