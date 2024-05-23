package monitorCommunicator

type MonitorComm struct {
	Address     string
	ServiceName string
}

type registerRequest struct {
	ServiceName string `json:"service_name"`
	Port        string `json:"port"`
}

type sendFormat struct {
	ServiceName string `json:"service_name"`
	Message     string `json:"message"`
}
