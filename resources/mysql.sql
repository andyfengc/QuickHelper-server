AIzaSyAPG-oD1I4NRGBcvo5wypBp7_itdhjd8t8

create database quickhelper DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use quickhelper;

create table User(
	Id bigint AUTO_INCREMENT not null,
	Avatar varchar(255),
	CreatedTime datetime,
	UserName varchar(50),
	NickName varchar(50),
	Password varchar(50),
	Email varchar(50),
	PhoneNumber varchar(20),
	primary key (Id)
);


create table Category(
	Id bigint AUTO_INCREMENT not null,
	Name varchar(255) not null,
	primary key (Id)
);

create table Task(
	Id bigint AUTO_INCREMENT not null,
	AuthorId bigint not null,
	CategoryId bigint not null,
	Title varchar(255) not null,
	Content varchar(4000),
	CreatedTime datetime,
	StartTime datetime,
	EndTime datetime,
	Price float,
	Address varchar(255),
	primary key (Id),
	foreign key (AuthorId) references User(Id),
	foreign key (CategoryId) references Category(Id)
);


create table Message(
	Id bigint AUTO_INCREMENT not null,
	SenderId bigint not null,
	ReceiverId bigint not null,
	TaskId bigint not null,
	ParentMessageId bigint not null,
	Text varchar(4000),
	CreatedTime datetime,
	primary key (Id),
	foreign key (SenderId) references User(Id),
	foreign key (ReceiverId) references User(Id),
	foreign key (TaskId) references Task(Id),
	foreign key (ParentMessageId) references Message(Id)
);


-- insert data
use quickhelper;
-- populate users
insert into User
	(Avatar, CreatedTime, UserName, NickName, Password, Email, PhoneNumber)
value
	('https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/8/005/01e/0bd/31f0f9f.jpg', '2017-08-08 11:30:50', 'andyf', 'Andy', '123', 'andyinbox3@gmail.com', '647-123-1234');
insert into User
	(Avatar, CreatedTime, UserName, NickName, Password, Email, PhoneNumber)
value
	('https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAJSAAAAJDI1ZmE4YzgxLWEzZGUtNDFhZi04Yzg0LTMwNzBkYjgzY2ZiNQ.jpg', '2017-07-15 11:30:50', 'rachaelh', 'Rachael', '123', 'andyinbox4@gmail.com', '207-123-1234');
insert into User
	(Avatar, CreatedTime, UserName, NickName, Password, Email, PhoneNumber)
value
	('https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/2/005/04e/2ad/12b1dec.jpg', '2017-05-21 11:30:50', 'johnm', 'John', '123', 'andyinbox5@gmail.com', '625-123-1234');
insert into User
	(Avatar, CreatedTime, UserName, NickName, Password, Email, PhoneNumber)
value
	('https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/5/005/056/187/2fb767d.jpg', '2017-04-5 11:30:50', 'judys', 'Judy', '123', 'andyinbox6@gmail.com', '416-123-1234');

-- populate category
insert into Category (Name) values ('接送');
insert into Category (Name) values ('外卖');
insert into Category (Name) values ('房屋日租');
insert into Category (Name) values ('扫雪');
insert into Category (Name) values ('打扫卫生');
insert into Category (Name) values ('修理炉子');
insert into Category (Name) values ('清理管道');
insert into Category (Name) values ('整理花园');
insert into Category (Name) values ('月嫂');
insert into Category (Name) values ('临时看护');

