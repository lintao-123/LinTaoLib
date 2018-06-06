use master
go
if exists(select name from sysdatabases where name='xmbook')
	drop database xmbook
create database xmbook
	on primary
(
	name='xmbook_data',
	filename='d:\xmbook\xmbook_data.mdf',
	size=5mb,
	maxsize=20mb,
	filegrowth=10%

)
log on
(
	name='xmbook',
	filename='d:\xmbook\xmbook_data.ldf',
	size=2mb,
	maxsize=10mb,
	filegrowth=10%
)
go
 

use xmbook 
go
--1.采购订单详细表
if exists(select name from sysobjects where name='procurementStatement')
	drop table procurementStatement
create table procurementStatement
(
	tempId INT identity(20180101,1) primary key not null,--编号
	procurementNo	varchar(20)	 not null,	--采购单号
	bookName	VARCHAR(50)	null,--	图书名称
	bookType varchar(20) not null,--图书类型
	bookBid	NUMERIC(8,2)	not null,--	图书进价
 	supplier	VARCHAR(50)	not null,--	供应商
	libraryTime	DATE	not null,--	采购时间
	purchaseQuantity	INT	not null,--	采购数量
	playEmp	VARCHAR(20)	not null,--	操作员
) 

insert into procurementStatement values('A001','海子的诗','诗选',15.00 , '中国书店出版社','2017-10-12' ,200, '刘斌');
insert into procurementStatement values('A002','毛泽东作品鉴赏','诗选' ,20.00 , '河北人民出版社','2017-10-12' ,220,'江流');
insert into procurementStatement values('A003','余光中经典作品','文学' ,22.80 , '当代世界出版社','2017-10-12' ,240, '朱瑾涛');
insert into procurementStatement values('A004','舒婷的诗','诗选' ,9.40 , '人民文学出版社','2017-10-12' ,260, '江流');
insert into procurementStatement values('A005','诗词韵律','诗选' ,24.00 , '文具供应商','2017-10-12' ,280, '刘斌');
insert into procurementStatement values('A006','格律诗写作技巧','语言' ,6.00 ,'青岛出版社','2017-10-12',300 , '肖云飞');
insert into procurementStatement values('A007','盛唐文化','历史' ,15.00 , '北京大学出版社','2017-10-12' ,200, '刘斌');
insert into procurementStatement values('A008','毛泽东传','文学' ,40.00 , '人民大学出版社','2017-10-12' ,210, '江流');
insert into procurementStatement values('A009','水是最好的药','医学' ,18.00 , '吉林文史出版社','2017-10-12' ,215, '朱瑾涛');
insert into procurementStatement values('A010','食物是最好的医药','医学' ,19.00, '天津大学出版社','2017-10-12',220 , '刘斌');
insert into procurementStatement values('A011','人体营养手册','医学' ,20.80 , '天津教育出版社','2017-10-12' ,225, '肖云飞');
insert into procurementStatement values('A012','留出你过冬的粮食','医学' ,20.00 , '中国时代经济出版社','2017-10-12' ,230, '刘斌');

select * from procurementStatement

--delete from procurementStatement where procurementNo=0101
go
 
use xmbook
go
--2.操作员信息表
if exists (select name from sysobjects where name='operators' )
	drop table operators
create table operators
(
	userId 	INT	identity(1,1) primary key not null,--	用户编号
	name	VARCHAR(50)	not null,--	姓名
	address	VARCHAR(50)	not null,--	地址
	telephoneNumber	VARCHAR(20)	not null,--	电话号码
	empType varchar(20) not null,--  员工类型
	empMoney numeric(8,2) not null,--员工工资
)
insert into operators values(  '刘斌', '湖南省郴州市','13378879622','采购员',3300);
insert into operators values( '朱瑾涛' ,'湖南省益阳市','1827897922','入库员',3000);
insert into operators values( '肖云飞' ,'湖南省郴州市','13678879622','销售员',3500);
insert into operators values( '江流' ,'江西省萍乡市','13378875622','操作员',3100);
insert into operators values( '李定金' ,'湖南省郴州市','13278879622','销售员',3500);
insert into operators values( '徐亚健' ,'湖南省郴州市','13778879622','采购员',3300);
insert into operators values( '刘希' ,'湖南省永州市','15372796232','入库员',3000);
insert into operators values( '周志明','湖南省郴州市','13372379622','销售员',3500);
insert into operators values( '刘欢' ,'湖南省郴州市','15378875622','操作员',3200);
insert into operators values( '乐杨' ,'湖南省郴州市','13678349622','采购员',3300);
insert into operators values( '钟诚' ,'湖南省益阳市','18823456226','入库员',3000);
insert into operators values( '方元江' ,'湖南省郴州市','15878835622','销售员',3500);
select * from  operators

go
use xmbook
go
--判断表是否存在，并删除
if exists (select name from sysobjects where name='returnInquiry')
	drop table returnInquiry;
	
--3.创建销售退货表
create table returnInquiry
(
	tempId int identity(20180201,1) primary key not null,--编号
	orderNumber varchar(20)  not null,--orderNumberid字段：主键，非空--销售单号
	bookName varchar(50) not null,--图书名称
	bookPrice numeric(8,2) not null,--图书价格
	salesVolume int not null,--销售数量
	salesPeriods date not null,--退货时间
	consoleOperator varchar(20) not null--操作员
)
go

--插入(insert)数据测试数据
insert into returnInquiry values('B001','海子的诗'  ,20.00,2 ,'2018-1-22','江流');
insert into returnInquiry values('B002','毛泽东作品鉴赏',29.00,4,'2018-1-22','江流');
insert into returnInquiry values('B003','余光中经典作品'  ,28.80,3 ,'2018-1-22','刘斌');
insert into returnInquiry values('B004','舒婷的诗'  ,15.40,5,'2018-1-22','刘斌');
insert into returnInquiry values('B005','诗词韵律'  ,30.00,1,'2018-1-22','朱瑾涛');
insert into returnInquiry values('B006','格律诗写作技巧'  ,10.00,2,'2018-1-22','李定金');
insert into returnInquiry values('B007','盛唐文化'   ,23.00,3,'2018-1-22','肖云飞');
insert into returnInquiry values('B008','毛泽东传'  ,55.00,1,'2018-1-22','肖云飞');
insert into returnInquiry values('B009','水是最好的药'   ,28.00,4,'2018-1-22','江流');
insert into returnInquiry values('B010','食物是最好的医药'  ,25.00,2,'2018-1-22','江流');
insert into returnInquiry values('B011','人体营养手册'  ,29.80,2,'2018-1-22','刘斌');
insert into returnInquiry values('B012','留出你过冬的粮食'  ,26.00,3,'2018-1-22','李定金');

select * from returnInquiry 
go
--4.采购订单表
use xmbook
go
if exists (select name from  sysobjects where name= 'procureInfo')
	drop table procureInfo
create table procureInfo
(
	tempId int identity(20180101,1) primary key not null,--编号
	procurementNo varchar(20) not null,--采购订单号
	supplier	VARCHAR(50)	not null,--	供应商
	libraryTime	DATE	not null--	采购日期	
)
insert into procureInfo values('A001','中国书店出版社','2018-01-01');
insert into procureInfo values('A002','河北人民出版社','2018-01-02');
insert into procureInfo values('A003','当代世界出版社','2018-01-04');
insert into procureInfo values('A004','人民文学出版社','2018-02-03');
insert into procureInfo values('A005','文具出版社','2018-01-05');
insert into procureInfo values('A006','青岛出版社','2018-02-01');
insert into procureInfo values('A007','北京大学出版社','2018-03-01');
insert into procureInfo values('A008','人民大学出版社','2018-01-11');
insert into procureInfo values('A009','吉林文史出版社','2018-02-15');
insert into procureInfo values('A010','天津大学出版社','2018-02-03');
insert into procureInfo values('A011','天津教育出版社','2018-04-01');
insert into procureInfo values('A012','中国时代经济出版社','2018-05-01');

go
use xmbook


--朱瑾涛数据库
go
--判断表是否存在，并删除
if exists (select name from sysobjects where name='enterInventory')
	drop table enterInventory;					
	
--5.创建图书进货表
create table enterInventory
(
	bookNo int identity(20180101,1) primary key not null,--bookNoid字段：主键，非空
	bookName varchar(50) not null,--图书名称  
	purchaseQuantity int not null,--采购数量
	bookBid numeric(8,2) not null,--进货价格
	inventoryTime date not null,--入库时间	
	consoleOperator varchar(50) not null--操作员
	
)
go

--插入(insert)数据测试数据
insert into enterInventory values('海子的诗',198,15.00,'2018-1-1','肖云飞');
insert into enterInventory values('毛泽东作品鉴赏',216,20.00,'2018-1-22','肖云飞');
insert into enterInventory values('余光中经典作品',238,22.80,'2018-1-22','江流');
insert into enterInventory values('舒婷的诗',257,9.40,'2018-1-22','江流');
insert into enterInventory values('诗词韵律',279,24.00,'2018-1-22','朱瑾涛');
insert into enterInventory values('格律诗写作技巧',298,6.00,'2018-1-22','朱瑾涛');
insert into enterInventory values('盛唐文化	',197,15.00,'2018-1-22','刘斌');
insert into enterInventory values('毛泽东传',217,40.00,'2018-1-22','肖云飞');
insert into enterInventory values('水是最好的药',213,18.00,'2018-1-22','刘斌');
insert into enterInventory values('食物是最好的医药',217,19.00,'2018-1-22','朱瑾涛');
insert into enterInventory values('人体营养手册',221,20.80,'2018-1-22','李定金');
insert into enterInventory values('留出你过冬的粮食',227,20.00,'2018-1-22','江流');

select * from enterInventory


--判断表是否存在，并删除
if exists (select name from sysobjects where name='supplierInformation')
	drop table supplierInformation;
	
--6.创建供应商表
create table supplierInformation
(
	supplierNumber  int identity(1,1) primary key not null,--supplierNumberid字段：主键，非空
	supplier varchar(50) not null,--供应商
	telephoneNumbers varchar(20) not null,--联系电话
	address varchar(50) not null,--联系地址
	contacts varchar(50) not null--联系人
)
go

--插入(insert)数据测试数据
insert into supplierInformation values('中国书店出版社',15116731192,'北京三环','许开');
insert into supplierInformation values('河北人民出版社',15173882274,'河北','徐哲');
insert into supplierInformation values('当代世界出版社',13832764324,'广西','李开复');
insert into supplierInformation values('人民文学出版社',17732782889,'郑州','王永义');
insert into supplierInformation values('文具出版社',12789327322,'太原','张山');
insert into supplierInformation values('青岛出版社',15143742872,'广州','于丹');
insert into supplierInformation values('北京大学出版社',13274327487,'陕西','汪中求');
insert into supplierInformation values('人民大学出版社',15233255324,'山西','刘庆');
insert into supplierInformation values('吉林文史出版社',17823463274,'重庆','徐维');
insert into supplierInformation values('天津大学出版社',13284732743,'珠海','冯兰');
insert into supplierInformation values('天津教育出版社',15116731192,'北京二环','刘顺');
insert into supplierInformation values('中国时代经济出版社',15118722332,'上海','葛肖');

select * from supplierInformation


--判断表是否存在，并删除
if exists (select name from sysobjects where name='memberInfo')
	drop table memberInfo;
	
--7.创建会员信息表
create table memberInfo
(
	memberId int identity(1,1) primary key not null,--memberId字段：主键，非空
	memberName varchar(50) not null,--会员姓名
	memberSex varchar(2) not null,--会员性别
	memberTelephone varchar(20) not null,--会员电话
	memberLevel varchar(20) not null,--会员等级
	memberIntegral numeric(8,2) not null--会员积分
)
go

--插入(insert)数据测试数据
insert into memberInfo values('张三','男',15372537613,'钻石会员',1000.01);
insert into memberInfo values('徐哲','男',12832873293,'黄金会员',500.01);
insert into memberInfo values('冯兰','男',13848353255,'钻石会员',1000.01);
insert into memberInfo values('刘顺','男',15234325454,'普通会员',100.01);
insert into memberInfo values('李开复','男',13784839384,'普通会员',100.01);
insert into memberInfo values('王永义','男',12763473284,'钻石会员',1000.01);
insert into memberInfo values('汪中求','男',15116731192,'钻石会员',1000.01);
insert into memberInfo values('葛肖','男',18124218743,'普通会员',100.01);
insert into memberInfo values('徐维','男',12387429398,'普通会员',100.01);
insert into memberInfo values('于丹','男',15389887223,'黄金会员',500.01);
insert into memberInfo values('黄虎','男',12472743282,'普通会员',100.01);
insert into memberInfo values('金飞','男',12164128724,'普通会员',100.01);

select * from memberInfo


--判断表是否存在，并删除
if exists (select name from sysobjects where name='memberLevel')
	drop table memberLevel;
	
--8.创建会员等级表
create table memberLevel
(
	levelNumber int identity(1,1) primary key not null,--levelNumberid字段：主键，非空
	levelName varchar(50) not null,--等级名称
	memberDiscount numeric(3,2) not null,--会员折扣
	upgradeIntegral varchar(50) not null--升级积分
)
go

--插入(insert)数据测试数据
insert into memberLevel values('钻石会员',0.72,'1000');
insert into memberLevel values('黄金会员',0.85,'500');
insert into memberLevel values('普通会员',0.9,'100');

select * from memberLevel

--判断表是否存在，并删除
if exists (select name from sysobjects where name='stockCheck')
	drop table stockCheck;
	
--9.创建库存表
create table stockCheck
(
	bookNo int identity(1,1) primary key not null,--stockCheckid字段：主键，非空
	bookName varchar(50) not null,--图书名称
	stockNamber int not null,--库存总量
)
go

--插入(insert)数据测试数据
insert into stockCheck values('海子的诗',150);
insert into stockCheck values('毛泽东作品鉴赏',170);
insert into stockCheck values('余光中经典作品',151);
insert into stockCheck values('舒婷的诗',184);
insert into stockCheck values('诗词韵律',190);
insert into stockCheck values('格律诗写作技巧',200);
insert into stockCheck values('盛唐文化',110);
insert into stockCheck values('毛泽东传',128);
insert into stockCheck values('水是最好的药',87);
insert into stockCheck values('食物是最好的医药',89);
insert into stockCheck values('人体营养手册',53);
insert into stockCheck values('留出你过冬的粮食',50);

select * from stockCheck
--肖云飞数据库
go
use xmbook
-------连接数据库
go



 if exists (select name from sysobjects where name='booktype')
     drop table booktype
     go
      
     --10.创建图书类别表
     create table booktype
     (   
       typeid int identity(1,1) primary key not null,--种类编号
       typename varchar(50) not null,--种类名称
       typedetailed varchar(50) not null,--种类描述
       )
      --插入数据测试
      
      insert into booktype values('哲学 ','人对自身反思的一个结果');   
      insert into booktype values('社会科学总论','研究各种现象的科学');
      insert into booktype values('政治 ','人类社会上层建筑的基本方面');
      insert into booktype values('军事','一个国家及政权的国防武装');
      insert into booktype values('经济','价值创造转化于实现');   
      insert into booktype values('文学','意识的产物，生活的反应');
      insert into booktype values('语言 ','人类最重要的交际工具');
      insert into booktype values('艺术','超越里个人层次的一种抽象的东西');   
      insert into booktype values('历史 ','记载和解释一系列人类活动的学科');
      insert into booktype values('名著','耳熟能详的经典之作');
      insert into booktype values('诗选','古代和现代优秀的诗集');
	  insert into booktype values('医学','关于如何调养自身和注意不良习惯');
      select * from booktype
      
      
      
      
     if exists (select name from sysobjects where name='bookdetailed')
     drop table bookdetailed
     go
      
     --11.创建销售订单表
     create table bookdetailed
           (
       bookno int identity(20180201,1) primary key not null,--销售编号
       ordernumber  varchar(20) not null,-- 销售订单号  
       bookname varchar(50) not null,--客户名称
       bookprice date not null,--销售日期
       salesman varchar(50) not null,--操作员
     
       )
      --插入数据测试
      
      insert into bookdetailed values('B001','赵丁吉','2018-2-12','刘斌');   
      insert into bookdetailed values('B002','梁巩','2018-2-13','朱瑾涛');
      insert into bookdetailed values('B003','张世意','2018-2-14','肖云飞');
      insert into bookdetailed values('B004','朱珂','2018-2-15','江流');
      insert into bookdetailed values('B005','黄盼','2018-2-16','李定金');
      insert into bookdetailed values('B006','郑敏龙','2018-2-17','徐亚健');
      insert into bookdetailed values('B007','彭波','2018-2-18','刘希');
      insert into bookdetailed values('B008','陈慧强','2018-2-19','周志明');       
      insert into bookdetailed values('B009','陈昭','2018-2-22','刘欢');
      insert into bookdetailed values('B010','刘希','2018-2-24','乐杨');
      insert into bookdetailed values('B011','杨维','2018-2-26','钟诚');
      insert into bookdetailed values('B012','罗宇谋','2018-2-28','方元江');
      
      select * from bookdetailed
      
      
      
      
     if exists (select name from sysobjects where name='bookoperation')
     drop table bookoperation
     go
      
     --12.创建图书操作表
     create table bookoperation   
           (
 
       bookno int identity(1,1) primary key not null,--图书编号
       bookname varchar(50) not null,--图书名称
       booktype varchar(50) not null,--图书种类
       bookauthor varchar(50) not null,--图书作者
       supplier varchar(50) not null,--供应商
       bookprice varchar(50) not null,--图书价格
       bookunit varchar(50) not null,--图书单位
     
       )
      --插入数据测试
      insert into bookoperation values('海子的诗','诗选','海子','中国书店出版社','20.00','本');   
      insert into bookoperation values('毛泽东作品鉴赏','文学','藏克家','河北人民出版社','29.00','本');
      insert into bookoperation values('余光中经典作品','文学','余光中','当代世界出版社','28.80','本');
      insert into bookoperation values('舒婷的诗','诗选','舒婷','人民文学出版社','15.40','本');
      insert into bookoperation values('诗词韵律','诗选','徐志刚','文具供应商','30.00','本');
      insert into bookoperation values('格律诗写作技巧','语言','王永义','青岛出版社','10.00','本');
      insert into bookoperation values('盛唐文化','历史','葛晓音','北京大学出版社','23.00','本');
      insert into bookoperation values('毛泽东传','文学','(美)特里','人民大学出版社','55.00','本');
      insert into bookoperation values('水是最好的药','医学','(美)马特','吉林文史出版社','28.00','本');
      insert into bookoperation values('食物是最好的医药','医学','[日]阿部博','天津大学出版社','25.00','本');
      insert into bookoperation values('人体营养手册','医学','(英)帕特','天津教育出版社','29.80','本');
      insert into bookoperation values('留出你过冬的粮食','医学','陈作新','中国时代经济出版社','26.00','本');       
     
      select * from bookoperation
      
      
     if exists (select name from sysobjects where name='salesinquiry')
     drop table salesinquiry
     go
      
     --13.创建销售订单详细表  
       create table salesinquiry   
           (
  
       ordernumber int identity(20180201,1) primary key not null,--编号
       ordernumberid varchar(50) not null,--销售订单号
       bookname varchar(50) not null,--图书名称
       booktype varchar(50) not null,--图书类型
       bookprice numeric(8,2) not null,--图书价格 double
       salesvolume int not null,--销售数量
       
       )
      --插入数据测试           
      insert into salesinquiry values('B001','海子的诗','诗选','20.00','50' );   
      insert into salesinquiry values('B002','毛泽东作品鉴赏','文学','29.00','50' );
      insert into salesinquiry values('B003','余光中经典作品','文学','28.80','90' );
      insert into salesinquiry values('B004','舒婷的诗','诗选','15.40','80' );
      insert into salesinquiry values('B005','诗词韵律','诗选','30.00','90' );
      insert into salesinquiry values('B006','格律诗写作技巧','语言','10.00','100' );
      insert into salesinquiry values('B007','盛唐文化','历史','23.00','90' );
      insert into salesinquiry values('B008','毛泽东传','文学','55.00','90');
      insert into salesinquiry values('B009','水是最好的药','医学','28.00','130' );
      insert into salesinquiry values('B010','食物是最好的医药','医学','25.00','120' );
      insert into salesinquiry values('B011','人体营养手册','医学','29.80','170' );
      insert into salesinquiry values('B012','留出你过冬的粮食','医学','26.00','140' );
      
     
      select * from salesinquiry
      
      
      
     if exists (select name from sysobjects where name='procure')
     drop table procure
     go
      
     --14.创建采购报损表  
       create table procure   
           (
        bookid int identity(20180101,1) primary key not null,--编号
        ordernumber varchar(50) not null,--采购订单号
        bookname varchar(50) not null,--图书名称
        salesvolume int not null,--图书数量
        bid numeric(8,2) not null,--图书进价 double
        salesperiods date not null,--报损日期 string
        operator varchar(50) not null,--操作员
           )
        --插入数据测试
        insert into procure values('A001','海子的诗','2','15.00' ,'2017-10-12','刘斌');
        insert into procure values('A002','毛泽东作品鉴赏','4','20.00' ,'2017-10-12','朱瑾涛');
        insert into procure values('A003','余光中经典作品','2','22.80' ,'2017-10-12','肖云飞');
        insert into procure values('A004','舒婷的诗','3','10' ,'2017-10-12','江流');
        insert into procure values('A005','诗词韵律','1','24.00' ,'2017-10-12','李定金');
        insert into procure values('A006','格律诗写作技巧','2','6.00' ,'2017-10-12','徐亚健');
        insert into procure values('A007','盛唐文化','3','15.00' ,'2017-10-12','刘希');
        insert into procure values('A008','毛泽东传','3','40.00' ,'2017-10-12','周志明');
        insert into procure values('A009','水是最好的药','2','18.00','2017-10-12','刘欢');
        insert into procure values('A010','食物是最好的医药','3','19.00','2017-10-12','乐杨');
        insert into procure values('A011','人体营养手册','4','20.80','2017-10-12','钟诚');
        insert into procure values('A012','留出你过冬的粮食','3','20.00','2017-10-12','方元江');
        
        select * from procure
        
        
         --刘斌数据库
         --15.销售表
    use xmbook
go
if exists(select name from sysobjects where name='salestable')
	drop table salestable

create table salestable
(
	bookno int identity(20180201,1) primary key not null,--bookNoid字段：主键，非空
	ordernumber  varchar(50) not null,-- 销售订单号 
    bookname varchar(50) not null,--图书名称  
    salesnumber int not null,--销售数量
    shouldprice numeric(8,2) not null,--应收价格
    realityprice numeric(8,2) not null,--实收价格
    salestime date not null,--销售日期
	saleser varchar(50)not null,--销售员									
)


insert into salestable values('B001','海子的诗',48,20.00,20.00,'2018-2-28','肖云飞');
insert into salestable values('B002','毛泽东作品鉴赏',46,29.00,29.00,'2018-2-28','朱瑾涛');
insert into salestable values('B003','余光中经典作品',87,28.80,28.80,'2018-2-28','江流');
insert into salestable values('B004','舒婷的诗',75,15.40,15.40,'2018-2-28','李定金');
insert into salestable values('B005','诗词韵律',89,30.00,30.00,'2018-2-28','肖云飞');
insert into salestable values('B006','格律诗写作技巧',98,10.00,10.00,'2018-2-28','朱瑾涛');
insert into salestable values('B007','盛唐文化',87,23.00,23.00,'2018-2-28','江流');
insert into salestable values('B008','毛泽东传',89,55.00,55.00,'2018-2-28','刘斌');
insert into salestable values('B009','水是最好的药',126,28.00,28.00,'2018-2-28','李定金');
insert into salestable values('B010','食物是最好的医药',118,25.00,25.00,'2018-2-28','李定金');
insert into salestable values('B011','人体营养手册',168,29.80,29.80,'2018-2-28','朱瑾涛');
insert into salestable values('B011','留出你过冬的粮食',137,26.00,26.00,'2018-2-28','朱瑾涛');


select * from salestable
