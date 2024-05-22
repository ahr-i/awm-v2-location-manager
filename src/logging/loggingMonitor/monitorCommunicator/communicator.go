package monitorCommunicator

import (
	"bytes"
	"encoding/json"
	"errors"
	"fmt"
	"net"
	"net/http"

	"github.com/ahr-i/awm-v2-location-manager/setting"
	"github.com/ahr-i/awm-v2-location-manager/src/logging/logDefault"
)

func (m *MonitorComm) SendUDPMessage(system string, msg string) error {
	if m == nil {
		return nil
	}

	senderConn, err := net.Dial("udp", m.Address)
	if err != nil {
		return err
	}
	defer senderConn.Close()

	msg = fmt.Sprintf("%s %s", system, msg)
	_, err = senderConn.Write([]byte(msg))
	if err != nil {
		return err
	}

	return nil
}

func register() error {
	url := fmt.Sprintf("http://%s/service/register", getMonitorServerAddress())
	jsonData, err := json.Marshal(registerRequest{
		ServiceName: getServiceName(),
		Port:        setting.Setting.ServerPort,
	})
	if err != nil {
		return err
	}

	resp, err := http.Post(url, "application/json", bytes.NewBuffer(jsonData))
	if err != nil {
		return err
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		msg := fmt.Sprintf("The response status of monitor-server is not 200. (response: %d)", resp.StatusCode)

		return errors.New(msg)
	}

	return nil
}

func testConnection() error {
	address := getMonitorServerAddress()
	address = fmt.Sprintf("http://%s/ping", address)
	resp, err := http.Get(address)
	if err != nil {
		return err
	}

	if resp.StatusCode != http.StatusOK {
		msg := fmt.Sprintf("The response status of monitor-server is not 200. (response: %d)", resp.StatusCode)

		return errors.New(msg)
	}
	logDefault.System("Received normal response from monitor-server.")

	return nil
}
