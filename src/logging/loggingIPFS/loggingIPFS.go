package loggingIPFS

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
}
