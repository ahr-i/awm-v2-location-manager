package monitorCommunicator

import (
	"github.com/ahr-i/awm-v2-location-manager/src/logging/logDefault"
)

func NewMonitor() (*MonitorComm, error) {
	printLog()
	err := testConnection()
	if err != nil {
		return nil, err
	}

	err = register()
	if err != nil {
		return nil, err
	}

	return &MonitorComm{
		Address:     getMonitorListenAddress(),
		ServiceName: getServiceName(),
	}, nil
}

func printLog() {
	logDefault.System("Initialize the monitor connection.")
	logDefault.System("Address: " + getMonitorListenAddress())
	logDefault.System("Service Name: " + getServiceName())
}
