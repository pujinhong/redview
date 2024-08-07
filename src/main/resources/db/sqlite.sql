

CREATE TABLE "t_datasource" (
	"id" INTEGER NOT NULL,
	"code" TEXT NULL,
	"name" TEXT NULL,
	"remark" TEXT NULL,
	"db_type" TEXT NULL,
	"db_driver" TEXT NULL,
	"db_url" TEXT NULL,
	"db_instance" TEXT NULL,
	"db_username" TEXT NULL,
	"db_password" TEXT NULL,
	"db_port" INTEGER NULL,
	"db_host" TEXT NULL,
	"del_flag" INTEGER NULL,
	"online" INTEGER NULL,
	"create_time" TEXT NULL,
	"create_by" TEXT NULL,
	"update_time" TEXT NULL,
	"update_by" TEXT NULL,
	PRIMARY KEY ("id"),
)
;

CREATE TABLE "t_bento_sql" (
	"id" INTEGER NOT NULL,
	"name" TEXT NULL,
	"title" TEXT NULL,
	"db_code" TEXT NULL,
	"statement" TEXT NULL,
	"create_by" TEXT NULL,
	"create_time" TEXT NULL,
	"update_by" TEXT NULL,
	"update_time" TEXT NULL,
	PRIMARY KEY ("id")
)
;
CREATE TABLE "t_bento_json" (
	"id" INTEGER NOT NULL,
	"name" TEXT NULL,
	"title" TEXT NULL,
	"json" TEXT NULL,
	"create_by" TEXT NULL,
	"create_time" TEXT NULL,
	"update_by" TEXT NULL,
	"update_time" TEXT NULL,
	PRIMARY KEY ("id")
)
;

