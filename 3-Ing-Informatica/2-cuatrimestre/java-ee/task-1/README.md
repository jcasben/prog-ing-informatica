# Task 1 - Chat using Java.IO

In this task we had to implement a Chat base in server/client architecture. The server of 
the chat had to be multithreaded so each thread handles one client connection. The client has
a UI created with Java FX

This had to be implemented with the Socket and ServerSocket class from the library Java IO.

This chat is configured to work only in the localhost of the computer, so to try it you will
have to run the server and client/s in several different terminal instances.

## How to run the program?

First, you need to install the Java Fx library and put it wherever you want in your computer.
If you don't have Java Fx, the client won't compile, but the server will still work.

### Compile and Run server

Run the following command to compile the server:

```bash
javac -d out src/server/ChatServer.java
```

and this command for running it:

```bash
java -cp out src.server.ChatServer
```

### Compile and Run client

To compile the client, run the following command:

```bash
javac -d out --module-path "<absolute-path-to-javafx-lib>" --add-modules javafx.controls,javafx.fxml src/client/ChatClient.java
```

and this command for running it:

```bash
java -cp out --module-path "<absolute-path-to-javafx-lib>" --add-modules javafx.controls,javafx.fxml src.client.ChatClient
```

This commands will output all the binaries to an /out folder, so it keeps the other directories
clean and tidy.

> [!IMPORTANT]
> It's important to have first the server running and then start the client. If you start
> the client first, it won't find the server running and won't be able to connect to it.
