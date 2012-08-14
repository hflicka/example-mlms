# Arden2ByteCode SQL Example

This example is also documented in the Arden2ByteCode wiki at: 
<http://arden2bytecode.sourceforge.net/docs/using-jdbc-and-sql-in-arden-syntax-read-statements>

## Running the Example

To run this example, you need an SQL capable JDBC driver such as the 
[Xerial SQLiteJDBC driver](http://www.xerial.org/trac/Xerial/wiki/SQLiteJDBC).

If you have downloaded the SQLite JDBC driver, you can use the 
following command to start Arden2ByteCode:

    $ arden2bytecode -n -p <path-to-sqlite>\sqlite-jdbc-<version>.jar -d org.sqlite.JDBC -e jdbc:sqlite:person.sqlite -r sql-example.mlm

Note that first, you have to cd to the directory the MLM is in.

The following command-line switches are used:

* **-n**  
  Don't display logo
* **-p**  
  Extend the classpath by the JDBC driver.
* **-d**  
  Specify the Java class name of the JDBC driver
* **-e**  
  Specify the execution environment and the JDBC connection 
  URL at the same time. The connection URL in turn
  specifies the SQLite file name.
* **-r**  
  Tell Arden2ByteCode to run the MLM directly after compilation.

## Using other SQL Engines

If you want to use MySQL or any other SQL capable database, 
you have to adjust the command line switches given above by
the appropriate driver .jar file, class name and JDBC 
connection URL.

A .sql file to set up the table used in this example is 
supplied with this example.  
I had SQLite in mind when writing it so you might have to
adjust the data types and datetime expressions for other
SQL databases.