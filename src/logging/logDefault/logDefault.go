package logDefault

import (
	"fmt"
	"log"
)

func System(msg interface{}) {
	Custom("(SYSTEM)", msg)
}

func Warn(msg interface{}) {
	Custom("(WARRING)", msg)
}

func Error(msg interface{}) {
	Custom("(ERROR)", msg)
}
func Custom(system string, msg interface{}) {
	msg = fmt.Sprintf("* %s %s", system, formatMessage(msg))

	log.Println(msg)
}

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
