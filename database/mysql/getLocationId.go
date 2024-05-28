package mysql

import (
	"github.com/ahr-i/awm-v2-location-manager/setting"
	_ "github.com/go-sql-driver/mysql"
	"golang.org/x/perf/storage/db"
)

func (mh *MysqlHandler) GetLocationId(latitude float32, longitude float32, category string) error {
	latitudeRange := setting.Setting.Default.LatitudeRange
	longitudeRange := calculateLongitudeRange(latitude)

	query := `
	SELECT id, latitude, longitude, category, score
	FROM locations
	WHERE latitude BETWEEN ? AND ?
	AND longitude BETWEEN ? AND ?
	AND category = ?`

	rows, err := db.Query(query,
		latitude-latitudeRange, latitude+latitudeRange,
		longitude-longitudeRange, longitude+longitudeRange,
		category,
	)
	if err != nil {
		return err
	}
	defer rows.Close()
}
