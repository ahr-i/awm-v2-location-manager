package loggingIPFS

import (
	"log"

	log2 "github.com/ipfs/go-log/v2"
)

func levelSetting(level string) {
	lvl, err := log2.LevelFromString(level)
	if err != nil {
		log.Println(err)
	}

	log2.SetAllLoggers(lvl)
}

func newLogger(system string) *log2.ZapEventLogger {
	logger := log2.Logger(system)

	levelSetting("info")

	return logger
}

func newSystemLogger() *log2.ZapEventLogger {
	return newLogger("system")
}

func newDebugLogger() *log2.ZapEventLogger {
	return newLogger("debug")
}
