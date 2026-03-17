<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Login</title>

                <link rel="stylesheet" href="/css/client/auth/css/style.css">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
            </head>

            <body>

                <div class="Sologan">
                    <a href="/client/homepage">
                        <div class="title">English Learning Platform</div>
                    </a>
                </div>

                <div class="login">
                    <div class="main">

                        <div class="title">Login</div>

                        <!-- Spring Form -->
                        <form:form method="POST" action="/login" modelAttribute="loginUser">

                            <div class="field">
                                <label>Email</label>
                                <form:input path="email" type="email" placeholder="abc@gmail.com" cssClass="input" />
                            </div>

                            <div class="field">
                                <label>Password</label>
                                <form:password path="passWord" placeholder="1234.." cssClass="input" />
                            </div>

                            <button type="submit" class="submit">
                                Login
                            </button>

                        </form:form>

                        <div class="new_user">
                            New user? <a href="/client/sign_up">Sign up</a>
                        </div>

                    </div>
                </div>

            </body>

            </html>