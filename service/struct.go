package service

import "github.com/ahr-i/awm-v2-location-manager/database"

type Service struct {
	DB database.DBHandler
}

type authenticationGetId struct {
	UserId int `json:"userId"`
}

type registerRequest struct {
	Latitude    float32 `json:"latitude"`
	Longitude   float32 `json:"longitude"`
	Category    string  `json:"category"`
	Title       string  `json:"title"`
	Description string  `json:"description"`
	Image       string  `json:"image"`
}
