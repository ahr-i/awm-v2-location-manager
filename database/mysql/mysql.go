package mysql

import (
	"database/sql"
	"fmt"
	"os"

	"github.com/ahr-i/awm-v2-location-manager/setting"
	"github.com/ahr-i/awm-v2-location-manager/src/logging"
)

func CreateMySQLHandler() *MysqlHandler {
	url := fmt.Sprintf("%s:%s@tcp(%s)/%s", setting.Setting.Database.User, setting.Setting.Database.Password, setting.Setting.Database.Address, setting.Setting.Database.Schema)
	db, err := sql.Open("mysql", url)
	if err != nil {
		logging.Logger.Error("Unable to connect to the database.")
		logging.Logger.Error("URL: " + url)
		logging.Logger.Error(err)
		os.Exit(1)
	}

	err = db.Ping()
	if err != nil {
		logging.Logger.Error(err)
		os.Exit(1)
	}
	logging.Logger.Info("Successfully connected to MySQL database.")

	err = checkTableAndCreate(db)
	if err != nil {
		logging.Logger.Error(err)
		os.Exit(1)
	}

	return &MysqlHandler{DB: db}
}
