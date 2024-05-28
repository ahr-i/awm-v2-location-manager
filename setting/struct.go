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
	Database struct {
		Address  string `json:"address"`
		Schema   string `json:"schema"`
		User     string `json:"user"`
		Password string `json:"password"`
	} `json:"database"`
	Service struct {
		Authentication string `json:"authentication"`
	} `json:"service"`
	Default struct {
		UserRate          int     `json:"user_rate"`
		LatitudeRange     float32 `json:"latitude_range"`
		Threshold         int     `json:"threshold"`
		RegisterBaseScore int     `json:"register_base_score"`
		DeleteBaseScore   int     `json:"delete_base_score"`
	} `json:"default"`
}

var Setting settingStruct
