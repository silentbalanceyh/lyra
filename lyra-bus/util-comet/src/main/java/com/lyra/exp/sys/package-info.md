## META -- System Error Code ( Lyra Framework Inner )

### __*Classes*__
Package: `com.lyra.exp.sys`

- __[PC]__ `com.lyra.exp.sys.MemberInitException` ( -20001 )
- __[PC]__ `com.lyra.exp.sys.ResourceIOException` ( -20002 )

### __*Basic Description:*__

The package `com.lyra.exp.sys` contains all the runtime exception classes which describe the error message occur when *Lyra Framework* is running.

### __*Error Code Table:*__

<table style="border-collapse:collapse;font-size:14px;">
	<tr style="font-style:italic">
		<td style="width:80px;">Error Code</td><td>Error Code Comments</td>
	</tr>
	<tr>
		<td><font style="color:red;">-20001</font></td>
		<td>
			When the instance variables have been initialized, the reference of these instance variables have been Null (ref==null) if some unexpected errors occured, in this situation this error happens and the error code return -20001.
		</td>
	</tr>
	<tr>
		<td><font style="color:red;">-20002</font></td>
		<td>
			When the resource files couldn't be read by Lyra Framework, this error happens and the error code return -20002, this kind of errors happen in I/O processing only. 
		</td>
	</tr>
</table>