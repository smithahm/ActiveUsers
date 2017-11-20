package ReadInputLog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.DatatypeConverter;

public class ReadInput {

	// Store the userid and hash value of the path accessed
	private Map<String, HashSet<String>> data = new HashMap<String, HashSet<String>>();

	public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		ReadInput r = new ReadInput();
		r.readLogFile();
		Scanner sc = new Scanner(System.in);
		System.out.println("please enter the value for N");
		int distinctPaths = sc.nextInt();
		// PRINTING The RESULT still In PROGRESS
		Iterator it = r.data.values().iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public void readLogFile() throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		GZIPInputStream gzip = new GZIPInputStream(new FileInputStream("access.log.gz"));
		BufferedReader br = new BufferedReader(new InputStreamReader(gzip));

		while (br.readLine() != null) {
			String[] values = br.readLine().split(",");
			String hashVal = hashTime(values[0] + values[2]);
			if (data.containsKey(values[1])) {
				HashSet<String> val = data.get(values[1]);
				val.add(hashVal);
				data.put(values[1], val);
			} else {
				HashSet<String> val = new HashSet<String>();
				val.add(hashVal);
				data.put(values[1], val);
			}
		}

	}

	// This method calculates the hash value of the path and time the user
	// accessed the paths.
	private String hashTime(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(input.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return myHash;
	}

}
