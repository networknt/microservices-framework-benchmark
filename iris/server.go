package main

import ("github.com/kataras/iris")

func main() {
	iris.Config.Charset = "UTF-8" // this is the default, you don't have to set it manually

	iris.Get("/", func(ctx *iris.Context) {
		ctx.Text(iris.StatusOK, "Hello World!")
	})
	iris.Listen(":8080")
}
