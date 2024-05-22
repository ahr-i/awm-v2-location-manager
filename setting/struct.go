package setting

type settingStruct struct {
	DebugMode   bool   `json:"debug_mode"`
	ServerPort  string `json:"server_port"`
	ServiceName string `json:"service_name"`
	Monitor     struct {
		IP         string `json:"ip"`
		ListenPort string `json:"listen_port"`
		ServerPort string `json:"server_port"`
	} `json:"monitor"`
}

var Setting settingStruct
