##创建数据库
CREATE DATABASE empdb;

##打开数据库
USE empdb;

##创建部门表
CREATE TABLE dep(
  depid INT PRIMARY KEY AUTO_INCREMENT,
  depname VARCHAR(50) NOT NULL
);

##创建福利表
CREATE TABLE welfare(
  wid INT PRIMARY KEY AUTO_INCREMENT,
  wname VARCHAR(50) NOT NULL
);

##创建员工表
CREATE TABLE emp(
  eid INT PRIMARY KEY AUTO_INCREMENT,
  ename VARCHAR(50) NOT NULL,
  sex VARCHAR(4) DEFAULT '男',
  address VARCHAR(150),
  birthday DATE,
  photo VARCHAR(50),
  depid INT NOT NULL,  
  CONSTRAINT fk_depid FOREIGN KEY (depid) REFERENCES dep(depid)
);

##创建薪资表(薪资与员工为1对1关联)
CREATE TABLE salary(
  sid INT PRIMARY KEY AUTO_INCREMENT,
  eid INT NOT NULL UNIQUE,
  emoney FLOAT CHECK (emoney>2000),
  CONSTRAINT fk_sa_eid FOREIGN KEY (eid) REFERENCES emp(eid)  
);


##创建员工福利关系表
CREATE TABLE empwelfare(
  ewid INT PRIMARY KEY AUTO_INCREMENT,
  eid INT NOT NULL,
  wid INT NOT NULL,
  CONSTRAINT fk_ewf_eid FOREIGN KEY (eid) REFERENCES emp(eid),
  CONSTRAINT fk_ewf_wid FOREIGN KEY (wid) REFERENCES welfare(wid)
);

##插入初始化数据
INSERT INTO dep(depname) VALUES('技术部');
INSERT INTO dep(depname) VALUES('市场部');
INSERT INTO dep(depname) VALUES('行政部');
INSERT INTO dep(depname) VALUES('人事部');
INSERT INTO dep(depname) VALUES('研发部');


INSERT INTO welfare(wname) VALUES('住房补贴');
INSERT INTO welfare(wname) VALUES('交通费');
INSERT INTO welfare(wname) VALUES('差旅费');
INSERT INTO welfare(wname) VALUES('误餐费');
INSERT INTO welfare(wname) VALUES('加班费');
INSERT INTO welfare(wname) VALUES('降温费');
INSERT INTO welfare(wname) VALUES('取暖费');
INSERT INTO welfare(wname) VALUES('三金五险');


SELECT w.* FROM empwelfare ew,welfare w WHERE ew.wid=w.wid;

SELECT e.*,d.depname FROM emp e,dep d WHERE 1=1 ;





