package monitorCommunicator

import "log"

func NewMonitor() *MonitorComm {
	address := getMonitorAddress()
	serviceName := getServiceName()

	printLog(address, serviceName)

	return &MonitorComm{
		Address:     address,
		ServiceName: serviceName,
	}
}

func printLog(address string, serviceName string) {
	log.Println("* (SYSTEM) Initialize the monitor connection.")
	log.Printf("* (SYSTEM) Address: %s\n", address)
	log.Printf("* (SYSTEM) Address: %s\n", serviceName)
}
