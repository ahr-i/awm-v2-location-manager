package handler

import (
	"github.com/ahr-i/awm-v2-location-manager/service"
	"github.com/gorilla/mux"
)

func CreateHandler() *Handler {
	mux := mux.NewRouter()
	handler := &Handler{
		Handler: mux,
		Service: service.CreateServiceHandler(),
	}

	mux.HandleFunc("/ping", handler.pingHandler).Methods("GET")
	mux.HandleFunc("/user/location/register", handler.registerHandler).Methods("POST")

	return handler
}
