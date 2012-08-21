Example of how to pass arguments to a MLM
=========================================

You may run this MLM with:

    $ arden2bytecode -r arguments.mlm

However, to pass arguments, use the **-a** flag of Arden2ByteCode.  
The string following the **-a** flag must be a valid constant 
Arden Syntax expression. Otherwise a parsing error will occur.  
To be a valid Arden Syntax expression, a string must be surrounded
by quotes which, in turn, must be properly escaped when being
passed on the command line.

The Windows shell and Bash use backslashes to escape quotes.

Windows shell example:

    > arden2bytecode -a "(\"arg 1\",\"arg 2\")" "(34.5,\"arg 4\")" -r arguments.mlm
	
