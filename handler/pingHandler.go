package handler

import "net/http"

func (h *Handler) pingHandler(w http.ResponseWriter, r *http.Request) {
	rend.JSON(w, http.StatusOK, nil)
}
