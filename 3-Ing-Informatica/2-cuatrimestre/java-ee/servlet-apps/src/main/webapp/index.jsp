<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles.css">
        <title>Servlet - Apps</title>
    </head>
    <body>
        <div class="servlet-container">
            <div class="app-container">
                <h1>Multiuser Chat</h1>
                <p>Please, before entering the chat, tell us your username.</p>
                <form method="post" action="chat">
                    <div class="form-field">
                        <p>Username:</p>
                        <label>
                            <input type="text" name="username" required>
                        </label>
                    </div>
                    <button type="submit">Enter chat</button>
                </form>
            </div>
            <div class="app-container">
                <h1>Area of the polygon</h1>
                <p>
                    Input the coordinates of a polygon and the servlet will calculate its area.
                    Please, follow the following format: x1,y1;x2,y2;...
                </p>
                <form method="post" action="polygon">
                    <div class="form-field">
                        <p>Coordinates:</p>
                        <label>
                            <input type="text" name="coordinates" required>
                        </label>
                    </div>
                    <button type="submit">Calculate area</button>
                </form>
            </div>
        </div>
    </body>
</html>