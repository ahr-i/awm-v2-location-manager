package mysql

import (
	"database/sql"
	"fmt"

	"github.com/ahr-i/awm-v2-location-manager/src/logging"
	_ "github.com/go-sql-driver/mysql"
)

func checkTableAndCreate(db *sql.DB) error {
	locationTable := "location"
	locationImageTable := "location_image"
	contributorTable := "contributor"
	userTable := "user_table"

	err := createLocation(db, locationTable)
	if err != nil {
		return err
	}

	err = createLocationImage(db, locationImageTable)
	if err != nil {
		return err
	}

	err = createContributor(db, contributorTable)
	if err != nil {
		return err
	}

	err = createUserTable(db, userTable)
	if err != nil {
		return err
	}

	return nil
}

func createLocation(db *sql.DB, table string) error {
	var result string
	checkQuery := fmt.Sprintf("SHOW TABLES LIKE '%s'", table)
	err := db.QueryRow(checkQuery).Scan(&result)
	if err != nil && err != sql.ErrNoRows {
		return err
	}

	if result == table {
		logging.Logger.Info(table + " is checked.")
	} else {
		query := fmt.Sprintf(`
		CREATE TABLE %s (
			location_id int NOT NULL AUTO_INCREMENT,
			category varchar(255) DEFAULT NULL,
			created_at datetime NOT NULL,
			description varchar(255) DEFAULT NULL,
			latitude double DEFAULT NULL,
			longitude double DEFAULT NULL,
			score int DEFAULT NULL,
			title varchar(255) DEFAULT NULL,
			visit_count int DEFAULT NULL,

			PRIMARY KEY (location_id)
		  ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3
		`, table)

		_, err := db.Exec(query)
		if err != nil {
			return err
		}
	}

	return nil
}

func createLocationImage(db *sql.DB, table string) error {
	var result string
	checkQuery := fmt.Sprintf("SHOW TABLES LIKE '%s'", table)
	err := db.QueryRow(checkQuery).Scan(&result)
	if err != nil && err != sql.ErrNoRows {
		return err
	}

	if result == table {
		logging.Logger.Info(table + " is checked.")
	} else {
		query := fmt.Sprintf(`
		CREATE TABLE %s (
			image_id int NOT NULL AUTO_INCREMENT,
			image longblob,
			location_id int DEFAULT NULL,

			PRIMARY KEY (image_id)
		  ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3
		`, table)

		_, err := db.Exec(query)
		if err != nil {
			return err
		}
	}

	return nil
}

func createContributor(db *sql.DB, table string) error {
	var result string
	checkQuery := fmt.Sprintf("SHOW TABLES LIKE '%s'", table)
	err := db.QueryRow(checkQuery).Scan(&result)
	if err != nil && err != sql.ErrNoRows {
		return err
	}

	if result == table {
		logging.Logger.Info(table + " is checked.")
	} else {
		query := fmt.Sprintf(`
		CREATE TABLE %s (
			contributor_id int NOT NULL AUTO_INCREMENT,
			location_id int DEFAULT NULL,
			rate int DEFAULT NULL,
			user_id varchar(255) DEFAULT NULL,

			PRIMARY KEY (contributor_id)
		  ) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3
		`, table)

		_, err := db.Exec(query)
		if err != nil {
			return err
		}
	}

	return nil
}

func createUserTable(db *sql.DB, table string) error {
	var result string
	checkQuery := fmt.Sprintf("SHOW TABLES LIKE '%s'", table)
	err := db.QueryRow(checkQuery).Scan(&result)
	if err != nil && err != sql.ErrNoRows {
		return err
	}

	if result == table {
		logging.Logger.Info(table + " is checked.")
	} else {
		query := fmt.Sprintf(`
		CREATE TABLE %s (
			id int NOT NULL AUTO_INCREMENT,
			creat_at datetime(6) DEFAULT NULL,
			image longblob,
			image_hash varchar(255) DEFAULT NULL,
			nick_name varchar(255) DEFAULT NULL,
			password varchar(255) DEFAULT NULL,
			phone_number varchar(255) DEFAULT NULL,
			provider varchar(255) DEFAULT NULL,
			rank_score int DEFAULT NULL,
			stat int DEFAULT NULL,
			user_id varchar(255) DEFAULT NULL,

			PRIMARY KEY (id)
		  ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3
		`, table)

		_, err := db.Exec(query)
		if err != nil {
			return err
		}
	}

	return nil
}
