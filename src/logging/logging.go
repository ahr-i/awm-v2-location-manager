package logging

import (
	"github.com/ahr-i/awm-v2-location-manager/src/logging/loggingIPFS"
	"github.com/ahr-i/awm-v2-location-manager/src/logging/loggingMonitor"
)

func Init(debugMode bool) {
	//Logger = newLogger()
	Logger = newLoggerMonitor()

	Logger.Init(debugMode)
}

func newLogger() Logging {
	return loggingIPFS.NewLogger()
}

func newLoggerMonitor() Logging {
	return loggingMonitor.NewLogger()
}
