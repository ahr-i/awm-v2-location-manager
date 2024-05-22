package logging

var Logger Logging

type Logging interface {
	Init(debugMode bool)
	Info(msg interface{})
	Warn(msg interface{})
	Error(msg interface{})
	DebugInfo(msg interface{})
	DebugWarn(msg interface{})
	DebugError(msg interface{})
}
