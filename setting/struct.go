package setting

type settingStruct struct {
	DebugMode bool `json:"debug_mode"`
}

var Setting settingStruct
