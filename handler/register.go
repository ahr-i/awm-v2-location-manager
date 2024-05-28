package handler

import (
	"net/http"

	"github.com/ahr-i/awm-v2-location-manager/src/logging"
)

func (h *Handler) registerHandler(w http.ResponseWriter, r *http.Request) {
	jwt := r.Header.Get("Authorization")
	userId, err := h.Service.Authentication(jwt)
	if err != nil {
		logging.Logger.Warn(err)
		rend.JSON(w, 1000, "'Authentication Service' is not working.")
		return
	}

	if err != nil {
		logging.Logger.Warn(err)
		rend.JSON(w, 1001, "Invalid Body format.")
		return
	}

	err = h.Service.Register(r.Body, userId)
	if err != nil {
		logging.Logger.Warn(err)
		rend.JSON(w, 1002, "Have trouble registering a location.")
		return
	}

	rend.JSON(w, http.StatusOK, "Successful location registration.")
}
