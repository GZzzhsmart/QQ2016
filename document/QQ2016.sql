/*
Navicat SQL Server Data Transfer

Source Server         : SqlServer连接
Source Server Version : 90000
Source Host           : 127.0.0.1:1433
Source Database       : QQ2016
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 90000
File Encoding         : 65001

Date: 2017-09-20 13:59:22
*/


-- ----------------------------
-- Table structure for Account
-- ----------------------------
DROP TABLE [dbo].[Account]
GO
CREATE TABLE [dbo].[Account] (
[qqCode] int NOT NULL ,
[nickName] varchar(30) NOT NULL ,
[headImg] varchar(50) NOT NULL ,
[password] varchar(20) NOT NULL ,
[age] int NOT NULL ,
[sex] char(4) NOT NULL ,
[star] varchar(20) NOT NULL ,
[blood] varchar(10) NULL ,
[nation] varchar(30) NULL ,
[hobit] varchar(100) NULL ,
[ipAddr] varchar(20) NULL ,
[port] int NULL ,
[onlinestatus] varchar(4) NULL ,
[remark] varchar(100) NULL 
)


GO

-- ----------------------------
-- Records of Account
-- ----------------------------
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'1763', N'受伤的总是我', N'head/8.jpg', N'123', N'20', N'男神', N'双子座', N'A', N'汉族', N'打篮球，上网，跑步', N'192.168.0.9', N'31692', N'离线', N'坚持自己')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'3374', N'风雨无阻', N'head/4.jpg', N'1234', N'30', N'男神', N'射手座', N'A', N'汉族', N'上班，爬山，旅游', N'127.0.0.1', N'0', N'离线', N'有风有雨，有你有我')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'6286', N'爱上你', N'head/2.jpg', N'1234', N'20', N'女神', N'处女座', N'A', N'维吾尔族', N'逛街，挣钱', N'127.0.0.1', N'0', N'离线', N'挣钱')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'6767', N'杏花雨', N'head/2.jpg', N'1234', N'18', N'男神', N'处女座', N'A', N'汉族', N'逛街，上网，游泳', N'127.0.0.1', N'0', N'离线', N'人生没有彩排，每天都是现场直播')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'6998', N'我', N'head/9.jpg', N'1234', N'19', N'男神', N'天蝎座', N'A', N'汉族', N'爬山，上网', N'127.0.0.1', N'0', N'离线', N'谈恋爱')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'7447', N'曲未终人却散', N'head/9.jpg', N'1234', N'19', N'男神', N'摩羯座', N'A', N'汉族', N'爬山，跑步，上网', N'127.0.0.1', N'0', N'离线', N'有志者事竟成')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'31266', N'我爱的是你爱我', N'head/8.jpg', N'1234', N'19', N'男神', N'摩羯座', N'A', N'汉族', N'上网，跑步，逛街，旅游', N'127.0.0.1', N'0', N'离线', N'世界那么大，我想出去走走')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'20108152', N'admin', N'head/1.jpg', N'1234', N'19', N'男神', N'天蝎座', N'A', N'汉族', N'上网，逛街，爬山，跑步，打篮球，看电影', N'127.0.0.1', N'0', N'离线', N'我喜欢你啊')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'23073484', N'小赖子', N'head/5.jpg', N'1234', N'20', N'男神', N'摩羯座', N'A', N'汉族', N'上网，打篮球', N'127.0.0.1', N'0', N'离线', N'挣钱')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'30562350', N'小美', N'head/3.jpg', N'1234', N'19', N'男神', N'天蝎座', N'A', N'汉族', N'', N'127.0.0.1', N'0', N'离线', N'相信自己你可以的')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'46072522', N'恨你', N'head/3.jpg', N'12', N'19', N'女神', N'双子座', N'O', N'汉族', N'跑步', N'127.0.0.1', N'0', N'离线', N'我恨你')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'46348781', N'伤你', N'head/3.jpg', N'123', N'20', N'女神', N'双鱼座', N'O', N'汉族', N'逛街，买买买', N'127.0.0.1', N'0', N'离线', N'伤你一万年')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'58719476', N'迷伤你', N'head/5.jpg', N'1234', N'19', N'女神', N'摩羯座', N'A', N'汉族', N'挣钱', N'127.0.0.1', N'0', N'离线', N'挣钱')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'71396743', N'想你', N'head/3.jpg', N'1234', N'19', N'男神', N'水瓶座', N'AB', N'汉族', N'跑步', N'127.0.0.1', N'0', N'离线', N'我爱你')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'73667325', N'sfd', N'head/9.jpg', N'111', N'11', N'男神', N'天蝎座', N'A', N'汉族', N'', N'127.0.0.1', N'0', N'离线', N'相信自己你可以的')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'77787502', N'爱你', N'head/3.jpg', N'1234', N'17', N'女神', N'天蝎座', N'O', N'汉族', N'爬山，跑步', N'127.0.0.1', N'0', N'离线', N'爱你一万年')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'81924298', N'qaws', N'head/3.jpg', N'123', N'1', N'男神', N'天蝎座', N'A', N'汉族', N'12', N'127.0.0.1', N'0', N'离线', N'爱的越深伤的越深')
GO
GO
INSERT INTO [dbo].[Account] ([qqCode], [nickName], [headImg], [password], [age], [sex], [star], [blood], [nation], [hobit], [ipAddr], [port], [onlinestatus], [remark]) VALUES (N'98839152', N'爱上你不是我的错', N'head/3.jpg', N'1111', N'19', N'男神', N'摩羯座', N'O', N'汉族', N'爬山，上网', N'127.0.0.1', N'0', N'离线', N'爱上你不是我的错')
GO
GO

-- ----------------------------
-- Table structure for Friends
-- ----------------------------
DROP TABLE [dbo].[Friends]
GO
CREATE TABLE [dbo].[Friends] (
[friendId] int NOT NULL IDENTITY(1,1) ,
[myQQcode] int NOT NULL ,
[friendQQcode] int NOT NULL ,
[groupName] varchar(20) NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[Friends]', RESEED, 64)
GO

-- ----------------------------
-- Records of Friends
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Friends] ON
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'12', N'1763', N'3374', N'同学')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'15', N'3374', N'1763', N'好友')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'19', N'1763', N'71396743', N'朋友')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'22', N'71396743', N'1763', N'家人')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'23', N'1763', N'7447', N'家人')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'24', N'1763', N'58719476', N'同学')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'25', N'1763', N'77787502', N'黑名单')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'26', N'7447', N'1763', N'家人')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'27', N'58719476', N'1763', N'同学')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'28', N'77787502', N'1763', N'黑名单')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'55', N'3374', N'20108152', N'同学')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'56', N'20108152', N'3374', N'同学')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'57', N'46072522', N'20108152', N'好友')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'58', N'20108152', N'46072522', N'同学')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'59', N'1763', N'46072522', N'好友')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'60', N'46072522', N'1763', N'好友')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'63', N'1763', N'23073484', N'好友')
GO
GO
INSERT INTO [dbo].[Friends] ([friendId], [myQQcode], [friendQQcode], [groupName]) VALUES (N'64', N'23073484', N'1763', N'好友')
GO
GO
SET IDENTITY_INSERT [dbo].[Friends] OFF
GO

-- ----------------------------
-- Table structure for offlineMsg
-- ----------------------------
DROP TABLE [dbo].[offlineMsg]
GO
CREATE TABLE [dbo].[offlineMsg] (
[msgId] int NOT NULL IDENTITY(1,1) ,
[myQQcode] int NOT NULL ,
[friendQQcode] int NOT NULL ,
[cmd] int NOT NULL ,
[msg] text NOT NULL 
)


GO

-- ----------------------------
-- Records of offlineMsg
-- ----------------------------
SET IDENTITY_INSERT [dbo].[offlineMsg] ON
GO
SET IDENTITY_INSERT [dbo].[offlineMsg] OFF
GO

-- ----------------------------
-- Indexes structure for table Account
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Account
-- ----------------------------
ALTER TABLE [dbo].[Account] ADD PRIMARY KEY ([qqCode])
GO

-- ----------------------------
-- Checks structure for table Account
-- ----------------------------
ALTER TABLE [dbo].[Account] ADD CHECK (([port]>=(0) AND [port]<=(65535)))
GO

-- ----------------------------
-- Indexes structure for table Friends
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Friends
-- ----------------------------
ALTER TABLE [dbo].[Friends] ADD PRIMARY KEY ([friendId])
GO

-- ----------------------------
-- Indexes structure for table offlineMsg
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table offlineMsg
-- ----------------------------
ALTER TABLE [dbo].[offlineMsg] ADD PRIMARY KEY ([msgId])
GO
