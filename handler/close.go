package handler

func (h *Handler) Close() {
	h.Service.DB.Close()
}
