package monitorCommunicator

import (
	"fmt"

	"github.com/ahr-i/awm-v2-location-manager/setting"
)

func getMonitorListenAddress() string {
	address := fmt.Sprintf("%s:%s", setting.Setting.Monitor.IP, setting.Setting.Monitor.ListenPort)

	return address
}

func getMonitorServerAddress() string {
	address := fmt.Sprintf("%s:%s", setting.Setting.Monitor.IP, setting.Setting.Monitor.ServerPort)

	return address
}

func getServiceName() string {
	return setting.Setting.ServiceName
}
