package mysql

import (
	"database/sql"
)

type MysqlHandler struct {
	DB *sql.DB
}
