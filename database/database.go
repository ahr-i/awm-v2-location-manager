package database

import "github.com/ahr-i/awm-v2-location-manager/database/mysql"

func CreateDatabase() DBHandler {
	return mysql.CreateMySQLHandler()
}
