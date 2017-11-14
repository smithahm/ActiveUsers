package ReadInputLog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class ReadInput {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		GZIPInputStream gzip = new GZIPInputStream(new FileInputStream("access.log.gz"));
		BufferedReader br = new BufferedReader(new InputStreamReader(gzip));

		while (br.readLine() != null) {
			System.out.println(br.readLine());
		}
	}

}
