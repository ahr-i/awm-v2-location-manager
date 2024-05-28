package service

import "github.com/ahr-i/awm-v2-location-manager/database"

func CreateServiceHandler() *Service {
	return &Service{DB: database.CreateDatabase()}
}
