## META -- Schema Validation Error Code

### __*Basic Description:*__

The package `com.lyra.orb.strategy.exp.json` contains all the exception classes which describe the error message occur when schema definitions validating happen.

- __[C]__ `com.lyra.orb.strategy.exp.json.MissingKeyDefException` ( -10001 )
- __[C]__ `com.lyra.orb.strategy.exp.json.MissingMetaDefException` ( -10002 )
- __[C]__ `com.lyra.orb.strategy.exp.json.MissingFieldDefException` ( -10003 )
- __[C]__ `com.lyra.orb.strategy.exp.json.MissingPrimaryKeyDefException` ( -10004 )
- __[C]__ `com.lyra.orb.strategy.exp.json.MissingMetaAttrException` ( -10005 )
- __[C]__ `com.lyra.orb.strategy.exp.json.MissingFieldAttrException` ( -10006 )
- __[C]__ `com.lyra.orb.strategy.exp.json.MissingPolicyDefException` ( -10007 )
- __[C]__ `com.lyra.orb.strategy.exp.json.MissingPrimaryKeyPolicyException` ( -10008 )
- __[C]__ `com.lyra.orb.strategy.exp.json.ConflictPolicyMoreThanTwoException` ( -10009 )
- __[C]__ `com.lyra.orb.strategy.exp.json.ConflictPolicyNMMatchingException` ( -10010 )
- __[C]__ `com.lyra.orb.strategy.exp.json.ConflictPolicyMMatchingException` ( -10011 )
- __[C]__ `com.lyra.orb.strategy.exp.json.ConflictPolicyPkTypeMatchingException` ( -10012 )

### __*Error Code Table:*__

<table style="border-collapse:collapse;font-size:14px;">
	<tr style="font-style:italic">
		<td style="width:80px;">Error Code</td><td style="width:160px;">Error Message</td>
	</tr>
	<tr>
		<td rowspan=2><font style="color:red;">-10001</font></td>
		<td><font style="color:blue;">[E] You must defined "__key__" in the root of your schema file, "__key__" attribute missed.</font></td>
	</tr>
	<tr>
		<td><font style="color:green">Rule</font> -> Every json schema file must contains "__key__" in root json object definition, if this attribute missed, return <font style="color:red;">Error: -10001</font>.</td>
	</tr>
	<tr>
		<td rowspan=2><font style="color:red;">-10002</font></td>
		<td><font style="color:blue;">[E] You must defined "__meta__" in the root of your schema file, "__meta__" attribute missed.</font></td>
	</tr>
	<tr>
		<td><font style="color:green">Rule</font> -> Every json schema file must contains "__meta__" in root json object definition, if this attribute missed, return <font style="color:red;">Error: -10002</font>.</td>
	</tr>
	<tr>
		<td rowspan=2><font style="color:red;">-10003</font></td>
		<td><font style="color:blue;">[E] You must defined "__fields__" in the root of your schema file, "__fields__" attribute missed.</font></td>
	</tr>
	<tr>
		<td><font style="color:green">Rule</font> -> Every json schema file must contains "__fields__" in root json object definition, if this attribute missed, return <font style="color:red;">Error: -10003</font>.</td>
	</tr>
	<tr>
		<td rowspan=2><font style="color:red;">-10004</font></td>
		<td><font style="color:blue;">[E] You must defined "pKeys" under "__key__" attribute, every model must have at least one primary key.</font></td>
	</tr>
	<tr>
		<td><font style="color:green">Rule</font> -> Every model schema definition must contain "__key__" attribute, it defined four keys: Primary Key, Not Null Key, Unique Key, Foreign Key, every model must contain Primary Key definition under "__key__" attribute named "pKeys", if this attribute missed, return <font style="color:red;">Error: -10004</font>.</td>
	</tr>
	<tr>
		<td rowspan=2><font style="color:red;">-10005</font></td>
		<td><font style="color:blue;">[E] Attribute "${inputField}" of "__meta__" missed, "__meta__" must contain four attributes (table,name,package,fullname).</font></td>
	</tr>
	<tr>
		<td><font style="color:green">Rule</font> -> Every model schema definition must contain "__meta__" attribute, it must contain all four attributes ( table, name, package, fullname ), if one of these attributes missed, return <font style="color:red;">Error: -10005</font>.</td>
	</tr>
	<tr>
		<td rowspan=2><font style="color:red;">-10006</font></td>
		<td><font style="color:blue;">[E] There are ${inputFieldCount} fields missing required attributes, every field must contain required four attributes.(name,type,columnName,columnType)</font></td>
	</tr>
	<tr>
		<td><font style="color:green">Rule</font> -> Every model schema definition must contain "__fields__" attribute, its data type is JSON Array, , each JSON Object describes the field definition and it must contain required four attributes (name,type,columnName,columnType), if one of these attributes missed, the invalid field counter increases by 1, finally return <font style="color:red;">Error: -10006</font>.</td>
	</tr>
		<tr>
		<td rowspan=2><font style="color:red;">-10007</font></td>
		<td><font style="color:blue;">[E] There is no "policy" attribute existing in defined primary keys, please check field schemata to set policy for primary key(s).</font></td>
	</tr>
	<tr>
		<td><font style="color:green">Rule</font> -> Every model schema definition must contain Primary Key definition with "policy" attribute, so there must be at least one field defined with "policy" attribute, once this attribute could not be found in all fields definition, return <font style="color:red;">Error: -10007</font>.</td>
	</tr>
		<tr>
		<td rowspan=2><font style="color:red;">-10008</font></td>
		<td><font style="color:blue;">[E] Primary Key "${inputPrimaryKey}"'s "policy" attribute missed, please set "policy" for "${inputPrimaryKey}".</font></td>
	</tr>
	<tr>
		<td><font style="color:green">Rule</font> -> For all primary keys' schema, it must contain "policy" attribute, if "policy" attribute missed in Primary Key definition, return <font style="color:red;">Error: -10008</font>.</td>
	</tr>
		<tr>
		<td rowspan=2><font style="color:red;">-10009</font></td>
		<td><font style="color:blue;">[E] Defined policy is "${inputPolicies}", there must be only one primary key "policy" exsiting in schema definition.</font></td>
	</tr>
	<tr>
		<td><font style="color:green">Rule</font> -> Every model could support one Pirmary Key policy only, once there exist more than one primary key policies being found in all fields definition, return <font style="color:red;">Error: -10007</font>.</td>
	</tr>
		<tr>
		<td rowspan=2><font style="color:red;">-10010</font></td>
		<td><font style="color:blue;">"[E] Defined policy is "${inputPolicy}", there must be only one field schema which contain "policy" attribute, conflict with more thant one definition."</font></td>
	</tr>
	<tr>
		<td><font style="color:green">Rule</font> -> If the policy of primary key definition is one of three (AUTO,GUID,RANDOM), there must be only one field with correct "policy" value, and this field must be defined in "pKeys". Once it's not matching, return <font style="color:red;">Error: -10010</font>.</td>
	</tr>
		<tr>
		<td rowspan=2><font style="color:red;">-10011</font></td>
		<td><font style="color:blue;">"[E] Defined policy is "MULTI", there must be more than one fields schema which contain "policy" attribute with value "MULTI", conflict with only one definition."</font></td>
	</tr>
	<tr>
		<td><font style="color:green">Rule</font> -> If the policy of primary key definition is MULTI, there must be more than one fields with the same "policy" value "MULTI", and these fields must be defined in "pKeys". Once they're not matching, return <font style="color:red;">Error: -10011</font>.</td>
	</tr>
		<tr>
		<td rowspan=2><font style="color:red;">-10012</font></td>
		<td><font style="color:blue;">[E] Your primary key's policy is not matching with SQL data type, please use correct SQL data type to define primary key.</font></td>
	</tr>
	<tr>
		<td><font style="color:green">Rule</font> -> GUID primary key must be defined in string type in SQL database, AUTO primary key must be defined in number type in SQL database, once above two rules have been broken, return <font style="color:red;">Error: -10012</font>.</td>
	</tr>
</table>