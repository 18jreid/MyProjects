import mimetypes
import pathlib
import time

from http.server import HTTPServer, BaseHTTPRequestHandler


class CS2610Assn1(BaseHTTPRequestHandler):
    def do_GET(self):
        resource = pathlib.Path(self.path[1:])

        if self.path == "/" or self.path == "/index":
            self.wfile.write(b"HTTP/1.1 301 Moved Permanently\n")
            print("HTTP/1.1 301 Moved Permanently")

            self.getHeaders()

            self.wfile.write(b"Location: http://10.0.0.202:8000/index.html\n\n")
            print("Location: http://10.0.0.202:8000/index.html")
        elif resource.is_file():
            self.wfile.write(b"HTTP/1.1 200 OK\n")
            print("HTTP/1.1 200 OK")

            self.getHeaders()
            self.getResource(self.path[1:])
        elif self.path.__contains__("/bio"):
            self.wfile.write(b"HTTP/1.1 301 Moved Permanently\n")
            print("HTTP/1.1 301 Moved Permanently")

            self.redirectToAbout()
        elif self.path.__contains__("about"):
            self.wfile.write(b"HTTP/1.1 301 Moved Permanently\n")
            print("HTTP/1.1 301 Moved Permanently")

            self.redirectToAbout()
        elif self.path == "/debugging":
            self.getDebugging()
        elif self.path == "/teapot":
            self.getTeapot()
        elif self.path == "/classified":
            self.getClassified()
        else:
            if self.path.__contains__("techtips"):
                self.wfile.write(b"HTTP/1.1 301 Moved Permanently\n")
                print("HTTP/1.1 301 Moved Permanently")

                self.getHeaders()

                self.wfile.write(b"Location: http://10.0.0.202:8000/techtips+css.html\n\n")
                print("Location: http://10.0.0.202:8000/techtips+css.html")
            else:
                self.getError404()
        print("\n-------------------------------------\n")

    def getClassified(self):
        self.wfile.write(b"HTTP/1.1 403 Forbidden\n")
        print("HTTP/1.1 403 Forbidden")

        self.getHeaders()

        self.wfile.write(bytes(f"Content-Type: {mimetypes.guess_type(self.path[1:])}\n\n", encoding="utf-8"))
        print(f"Content-Type: {mimetypes.guess_type(self.path)}")

        self.wfile.write(bytes(f"""<!DOCTYPE html>
                            <html lang="en">
                                <head>
                                    <meta charset="utf-8"/>
                                    <title>Teapot</title>
                                </head>
                                <body>
                                    <h1>Turn back now!</h1>
                                    <p>This is a restricted area, you do not have access to enter. Turn back now or else!</p>
                                    <p>Go back <a href="index.html">Home</a></p>
                                </body>
                            </html>\n""", encoding="utf-8"))

    def getTeapot(self):
        self.wfile.write(b"HTTP/1.1 418 I'm a teapot\n")
        print("HTTP/1.1 418 I'm a teapot")

        self.getHeaders()
        self.wfile.write(b"\n")

        self.wfile.write(bytes(f"""<!DOCTYPE html>
                            <html lang="en">
                                <head>
                                    <meta charset="utf-8"/>
                                    <title>Teapot</title>
                                </head>
                                <body>
                                    <p>
                                        <a href= "index.html">Blog</a>
                                        <a href= "about.html">About</a>
                                        <a href= "techtips+css.html">Tech Tips</a>
                                        <a href= "http://10.0.0.202:8000/debugging">Debugging</a>
                                        <a href= "http://10.0.0.202:8000/teapot">Teapot</a>
                                        <a href= "http://10.0.0.202:8000/classified">Authorized</a>
                                    </p>
                                    <h1>I'm a teapot!</h1>
                                    <p>Go back <a href="index.html">Home</a></p>
                                </body>
                            </html>\n""", encoding="utf-8"))

    def getDebugging(self):
        self.wfile.write(b"HTTP/1.1 200 OK\n")
        print("HTTP/1.1 200 OK")

        self.getHeaders()
        self.wfile.write(b"\n")

        self.wfile.write(bytes(f"""<!DOCTYPE html>
                            <html lang="en">
                                <head>
                                    <meta charset="utf-8"/>
                                    <title>Debugging</title>
                                </head>
                                <body>
                                    <h1>SERVER DEBUGGING PAGE</h1>
                                    <h2>You got what you asked for</h2>
                                    <p>You are visiting the {self.path} page from <strong>
                                    {self.client_address[0]}</strong> port number <strong>
                                    {self.client_address[1]}</strong></p>
                                    <p>It is now {time.strftime('%c')}</p>
                                    <ul>
                                        <li><strong>Command:</strong> {self.command}</li>
                                        <li><strong>Path:</strong> {self.path}</li>
                                        <li><strong>Request Version:</strong> {self.request_version}</li>
                                        <li><strong>Version String:</strong> {self.version_string()}</li>
                                    </ul>
                                    <h3>Request Headers</h3>
                                    <ol>
                                        <li>Host: {self.headers['Host']}</li>
                                        <li>User-Agent: {self.headers['User-Agent']}</li>
                                        <li>Accept: {self.headers['Accept']}</li>
                                        <li>Accept-Language: {self.headers['Accept-Language']}</li>
                                        <li>Accept-Encoding: {self.headers['Accept-Encoding']}</li>
                                        <li>DNT: {self.headers['DNT']}</li>
                                        <li>Connection: {self.headers['Connection']}</li>
                                        <li>Referrer: {self.headers['Referrer']}</li>
                                        <li>Upgrade-Insecure-Requests: {self.headers['Upgrade-Insecure-Requests']}</li>
                                    </ol>
                                    <p>Go back <a href="index.html">Home</a></p>
                                </body>
                            </html>\n""", encoding="utf-8"))

    def redirectToAbout(self):
        self.getHeaders()

        self.wfile.write(b"Location: http://10.0.0.202:8000/about.html\n")
        print("Location: http://10.0.0.202:8000/about.html")

        self.getResource("about.html")

    def getError404(self):
        self.wfile.write(b"HTTP/1.1 404 Not Found\n\n")
        print("HTTP/1.1 404 Not Found")

        self.wfile.write(bytes(f"""<!DOCTYPE html>
                    <html lang="en">
                        <head>
                            <meta charset="utf-8"/>
                            <title>{self.path}404 Not Found</title>
                        </head>
                        <body>
                            <h1>Error, your resource was not found.</h1>
                            <p>{self.path} does not exist :(</p>
                            <p>Click here to go back to the <a href="index.html">Home</a> page!</p>
                        </body>
                    </html>""", encoding="utf-8"))

    def getResource(self, path):
        f = open(path, "rb")
        data = f.read()
        f.close()

        self.wfile.write(bytes(f"Content-Length: {len(data)}\n", encoding="utf-8"))
        print(f"Content-Length: {len(data)}")

        self.wfile.write(b"\n")
        self.wfile.write(data)

    def getHeaders(self):
        self.wfile.write(b"Server: Justin's Awesome Server\n")
        print("Server: Justin's Awesome Server")

        self.wfile.write(b"Connection: close\n")
        print("Connection: close")

        self.wfile.write(bytes(f"Date: {time.strftime('%c')}\n", encoding="utf-8"))
        print(f"Date: {time.strftime('%c')}")

        self.wfile.write(b"Cache-Control: max-age=10\n")
        print("Cache-Control: max-age=3")

        self.getContentType()

    def getContentType(self):
        resource = self.path[1:]
        if resource.__contains__(".html"):
            self.wfile.write(b"Content-type: text/html\n")
            print("Content-type: text/html")
        elif resource.__contains__(".css"):
            self.wfile.write(b"Content-type: text/css\n")
            print("Content-type: text/css")
        elif resource.__contains__(".jpeg"):
            self.wfile.write(b"Content-type: image/jpeg\n")
            print("Content-type: image/jpeg")
        elif resource.__contains__(".jpg"):
            self.wfile.write(b"Content-type: image/jpg\n")
            print("Content-type: image/jpg")
        elif resource.__contains__(".JPG"):
            self.wfile.write(b"Content-type: image/JPG\n")
            print("Content-type: image/JPG")
        elif resource.__contains__(".mov"):
            self.wfile.write(b"Content-type: video/mov\n")
            print("Content-type: video/mov")
        elif resource.__contains__(".ico"):
            self.wfile.write(b"Content-type: image/x-icon\n")
            print("Content-type: image/x-icon")


if __name__ == '__main__':
    server_address = ('10.0.0.202', 8000, )
    print(f"Serving from http://{server_address[0]}:{server_address[1]}")
    print("Press Ctrl-C to quit\n")
    try:
        HTTPServer(server_address, CS2610Assn1).serve_forever()
    except KeyboardInterrupt:
        print(" Exiting")
        exit(0)
