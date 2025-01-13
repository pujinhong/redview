
-- 数据库配置
CREATE TABLE "t_datasource" (
	"id" INTEGER NOT NULL,
	"code" TEXT NULL,
	"name" TEXT NULL,
	"remark" TEXT NULL,
	"db_type" TEXT NULL,
	"db_driver" TEXT NULL,
	"db_url" TEXT NULL,
	"db_username" TEXT NULL,
	"db_password" TEXT NULL,
	"db_host" TEXT NULL,
	"db_port" INTEGER NULL,
	"db_instance" TEXT NULL,
	"db_ssl" INTEGER NULL,
	"db_ssl_key" TEXT NULL,
	"del_flag" INTEGER NULL,
	"online" INTEGER NULL,
	"create_time" datetime NULL,  
	"update_time" datetime NULL,
	PRIMARY KEY ("id")
);
-- 视图SQL
CREATE TABLE "t_bento_sql" (
	"id" INTEGER NOT NULL,
	"name" TEXT NULL,
	"title" TEXT NULL,
	"db_code" TEXT NULL,
	"statement" TEXT NULL,
	"is_cache" INTEGER NULL,
  	"cache_type" TEXT NULL,
  	"cache_expire" INTEGER NULL, 
  	"create_time" datetime NULL,
	"update_time" datetime NULL,
	PRIMARY KEY ("id")
);
-- 视图JSON
CREATE TABLE "t_bento_json" (
	"id" INTEGER NOT NULL,
	"name" TEXT NULL,
	"title" TEXT NULL,
	"json" TEXT NULL,
	"create_time" datetime NULL,
	"update_time" datetime NULL,
	PRIMARY KEY ("id")
);
-- 视图组合
CREATE TABLE `t_bento_group` (
  "id" int(11) NOT NULL,
  "name" varchar(50) DEFAULT NULL,
  "title" varchar(50) DEFAULT NULL,
  "statement" TEXT NULL,
  "bentos" TEXT NULL,
  "create_time" datetime DEFAULT NULL,
  "update_time" datetime DEFAULT NULL,
  PRIMARY KEY ("id")
)

