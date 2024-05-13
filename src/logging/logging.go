package logging

import (
	"github.com/ahr-i/awm-v2-location-manager/src/logging/loggingIPFS"
	"github.com/ahr-i/awm-v2-location-manager/src/logging/loggingMonitor"
)

type Logger interface {
	Init(debugMode bool)
	Info(msg interface{})
	Warn(msg interface{})
	Error(msg interface{})
	DebugInfo(msg interface{})
	DebugWarn(msg interface{})
	DebugError(msg interface{})
}

func NewLogger() Logger {
	return loggingIPFS.NewLogger()
}

func NewLoggerMonitor() Logger {
	return loggingMonitor.NewLogger()
}
