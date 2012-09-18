/**
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import arden.compiler.CompiledMlm;
import arden.runtime.ArdenNumber;
import arden.runtime.ArdenString;
import arden.runtime.ArdenValue;
import arden.runtime.ExecutionContext;
import arden.runtime.DatabaseQuery;
import arden.runtime.MedicalLogicModule;
import arden.runtime.MemoryQuery;

/**
 * @author Klaus-Hendrik Wolf
 * @version 1.0
 * 
 */
public class ExecMLM extends ExecutionContext {

	private static final String FILE_NAME = "test.class";
	private static final String MLM_NAME = "test.mlm";

	public void write(ArdenValue message, String destination) {
		System.out.println("Write {" + destination + "}: " + message.toString());
	}
	
	public DatabaseQuery createQuery(String mapping) {
		System.out.println("Read {" + mapping + "}:");
		return new MemoryQuery(readArdenValuesFromStdIn());
	}

	public ArdenValue [] readArdenValuesFromStdIn() {
		String curLine = "";
		Vector <String> inputStrings = new Vector <String>();
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);

		do {
			System.out.print(" - Enter value (leave blank to finish): ");
			try {
				curLine = in.readLine();
				inputStrings.add(curLine);
			} catch (IOException e) {
				curLine = "";
			}
		} while (curLine.length() > 0);
		
		ArdenValue [] ardenArray = new ArdenValue[inputStrings.size()];
		for (int i = 0; i < inputStrings.size(); i++) {
			double inputNumber = 0.0;
			boolean isNumber = true;
			try {
				inputNumber = Double.parseDouble(inputStrings.get(i));
			} catch (NumberFormatException e) {
				isNumber = false;
			}
			if ( isNumber ){
				ardenArray[i] = new ArdenNumber(inputNumber);
			} else {
				ardenArray[i] = new ArdenString(inputStrings.get(i));
			}
		}	
		return ardenArray;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String fileName = FILE_NAME;
		String mlmName = MLM_NAME;

		if (args.length == 2) {
			fileName = args[0];
			mlmName = args[1];
		} else {
			System.out.println("Usage: java -jar execmlm.jar [compiled MLM class file] [MLM name]");
			System.out.println("Trying " + fileName + " " + mlmName + "!\n");
		}

		File mlmfile = new File(fileName);
		if (!mlmfile.exists()) {
			System.out.println("ERROR: File " + mlmfile + " does not exist!");
			System.exit(3);
		}
		
		MedicalLogicModule mlm = null;
		try {
			mlm = new CompiledMlm(mlmfile, "test");
		} catch (IOException e) {
			System.out.println("ERROR: Could not load MLM " + mlmName + " from file " + mlmfile + "!");
			System.exit(1);
		}

		ExecMLM context = new ExecMLM();
		ArdenValue[] arguments = null;
		ArdenValue[] result = null;
		try {
			result = mlm.run(context, arguments);
		} catch (InvocationTargetException e) {
			System.out.println("ERROR: Could not execute MLM " + mlmName + "!");
			System.exit(2);
		}
		if (result != null && result.length > 0) {
			System.out.println("Return Value: " + result[0].toString());
		} else {
			System.out.println("There was no return value.");
		}
	}

}


