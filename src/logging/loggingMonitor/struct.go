package loggingMonitor

import (
	log2 "github.com/ipfs/go-log/v2"
)

type Logger struct {
	System      *log2.ZapEventLogger
	Debug       *log2.ZapEventLogger
	MonitorComm MonitorComm
}

type MonitorComm interface {
	SendUDPMessage(system string, msg string) error
}
