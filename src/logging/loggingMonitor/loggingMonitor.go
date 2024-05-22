package loggingMonitor

import (
	"github.com/ahr-i/awm-v2-location-manager/src/logging/logDefault"
	"github.com/ahr-i/awm-v2-location-manager/src/logging/loggingMonitor/monitorCommunicator"
)

func NewLogger() *Logger {
	system := newSystemLogger()
	debug := newDebugLogger()

	return &Logger{
		System: system,
		Debug:  debug,
	}
}

func (l *Logger) Init(debugMode bool) {
	systemLogger := newSystemLogger()

	systemLogger.Info("Successfully initialized the 'logging-system' package.")
	if debugMode {
		debugLogger := newDebugLogger()
		debugLogger.Info("Successfully initialized the 'logging-debug' package.")
	}

	var err error
	l.MonitorComm, err = monitorCommunicator.NewMonitor()
	if err != nil {
		logDefault.Warn(err)
		logDefault.Warn("The monitor-server connection failed.")
	} else {
		systemLogger.Info("The monitor connection was successful.")
	}
}
