package main

import (
    "io"
    "github.com/julienschmidt/httprouter"
    "net/http"
)

func Hello(w http.ResponseWriter, r *http.Request, ps httprouter.Params) {
  io.WriteString(w, "Hello World!")
}

func main() {
  router := httprouter.New()
  router.GET("/", Hello)

  http.ListenAndServe(":8080", router)
}
