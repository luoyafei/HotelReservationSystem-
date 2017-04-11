create database Hotel;
use Hote;

create table t_admin (
	adminId varchar(32) primary key,
	adminNum varchar(32),
	adminPwd varchar(32),
	recentLogin datetime
) engine=innoDB default charset=utf8;

create table t_hotel (
	hotelId varchar(32) primary key, 
	hotelName varchar(32),
	hotelAddress varchar(255),
	inOUtTimeDesc varchar(255),
	hotelTel varchar(32),
	internetService varchar(255),
	openTime varchar(255),
	hotelDevice varchar(255),
	hotelService varchar(255),
	hotelBrief varchar(255),
	browseMap varchar(255),
	createDate datetime default current_timestamp
) engine=innoDB default charset=utf8;

create table t_house (
	houseId varchar(32) primary key,
	belongHotelId varchar(32),
	browseMap varchar(255),
	houseInfo varchar(255),
	cheapService varchar(255),
	price varchar(11),
	isOrder varchar(2),
	createDate datetime default current_timestamp,
	constraint foreign key (belongHotelId) references t_hotel (hotelId)
) engine=innoDB default charset=utf8;

create table t_subscribe (
	subscribeId varchar(32) primary key,
	userId varchar(32),
	hotelId varchar(32),
	houseId varchar(32),
	isUnsubscribe varchar(2),
	subscribeTime datetime,
	unsubscribeTime datetime,
	constraint foreign key (userId) references t_user (userId),
	constraint foreign key (hotelId) references t_hotel (hotelId),
	constraint foreign key (houseId) references t_house (houseId)
) engine=innoDB default charset=utf8;

create table t_user (
	userId varchar(32) primary key,
	userTel varchar(32),
	userPassword varchar(32),
	userIntegral varchar(11)
) engine=innoDB default charset=utf8;

