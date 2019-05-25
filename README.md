# board


1. MySQL 5.7을 버전에 맞추어 설치한다.

(스크롤을 아래쪽으로 내려 **Generally Available (GA) Releases** 탭의 오른쪽에 **Looking for previous GA versions?** 을 클릭하면 받을 수 있다.
  
[이동](https://dev.mysql.com/downloads/mysql)


2. cmd.exe (관리자 권한) or 터미널을 실행하여 아래 명령어를 입력한다.

```
$ mysql -u root -p mysql
```


3. 아래 SQL 내용을 복사하여 붙여넣는다. 

```
-- board_ex database 생성
CREATE DATABASE  IF NOT EXISTS board_ex;
GRANT ALL PRIVILEGES ON board_ex.* TO board IDENTIFIED BY 'board' WITH GRANT OPTION;
USE board_ex;

-- table 삭제
DROP TABLE IF EXISTS tbl_attach;
DROP TABLE IF EXISTS tbl_reply;
DROP TABLE IF EXISTS tbl_board;
DROP TABLE IF EXISTS tbl_member;

-- tbl_member 생성
CREATE TABLE tbl_member (
  userid VARCHAR(50) NOT NULL,
  userpw VARCHAR(500) NOT NULL,
  username VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  regdate TIMESTAMP DEFAULT now(),
  updatedate TIMESTAMP DEFAULT now(),
  CONSTRAINT PK_TBL_MUMBER_USERID PRIMARY KEY(userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- tbl_board table 생성
-- tbl_board.writer = tbl_member userid
CREATE TABLE tbl_board (
  bno int NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  content TEXT,
  writer VARCHAR(50) NOT NULL,
  regdate TIMESTAMP NOT NULL DEFAULT now(),
  updateDate TIMESTAMP NOT NULL DEFAULT now(),
  viewcnt INT DEFAULT 0,
  replycnt INT DEFAULT 0,
  CONSTRAINT PK_TBL_BOARD_BNO PRIMARY KEY (bno),
  CONSTRAINT FK_TBL_BOARD_WRITER FOREIGN KEY (writer) REFERENCES tbl_member(userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tbl_reply (
  rno INT NOT NULL AUTO_INCREMENT,
  bno INT NOT NULL DEFAULT 0,
  replycontent VARCHAR(3000) NOT NULL,
  replyer VARCHAR(50) NOT NULL,
  regdate TIMESTAMP NOT NULL DEFAULT now(),
  updateDate TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT PK_TBL_REPLY_RNO PRIMARY KEY (rno),
  CONSTRAINT FK_TBL_REPLY_REPLYER FOREIGN KEY (replyer) REFERENCES tbl_member(userid),
  CONSTRAINT FK_TBL_REPLY_bno FOREIGN KEY (bno) REFERENCES tbl_board(bno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tbl_attach (
  ano INT NOT NULL AUTO_INCREMENT,
  filename VARCHAR(150) NOT NULL,
  fullname VARCHAR(150) NOT NULL,
  bno INT NOT NULL,
  description TEXT,
  regdate TIMESTAMP DEFAULT now(),
  updatedate TIMESTAMP DEFAULT now(),
  duedate TIMESTAMP DEFAULT now(),
  PRIMARY KEY(ano),
  CONSTRAINT FK_TBL_ATTACH_BNO FOREIGN KEY(bno) REFERENCES tbl_board(bno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

```
