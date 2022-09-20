/*ユーザーマスター*/
CREATE TABLE IF NOT EXISTS m_user(
  user_id VARCHAR(50)PRIMARY KEY
  ,password VARCHAR(255)
  ,user_name VARCHAR(50)
  ,notification_time VARCHAR(5)
);

/*データマスター*/
CREATE TABLE IF NOT EXISTS data_table(
  unique_id VARCHAR(36)PRIMARY KEY,
  user_id VARCHAR(50),
  title VARCHAR(50),
  site_url VARCHAR(100),  
  file_path VARCHAR (75)
 
);