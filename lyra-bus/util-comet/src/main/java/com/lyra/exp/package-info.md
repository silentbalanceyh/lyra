## META - Abstract Exception

### __*Classes*__
Package: `com.lyra.exp`

- __[PA]__ `com.lyra.exp.AbstractSchemaException`
- __[PA]__ `com.lyra.exp.AbstractSystemException`

### __*Basic Description:*__

The package `com.lyra.exp` contains all abstract exception classes in Lyra Framework, other exception classes must be inherited from these exception classes. 

#### *1 - AbstractSchemaException*

This exception is used in schema validation and it's super class is `java.lang.Exception`, once these kind of exceptions happen, it must be catched by Lyra Framework and could send error response to client to described the issue of provided schema files.

#### *2 - AbstractSystemException*

This exception is used in system runtime and it's super class is `java.lang.RuntimeException`, it's not needed to be catched but it could provide debug information to help us and the logger will record the java stack messages.