package mysql

import (
	"math"

	"github.com/ahr-i/awm-v2-location-manager/setting"
)

func calculateLongitudeRange(latitude float32) float32 {
	return float32(math.Cos(float64(latitude)*math.Pi/180) * float64(setting.Setting.Default.LatitudeRange))
}
