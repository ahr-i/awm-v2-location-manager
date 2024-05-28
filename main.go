package main

import (
	"net/http"

	"github.com/ahr-i/awm-v2-location-manager/handler"
	"github.com/ahr-i/awm-v2-location-manager/setting"
	"github.com/ahr-i/awm-v2-location-manager/src/corsController"
	"github.com/ahr-i/awm-v2-location-manager/src/logging"
	"github.com/urfave/negroni"
)

func initialization() {
	setting.Init()
	logging.Init(setting.Setting.DebugMode)
}

func startServer() {
	mux := handler.CreateHandler()
	handler := negroni.Classic()
	defer mux.Close()

	handler.Use(corsController.SetCors("*", "GET, POST, PUT, DELETE", "*", true))
	handler.UseHandler(mux)

	logging.Logger.Info("HTTP server start.")
	http.ListenAndServe(":"+setting.Setting.ServerPort, handler)
}

func main() {
	initialization()
	startServer()
}
