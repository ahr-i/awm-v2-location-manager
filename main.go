package main

import (
	"github.com/ahr-i/awm-v2-location-manager/src/logging"
)

func Init() {
	//logger := logging.NewLogger()
	logger := logging.NewLoggerMonitor()
	logger.Init(true)
	logger.Info("Test1")
	logger.Info("Test2")
}

func main() {
	Init()
}
