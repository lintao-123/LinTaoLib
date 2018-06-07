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
--1.�ɹ�������ϸ��
if exists(select name from sysobjects where name='procurementStatement')
	drop table procurementStatement
create table procurementStatement
(
	tempId INT identity(20180101,1) primary key not null,--���
	procurementNo	varchar(20)	 not null,	--�ɹ�����
	bookName	VARCHAR(50)	null,--	ͼ������
	bookType varchar(20) not null,--ͼ������
	bookBid	NUMERIC(8,2)	not null,--	ͼ�����
 	supplier	VARCHAR(50)	not null,--	��Ӧ��
	libraryTime	DATE	not null,--	�ɹ�ʱ��
	purchaseQuantity	INT	not null,--	�ɹ�����
	playEmp	VARCHAR(20)	not null,--	����Ա
) 

insert into procurementStatement values('A001','���ӵ�ʫ','ʫѡ',15.00 , '�й���������','2017-10-12' ,200, '����');
insert into procurementStatement values('A002','ë����Ʒ����','ʫѡ' ,20.00 , '�ӱ����������','2017-10-12' ,220,'����');
insert into procurementStatement values('A003','����о�����Ʒ','��ѧ' ,22.80 , '�������������','2017-10-12' ,240, '�����');
insert into procurementStatement values('A004','���õ�ʫ','ʫѡ' ,9.40 , '������ѧ������','2017-10-12' ,260, '����');
insert into procurementStatement values('A005','ʫ������','ʫѡ' ,24.00 , '�ľ߹�Ӧ��','2017-10-12' ,280, '����');
insert into procurementStatement values('A006','����ʫд������','����' ,6.00 ,'�ൺ������','2017-10-12',300 , 'Ф�Ʒ�');
insert into procurementStatement values('A007','ʢ���Ļ�','��ʷ' ,15.00 , '������ѧ������','2017-10-12' ,200, '����');
insert into procurementStatement values('A008','ë�󶫴�','��ѧ' ,40.00 , '�����ѧ������','2017-10-12' ,210, '����');
insert into procurementStatement values('A009','ˮ����õ�ҩ','ҽѧ' ,18.00 , '������ʷ������','2017-10-12' ,215, '�����');
insert into procurementStatement values('A010','ʳ������õ�ҽҩ','ҽѧ' ,19.00, '����ѧ������','2017-10-12',220 , '����');
insert into procurementStatement values('A011','����Ӫ���ֲ�','ҽѧ' ,20.80 , '������������','2017-10-12' ,225, 'Ф�Ʒ�');
insert into procurementStatement values('A012','�������������ʳ','ҽѧ' ,20.00 , '�й�ʱ�����ó�����','2017-10-12' ,230, '����');

select * from procurementStatement

--delete from procurementStatement where procurementNo=0101
go
 
use xmbook
go
--2.����Ա��Ϣ��
if exists (select name from sysobjects where name='operators' )
	drop table operators
create table operators
(
	userId 	INT	identity(1,1) primary key not null,--	�û����
	name	VARCHAR(50)	not null,--	����
	address	VARCHAR(50)	not null,--	��ַ
	telephoneNumber	VARCHAR(20)	not null,--	�绰����
	empType varchar(20) not null,--  Ա������
	empMoney numeric(8,2) not null,--Ա������
)
insert into operators values(  '����', '����ʡ������','13378879622','�ɹ�Ա',3300);
insert into operators values( '�����' ,'����ʡ������','1827897922','���Ա',3000);
insert into operators values( 'Ф�Ʒ�' ,'����ʡ������','13678879622','����Ա',3500);
insert into operators values( '����' ,'����ʡƼ����','13378875622','����Ա',3100);
insert into operators values( '���' ,'����ʡ������','13278879622','����Ա',3500);
insert into operators values( '���ǽ�' ,'����ʡ������','13778879622','�ɹ�Ա',3300);
insert into operators values( '��ϣ' ,'����ʡ������','15372796232','���Ա',3000);
insert into operators values( '��־��','����ʡ������','13372379622','����Ա',3500);
insert into operators values( '����' ,'����ʡ������','15378875622','����Ա',3200);
insert into operators values( '����' ,'����ʡ������','13678349622','�ɹ�Ա',3300);
insert into operators values( '�ӳ�' ,'����ʡ������','18823456226','���Ա',3000);
insert into operators values( '��Ԫ��' ,'����ʡ������','15878835622','����Ա',3500);
select * from  operators

go
use xmbook
go
--�жϱ��Ƿ���ڣ���ɾ��
if exists (select name from sysobjects where name='returnInquiry')
	drop table returnInquiry;
	
--3.���������˻���
create table returnInquiry
(
	tempId int identity(20180201,1) primary key not null,--���
	orderNumber varchar(20)  not null,--orderNumberid�ֶΣ��������ǿ�--���۵���
	bookName varchar(50) not null,--ͼ������
	bookPrice numeric(8,2) not null,--ͼ��۸�
	salesVolume int not null,--��������
	salesPeriods date not null,--�˻�ʱ��
	consoleOperator varchar(20) not null--����Ա
)
go

--����(insert)���ݲ�������
insert into returnInquiry values('B001','���ӵ�ʫ'  ,20.00,2 ,'2018-1-22','����');
insert into returnInquiry values('B002','ë����Ʒ����',29.00,4,'2018-1-22','����');
insert into returnInquiry values('B003','����о�����Ʒ'  ,28.80,3 ,'2018-1-22','����');
insert into returnInquiry values('B004','���õ�ʫ'  ,15.40,5,'2018-1-22','����');
insert into returnInquiry values('B005','ʫ������'  ,30.00,1,'2018-1-22','�����');
insert into returnInquiry values('B006','����ʫд������'  ,10.00,2,'2018-1-22','���');
insert into returnInquiry values('B007','ʢ���Ļ�'   ,23.00,3,'2018-1-22','Ф�Ʒ�');
insert into returnInquiry values('B008','ë�󶫴�'  ,55.00,1,'2018-1-22','Ф�Ʒ�');
insert into returnInquiry values('B009','ˮ����õ�ҩ'   ,28.00,4,'2018-1-22','����');
insert into returnInquiry values('B010','ʳ������õ�ҽҩ'  ,25.00,2,'2018-1-22','����');
insert into returnInquiry values('B011','����Ӫ���ֲ�'  ,29.80,2,'2018-1-22','����');
insert into returnInquiry values('B012','�������������ʳ'  ,26.00,3,'2018-1-22','���');

select * from returnInquiry 
go
--4.�ɹ�������
use xmbook
go
if exists (select name from  sysobjects where name= 'procureInfo')
	drop table procureInfo
create table procureInfo
(
	tempId int identity(20180101,1) primary key not null,--���
	procurementNo varchar(20) not null,--�ɹ�������
	supplier	VARCHAR(50)	not null,--	��Ӧ��
	libraryTime	DATE	not null--	�ɹ�����	
)
insert into procureInfo values('A001','�й���������','2018-01-01');
insert into procureInfo values('A002','�ӱ����������','2018-01-02');
insert into procureInfo values('A003','�������������','2018-01-04');
insert into procureInfo values('A004','������ѧ������','2018-02-03');
insert into procureInfo values('A005','�ľ߳�����','2018-01-05');
insert into procureInfo values('A006','�ൺ������','2018-02-01');
insert into procureInfo values('A007','������ѧ������','2018-03-01');
insert into procureInfo values('A008','�����ѧ������','2018-01-11');
insert into procureInfo values('A009','������ʷ������','2018-02-15');
insert into procureInfo values('A010','����ѧ������','2018-02-03');
insert into procureInfo values('A011','������������','2018-04-01');
insert into procureInfo values('A012','�й�ʱ�����ó�����','2018-05-01');

go
use xmbook


--��������ݿ�
go
--�жϱ��Ƿ���ڣ���ɾ��
if exists (select name from sysobjects where name='enterInventory')
	drop table enterInventory;					
	
--5.����ͼ�������
create table enterInventory
(
	bookNo int identity(20180101,1) primary key not null,--bookNoid�ֶΣ��������ǿ�
	bookName varchar(50) not null,--ͼ������  
	purchaseQuantity int not null,--�ɹ�����
	bookBid numeric(8,2) not null,--�����۸�
	inventoryTime date not null,--���ʱ��	
	consoleOperator varchar(50) not null--����Ա
	
)
go

--����(insert)���ݲ�������
insert into enterInventory values('���ӵ�ʫ',198,15.00,'2018-1-1','Ф�Ʒ�');
insert into enterInventory values('ë����Ʒ����',216,20.00,'2018-1-22','Ф�Ʒ�');
insert into enterInventory values('����о�����Ʒ',238,22.80,'2018-1-22','����');
insert into enterInventory values('���õ�ʫ',257,9.40,'2018-1-22','����');
insert into enterInventory values('ʫ������',279,24.00,'2018-1-22','�����');
insert into enterInventory values('����ʫд������',298,6.00,'2018-1-22','�����');
insert into enterInventory values('ʢ���Ļ�	',197,15.00,'2018-1-22','����');
insert into enterInventory values('ë�󶫴�',217,40.00,'2018-1-22','Ф�Ʒ�');
insert into enterInventory values('ˮ����õ�ҩ',213,18.00,'2018-1-22','����');
insert into enterInventory values('ʳ������õ�ҽҩ',217,19.00,'2018-1-22','�����');
insert into enterInventory values('����Ӫ���ֲ�',221,20.80,'2018-1-22','���');
insert into enterInventory values('�������������ʳ',227,20.00,'2018-1-22','����');

select * from enterInventory


--�жϱ��Ƿ���ڣ���ɾ��
if exists (select name from sysobjects where name='supplierInformation')
	drop table supplierInformation;
	
--6.������Ӧ�̱�
create table supplierInformation
(
	supplierNumber  int identity(1,1) primary key not null,--supplierNumberid�ֶΣ��������ǿ�
	supplier varchar(50) not null,--��Ӧ��
	telephoneNumbers varchar(20) not null,--��ϵ�绰
	address varchar(50) not null,--��ϵ��ַ
	contacts varchar(50) not null--��ϵ��
)
go

--����(insert)���ݲ�������
insert into supplierInformation values('�й���������',15116731192,'��������','��');
insert into supplierInformation values('�ӱ����������',15173882274,'�ӱ�','����');
insert into supplierInformation values('�������������',13832764324,'����','���');
insert into supplierInformation values('������ѧ������',17732782889,'֣��','������');
insert into supplierInformation values('�ľ߳�����',12789327322,'̫ԭ','��ɽ');
insert into supplierInformation values('�ൺ������',15143742872,'����','�ڵ�');
insert into supplierInformation values('������ѧ������',13274327487,'����','������');
insert into supplierInformation values('�����ѧ������',15233255324,'ɽ��','����');
insert into supplierInformation values('������ʷ������',17823463274,'����','��ά');
insert into supplierInformation values('����ѧ������',13284732743,'�麣','����');
insert into supplierInformation values('������������',15116731192,'��������','��˳');
insert into supplierInformation values('�й�ʱ�����ó�����',15118722332,'�Ϻ�','��Ф');

select * from supplierInformation


--�жϱ��Ƿ���ڣ���ɾ��
if exists (select name from sysobjects where name='memberInfo')
	drop table memberInfo;
	
--7.������Ա��Ϣ��
create table memberInfo
(
	memberId int identity(1,1) primary key not null,--memberId�ֶΣ��������ǿ�
	memberName varchar(50) not null,--��Ա����
	memberSex varchar(2) not null,--��Ա�Ա�
	memberTelephone varchar(20) not null,--��Ա�绰
	memberLevel varchar(20) not null,--��Ա�ȼ�
	memberIntegral numeric(8,2) not null--��Ա����
)
go

--����(insert)���ݲ�������
insert into memberInfo values('����','��',15372537613,'��ʯ��Ա',1000.01);
insert into memberInfo values('����','��',12832873293,'�ƽ��Ա',500.01);
insert into memberInfo values('����','��',13848353255,'��ʯ��Ա',1000.01);
insert into memberInfo values('��˳','��',15234325454,'��ͨ��Ա',100.01);
insert into memberInfo values('���','��',13784839384,'��ͨ��Ա',100.01);
insert into memberInfo values('������','��',12763473284,'��ʯ��Ա',1000.01);
insert into memberInfo values('������','��',15116731192,'��ʯ��Ա',1000.01);
insert into memberInfo values('��Ф','��',18124218743,'��ͨ��Ա',100.01);
insert into memberInfo values('��ά','��',12387429398,'��ͨ��Ա',100.01);
insert into memberInfo values('�ڵ�','��',15389887223,'�ƽ��Ա',500.01);
insert into memberInfo values('�ƻ�','��',12472743282,'��ͨ��Ա',100.01);
insert into memberInfo values('���','��',12164128724,'��ͨ��Ա',100.01);

select * from memberInfo


--�жϱ��Ƿ���ڣ���ɾ��
if exists (select name from sysobjects where name='memberLevel')
	drop table memberLevel;
	
--8.������Ա�ȼ���
create table memberLevel
(
	levelNumber int identity(1,1) primary key not null,--levelNumberid�ֶΣ��������ǿ�
	levelName varchar(50) not null,--�ȼ�����
	memberDiscount numeric(3,2) not null,--��Ա�ۿ�
	upgradeIntegral varchar(50) not null--��������
)
go

--����(insert)���ݲ�������
insert into memberLevel values('��ʯ��Ա',0.72,'1000');
insert into memberLevel values('�ƽ��Ա',0.85,'500');
insert into memberLevel values('��ͨ��Ա',0.9,'100');

select * from memberLevel

--�жϱ��Ƿ���ڣ���ɾ��
if exists (select name from sysobjects where name='stockCheck')
	drop table stockCheck;
	
--9.��������
create table stockCheck
(
	bookNo int identity(1,1) primary key not null,--stockCheckid�ֶΣ��������ǿ�
	bookName varchar(50) not null,--ͼ������
	stockNamber int not null,--�������
)
go

--����(insert)���ݲ�������
insert into stockCheck values('���ӵ�ʫ',150);
insert into stockCheck values('ë����Ʒ����',170);
insert into stockCheck values('����о�����Ʒ',151);
insert into stockCheck values('���õ�ʫ',184);
insert into stockCheck values('ʫ������',190);
insert into stockCheck values('����ʫд������',200);
insert into stockCheck values('ʢ���Ļ�',110);
insert into stockCheck values('ë�󶫴�',128);
insert into stockCheck values('ˮ����õ�ҩ',87);
insert into stockCheck values('ʳ������õ�ҽҩ',89);
insert into stockCheck values('����Ӫ���ֲ�',53);
insert into stockCheck values('�������������ʳ',50);

select * from stockCheck
--Ф�Ʒ����ݿ�
go
use xmbook
-------�������ݿ�
go



 if exists (select name from sysobjects where name='booktype')
     drop table booktype
     go
      
     --10.����ͼ������
     create table booktype
     (   
       typeid int identity(1,1) primary key not null,--������
       typename varchar(50) not null,--��������
       typedetailed varchar(50) not null,--��������
       )
      --�������ݲ���
      
      insert into booktype values('��ѧ ','�˶�����˼��һ�����');   
      insert into booktype values('����ѧ����','�о���������Ŀ�ѧ');
      insert into booktype values('���� ','��������ϲ㽨���Ļ�������');
      insert into booktype values('����','һ�����Ҽ���Ȩ�Ĺ�����װ');
      insert into booktype values('����','��ֵ����ת����ʵ��');   
      insert into booktype values('��ѧ','��ʶ�Ĳ������ķ�Ӧ');
      insert into booktype values('���� ','��������Ҫ�Ľ��ʹ���');
      insert into booktype values('����','��Խ����˲�ε�һ�ֳ���Ķ���');   
      insert into booktype values('��ʷ ','���غͽ���һϵ��������ѧ��');
      insert into booktype values('����','��������ľ���֮��');
      insert into booktype values('ʫѡ','�Ŵ����ִ������ʫ��');
	  insert into booktype values('ҽѧ','������ε��������ע�ⲻ��ϰ��');
      select * from booktype
      
      
      
      
     if exists (select name from sysobjects where name='bookdetailed')
     drop table bookdetailed
     go
      
     --11.�������۶�����
     create table bookdetailed
           (
       bookno int identity(20180201,1) primary key not null,--���۱��
       ordernumber  varchar(20) not null,-- ���۶�����  
       bookname varchar(50) not null,--�ͻ�����
       bookprice date not null,--��������
       salesman varchar(50) not null,--����Ա
     
       )
      --�������ݲ���
      
      insert into bookdetailed values('B001','�Զ���','2018-2-12','����');   
      insert into bookdetailed values('B002','����','2018-2-13','�����');
      insert into bookdetailed values('B003','������','2018-2-14','Ф�Ʒ�');
      insert into bookdetailed values('B004','����','2018-2-15','����');
      insert into bookdetailed values('B005','����','2018-2-16','���');
      insert into bookdetailed values('B006','֣����','2018-2-17','���ǽ�');
      insert into bookdetailed values('B007','��','2018-2-18','��ϣ');
      insert into bookdetailed values('B008','�»�ǿ','2018-2-19','��־��');       
      insert into bookdetailed values('B009','����','2018-2-22','����');
      insert into bookdetailed values('B010','��ϣ','2018-2-24','����');
      insert into bookdetailed values('B011','��ά','2018-2-26','�ӳ�');
      insert into bookdetailed values('B012','����ı','2018-2-28','��Ԫ��');
      
      select * from bookdetailed
      
      
      
      
     if exists (select name from sysobjects where name='bookoperation')
     drop table bookoperation
     go
      
     --12.����ͼ�������
     create table bookoperation   
           (
 
       bookno int identity(1,1) primary key not null,--ͼ����
       bookname varchar(50) not null,--ͼ������
       booktype varchar(50) not null,--ͼ������
       bookauthor varchar(50) not null,--ͼ������
       supplier varchar(50) not null,--��Ӧ��
       bookprice varchar(50) not null,--ͼ��۸�
       bookunit varchar(50) not null,--ͼ�鵥λ
     
       )
      --�������ݲ���
      insert into bookoperation values('���ӵ�ʫ','ʫѡ','����','�й���������','20.00','��');   
      insert into bookoperation values('ë����Ʒ����','��ѧ','�ؿ˼�','�ӱ����������','29.00','��');
      insert into bookoperation values('����о�����Ʒ','��ѧ','�����','�������������','28.80','��');
      insert into bookoperation values('���õ�ʫ','ʫѡ','����','������ѧ������','15.40','��');
      insert into bookoperation values('ʫ������','ʫѡ','��־��','�ľ߹�Ӧ��','30.00','��');
      insert into bookoperation values('����ʫд������','����','������','�ൺ������','10.00','��');
      insert into bookoperation values('ʢ���Ļ�','��ʷ','������','������ѧ������','23.00','��');
      insert into bookoperation values('ë�󶫴�','��ѧ','(��)����','�����ѧ������','55.00','��');
      insert into bookoperation values('ˮ����õ�ҩ','ҽѧ','(��)����','������ʷ������','28.00','��');
      insert into bookoperation values('ʳ������õ�ҽҩ','ҽѧ','[��]������','����ѧ������','25.00','��');
      insert into bookoperation values('����Ӫ���ֲ�','ҽѧ','(Ӣ)����','������������','29.80','��');
      insert into bookoperation values('�������������ʳ','ҽѧ','������','�й�ʱ�����ó�����','26.00','��');       
     
      select * from bookoperation
      
      
     if exists (select name from sysobjects where name='salesinquiry')
     drop table salesinquiry
     go
      
     --13.�������۶�����ϸ��  
       create table salesinquiry   
           (
  
       ordernumber int identity(20180201,1) primary key not null,--���
       ordernumberid varchar(50) not null,--���۶�����
       bookname varchar(50) not null,--ͼ������
       booktype varchar(50) not null,--ͼ������
       bookprice numeric(8,2) not null,--ͼ��۸� double
       salesvolume int not null,--��������
       
       )
      --�������ݲ���           
      insert into salesinquiry values('B001','���ӵ�ʫ','ʫѡ','20.00','50' );   
      insert into salesinquiry values('B002','ë����Ʒ����','��ѧ','29.00','50' );
      insert into salesinquiry values('B003','����о�����Ʒ','��ѧ','28.80','90' );
      insert into salesinquiry values('B004','���õ�ʫ','ʫѡ','15.40','80' );
      insert into salesinquiry values('B005','ʫ������','ʫѡ','30.00','90' );
      insert into salesinquiry values('B006','����ʫд������','����','10.00','100' );
      insert into salesinquiry values('B007','ʢ���Ļ�','��ʷ','23.00','90' );
      insert into salesinquiry values('B008','ë�󶫴�','��ѧ','55.00','90');
      insert into salesinquiry values('B009','ˮ����õ�ҩ','ҽѧ','28.00','130' );
      insert into salesinquiry values('B010','ʳ������õ�ҽҩ','ҽѧ','25.00','120' );
      insert into salesinquiry values('B011','����Ӫ���ֲ�','ҽѧ','29.80','170' );
      insert into salesinquiry values('B012','�������������ʳ','ҽѧ','26.00','140' );
      
     
      select * from salesinquiry
      
      
      
     if exists (select name from sysobjects where name='procure')
     drop table procure
     go
      
     --14.�����ɹ������  
       create table procure   
           (
        bookid int identity(20180101,1) primary key not null,--���
        ordernumber varchar(50) not null,--�ɹ�������
        bookname varchar(50) not null,--ͼ������
        salesvolume int not null,--ͼ������
        bid numeric(8,2) not null,--ͼ����� double
        salesperiods date not null,--�������� string
        operator varchar(50) not null,--����Ա
           )
        --�������ݲ���
        insert into procure values('A001','���ӵ�ʫ','2','15.00' ,'2017-10-12','����');
        insert into procure values('A002','ë����Ʒ����','4','20.00' ,'2017-10-12','�����');
        insert into procure values('A003','����о�����Ʒ','2','22.80' ,'2017-10-12','Ф�Ʒ�');
        insert into procure values('A004','���õ�ʫ','3','10' ,'2017-10-12','����');
        insert into procure values('A005','ʫ������','1','24.00' ,'2017-10-12','���');
        insert into procure values('A006','����ʫд������','2','6.00' ,'2017-10-12','���ǽ�');
        insert into procure values('A007','ʢ���Ļ�','3','15.00' ,'2017-10-12','��ϣ');
        insert into procure values('A008','ë�󶫴�','3','40.00' ,'2017-10-12','��־��');
        insert into procure values('A009','ˮ����õ�ҩ','2','18.00','2017-10-12','����');
        insert into procure values('A010','ʳ������õ�ҽҩ','3','19.00','2017-10-12','����');
        insert into procure values('A011','����Ӫ���ֲ�','4','20.80','2017-10-12','�ӳ�');
        insert into procure values('A012','�������������ʳ','3','20.00','2017-10-12','��Ԫ��');
        
        select * from procure
        
        
         --�������ݿ�
         --15.���۱�
    use xmbook
go
if exists(select name from sysobjects where name='salestable')
	drop table salestable

create table salestable
(
	bookno int identity(20180201,1) primary key not null,--bookNoid�ֶΣ��������ǿ�
	ordernumber  varchar(50) not null,-- ���۶����� 
    bookname varchar(50) not null,--ͼ������  
    salesnumber int not null,--��������
    shouldprice numeric(8,2) not null,--Ӧ�ռ۸�
    realityprice numeric(8,2) not null,--ʵ�ռ۸�
    salestime date not null,--��������
	saleser varchar(50)not null,--����Ա									
)


insert into salestable values('B001','���ӵ�ʫ',48,20.00,20.00,'2018-2-28','Ф�Ʒ�');
insert into salestable values('B002','ë����Ʒ����',46,29.00,29.00,'2018-2-28','�����');
insert into salestable values('B003','����о�����Ʒ',87,28.80,28.80,'2018-2-28','����');
insert into salestable values('B004','���õ�ʫ',75,15.40,15.40,'2018-2-28','���');
insert into salestable values('B005','ʫ������',89,30.00,30.00,'2018-2-28','Ф�Ʒ�');
insert into salestable values('B006','����ʫд������',98,10.00,10.00,'2018-2-28','�����');
insert into salestable values('B007','ʢ���Ļ�',87,23.00,23.00,'2018-2-28','����');
insert into salestable values('B008','ë�󶫴�',89,55.00,55.00,'2018-2-28','����');
insert into salestable values('B009','ˮ����õ�ҩ',126,28.00,28.00,'2018-2-28','���');
insert into salestable values('B010','ʳ������õ�ҽҩ',118,25.00,25.00,'2018-2-28','���');
insert into salestable values('B011','����Ӫ���ֲ�',168,29.80,29.80,'2018-2-28','�����');
insert into salestable values('B011','�������������ʳ',137,26.00,26.00,'2018-2-28','�����');


select * from salestable
