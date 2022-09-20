package com.example.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
@Service
public class Streamgetter {
	/**インプットストリームから文字列を取得する
	 * @return streamの文字列を返す
	 * @param InputStreamを入れる*/
	 public static String getString(InputStream in) throws IOException
	    {
	        Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
	        BufferedReader br = new BufferedReader(reader);
	 
	        StringBuilder sb = new StringBuilder();
	        String line;
	 
	        while ((line = br.readLine()) != null)
	        {
	            sb.append(line);
	            sb.append('\n');
	        }
	        br.close();
	 
	        return sb.toString();
	    }

}
