package loggingIPFS

import "fmt"

func formatMessage(msg interface{}) string {
	switch v := msg.(type) {
	case string:
		return v
	case error:
		return v.Error()
	default:
		return fmt.Sprintf("%v", v)
	}
}

func (l *Logger) Info(msg interface{}) {
	l.System.Info(formatMessage(msg))
}

func (l *Logger) Warn(msg interface{}) {
	l.System.Warn(formatMessage(msg))
}

func (l *Logger) Error(msg interface{}) {
	l.System.Error(formatMessage(msg))
}

func (l *Logger) DebugInfo(msg interface{}) {
	l.Debug.Info(formatMessage(msg))
}

func (l *Logger) DebugWarn(msg interface{}) {
	l.Debug.Warn(formatMessage(msg))
}

func (l *Logger) DebugError(msg interface{}) {
	l.Debug.Error(formatMessage(msg))
}
