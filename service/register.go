package service

import (
	"encoding/json"
	"io"
)

func (s *Service) Register(body io.ReadCloser, userId int) error {
	defer body.Close()
	
	var request registerRequest
	decoder := json.NewDecoder(body)
	err := decoder.Decode(&request)
	if err != nil {
		return err
	}

	existLocationId := s.DB.

	return nil
}