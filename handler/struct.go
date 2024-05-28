package handler

import (
	"net/http"

	"github.com/ahr-i/awm-v2-location-manager/service"
	"github.com/unrolled/render"
)

var rend *render.Render = render.New()

type Handler struct {
	http.Handler
	*service.Service
}
