package com.example.application;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.MUser;
import com.example.UserService;
import com.example.application.mail.MailSendController;
import com.example.application.urlFile.UrlFileManager;
import com.example.dataTable.DataTable;
import com.example.dataTable.DataTableService;



@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private final String SUBJECT= "サイトに更新がありました";
	private final String ENDMASSAGE = "以上のサイトに変更がありました";

	
	@Autowired
	public MailSendController mailSendController;
	@Autowired
	public UserService userService;
	@Autowired
	public DataTableService dataTableService;
	@Autowired
	private UrlFileManager manager;
	
	
/**毎分通知時間と一致するユーザーのデータテーブルに含まれるhtmlとファイルを比較して異なるものをメールで送る*/
	@Scheduled(fixedRate = 60000)
	public void mailSend() {
		log.info("メールサービスの開始");
		List<MUser> users =userService.getMachUsersWithNow();
		//マッチするユーザーがいない場合処理終わり
		if (users.isEmpty()) {
			log.info("マッチするユーザーがいなかった為サービスの完了");
			return;
		}
		//ユーザー毎に対して行う処理
		for (MUser mUser : users) {
			StringBuilder urlList = new StringBuilder();
			List<DataTable> dataTables = dataTableService.findByUserId(mUser.getUserId());
			if (dataTables.isEmpty()) {
				continue;
			}
			//データ毎に対して行う処理
			for (DataTable dataTable : dataTables) {
				boolean isUrlSame =true;
				try {
					isUrlSame= manager.isSameStream(dataTable);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (!isUrlSame) {
					urlList.append(dataTable.getSiteUrl());
					urlList.append("\r");
					urlList.append("\r");
				}
			}
			if (urlList.isEmpty()) {
				log.info(mUser.getUserId()+"さんのリストには変更が無かっただ");
				continue;
			}
			urlList.append(ENDMASSAGE);
			String finalText = urlList.toString();
			mailSendController.write1("histryrecode@gmail.com", mUser.getUserId(), SUBJECT, finalText);
			log.info(mUser.getUserId()+" にメールを送信しました");
		}
		log.info("メールサービス完了");
	}
}
