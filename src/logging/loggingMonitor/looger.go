package loggingMonitor

import (
	"fmt"

	"github.com/ahr-i/awm-v2-location-manager/setting"
	"github.com/ahr-i/awm-v2-location-manager/src/logging/logDefault"
)

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

func controlError(err error) {
	if setting.Setting.DebugMode && err != nil {
		logDefault.Warn(err)
	}
}

func (l *Logger) Info(msg interface{}) {
	msg_ := formatMessage(msg)

	l.System.Info(msg_)
	err := l.MonitorComm.SendUDPMessage("(INFO)", msg_)
	controlError(err)
}

func (l *Logger) Warn(msg interface{}) {
	msg_ := formatMessage(msg)

	l.System.Warn(msg_)
	err := l.MonitorComm.SendUDPMessage("(WARN)", msg_)
	controlError(err)
}

func (l *Logger) Error(msg interface{}) {
	msg_ := formatMessage(msg)

	l.System.Error(msg_)
	err := l.MonitorComm.SendUDPMessage("(ERROR)", msg_)
	controlError(err)
}

func (l *Logger) DebugInfo(msg interface{}) {
	msg_ := formatMessage(msg)

	l.Debug.Info(msg_)
	err := l.MonitorComm.SendUDPMessage("(INFO)", msg_)
	controlError(err)
}

func (l *Logger) DebugWarn(msg interface{}) {
	msg_ := formatMessage(msg)

	l.Debug.Warn(msg_)
	err := l.MonitorComm.SendUDPMessage("(WARN)", msg_)
	controlError(err)
}

func (l *Logger) DebugError(msg interface{}) {
	msg_ := formatMessage(msg)

	l.Debug.Error(msg_)
	err := l.MonitorComm.SendUDPMessage("(ERROR)", msg_)
	controlError(err)
}
