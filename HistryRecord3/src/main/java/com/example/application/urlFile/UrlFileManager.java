package com.example.application.urlFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.example.application.Streamgetter;
import com.example.dataTable.DataTable;

@Component
public class UrlFileManager {
	//ファイルの場所指定
	private final String path = new String("C://testfile/");
	
	
	/**登録時にファイル作成するメソッド
	 * @return 成功したらファイルパスを返す　失敗したら失敗した文字列を返す
	 * @throws IOException URLから文字列を取得できない場合とファイルが新規作成できない場合*/
	public String getUrlSave(String uuid ,String siteUrl) throws IOException {

		try {
			
			URL url = new URL(siteUrl);
			Path fullQualifiedName = Paths.get(path+uuid+".text");
			InputStream is = new BufferedInputStream(url.openStream());
			//ファイルの入れ物作成
			File file = new File(fullQualifiedName.toString());
			//ファイルが無かったらつくる
			if (file.exists()) {
				Files.createFile(fullQualifiedName);
			}
			//ファイルを保存
			PrintWriter pw = new PrintWriter(file);
			pw.print(Streamgetter.getString(is));
			pw.close();

			return fullQualifiedName.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "登録失敗しちゃった(*ﾉω・*)ﾃﾍ";
		}

	}
	/**ファイルと現在のurlから取得したstreamを比較するメソッド
	 * ついでにファイルの上書きもしちゃう*/
	public boolean isSameStream(DataTable dataTable)throws IOException {
		boolean same = false;
		String siteUrl= dataTable.getSiteUrl();
		URL url = new URL(siteUrl);
		InputStream is1 = new BufferedInputStream(url.openStream());
		String is1String= Streamgetter.getString(is1);
		File file = new File(dataTable.getFilePath());
		InputStream is2 = new BufferedInputStream(new FileInputStream(file));
		String is2String= Streamgetter.getString(is2);
		is2.close();
		System.out.println("URLの文字列");
		System.out.println(is1String);
		System.out.println("ファイルの文字列");
		System.out.println(is2String);
		if(is1String.equals(is2String)) {
			same=true;
		}else {
			updateFile(is1String, file);
		}
		return same;
	}
	/**ファイルを上書きするメソッド*/
	public void updateFile(String string ,File file) {
		try {
			PrintWriter pw = new PrintWriter(file);
					pw.print(string);
					pw.close();
					System.out.println("ファイル書き込み成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ファイル上書き失敗");
			System.out.println(e);
		}
	}
	
	

}
