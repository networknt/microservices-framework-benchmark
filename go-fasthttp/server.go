package main

import (
    "github.com/valyala/fasthttp"
    "github.com/valyala/fasthttp/reuseport"
    "net"
    "flag"
    "runtime"
    "log"
    "os/exec"
    "os"
)

var (
    listenAddr = flag.String("listenAddr", ":8080", "Address to listen to")
    prefork    = flag.Bool("prefork", false, "use prefork")
    child      = flag.Bool("child", false, "is child proc")
)

func main() {
    flag.Parse()

    var err error

    s := &fasthttp.Server{
        Handler: mainHandler,
        Name:    "go",
    }
    ln := getListener()
    if err = s.Serve(ln); err != nil {
        log.Fatalf("Error when serving incoming connections: %s", err)
    }

}

func mainHandler(ctx *fasthttp.RequestCtx) {
    path := ctx.Path()
    switch string(path) {
    case "/":
        plaintextHandler(ctx)
    default:
        ctx.Error("unexpected path", fasthttp.StatusBadRequest)
    }
}

func plaintextHandler(ctx *fasthttp.RequestCtx) {
    ctx.SetContentType("text/plain")
    ctx.WriteString("Hello World!")
}

func getListener() net.Listener {
    if !*prefork {
        runtime.GOMAXPROCS(runtime.NumCPU())
        ln, err := net.Listen("tcp4", *listenAddr)
        if err != nil {
            log.Fatal(err)
        }
        return ln
    }

    if !*child {
        children := make([]*exec.Cmd, runtime.NumCPU())
        for i := range children {
            children[i] = exec.Command(os.Args[0], "-prefork", "-child")
            children[i].Stdout = os.Stdout
            children[i].Stderr = os.Stderr
            if err := children[i].Start(); err != nil {
                log.Fatal(err)
            }
        }
        for _, ch := range children {
            if err := ch.Wait(); err != nil {
                log.Print(err)
            }
        }
        os.Exit(0)
        panic("unreachable")
    }

    runtime.GOMAXPROCS(1)
    ln, err := reuseport.Listen("tcp4", *listenAddr)
    if err != nil {
        log.Fatal(err)
    }
    return ln
}