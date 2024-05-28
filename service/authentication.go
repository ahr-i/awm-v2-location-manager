package service

import (
	"encoding/json"
	"fmt"
	"net/http"

	"github.com/ahr-i/awm-v2-location-manager/setting"
)

func (s *Service) Authentication(jwt string) (int, error) {
	url := fmt.Sprintf("http://%s/authentication/get-id", setting.Setting.Service.Authentication)

	client := &http.Client{}
	req, err := http.NewRequest("POST", url, nil)
	if err != nil {
		return -1, err
	}
	req.Header.Set("Authorization", jwt)

	resp, err := client.Do(req)
	if err != nil {
		return -1, err
	}

	var response authenticationGetId
	decoder := json.NewDecoder(resp.Body)
	err = decoder.Decode(&response)
	if err != nil {
		return -1, err
	}

	return response.UserId, nil
}
