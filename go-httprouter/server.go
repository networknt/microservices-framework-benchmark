package main

import (
    "fmt"
    "github.com/julienschmidt/httprouter"
    "net/http"
)

func Hello(w http.ResponseWriter, r *http.Request, ps httprouter.Params) {
    fmt.Println("Hello World!")
}

func main() {
    router := httprouter.New()
    router.GET("/", Hello)

  http.ListenAndServe(":8080", router)
}
