package corsController

import "github.com/rs/cors"

func SetCors(origins string, methods string, headers string, credentials bool) *cors.Cors {
	return cors.New(cors.Options{
		AllowedOrigins:   []string{origins},
		AllowedMethods:   []string{methods},
		AllowedHeaders:   []string{headers},
		AllowCredentials: credentials,
	})
}
