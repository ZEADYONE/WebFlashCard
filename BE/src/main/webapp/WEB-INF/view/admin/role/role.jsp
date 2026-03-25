<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


            <!DOCTYPE html>
            <html lang="vi">

            <head>
                <meta charset="UTF-8">
                <title>Role Management</title>
                <link rel="stylesheet" href="/css/admin/deck.css">
                <link rel="stylesheet" href="/css/admin/header-slide.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
            </head>

            <body>

                <header class="top-nav">
                    <div class="brand">
                        <h1>English Learning Platform</h1>
                        <p>Master English with Interactive Exercises</p>
                    </div>
                    <div class="container-info" id="userDropdownTrigger">
                        <i class="fa-regular fa-user"></i>
                        <span class="user-name">
                            <c:out value="${sessionScope.fullName}" />
                        </span>
                        <i class="fa-solid fa-chevron-down mini-arrow"></i>

                        <div class="info-dropdown" id="infoDropdown">

                            <a href="#" class="dropdown-item">
                                <i class="fa-solid fa-circle-info"></i>
                                <span>Information</span>
                            </a>
                            <c:if test="${sessionScope.role == 'ADMIN'}">
                                <a href="/" class="dropdown-item">
                                    <i class="fa-regular fa-user"></i>
                                    <span>Client</span>
                                </a>
                            </c:if>

                            <form method="post" action="/logout">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                                <button type="submit" class="dropdown-item"
                                    style="width: 100%; border: 0px none; background-color: white;">
                                    <i class="fa-solid fa-right-from-bracket"></i>
                                    <span>Logout</span>
                                </button>
                            </form>

                        </div>
                    </div>
                </header>

                <div class="container">
                    <aside class="sidebar">
                        <div class="sidebar-header">
                            <i class="fa-solid fa-layer-group logo-icon"></i>
                        </div>
                        <nav class="menu">
                            <a href="/admin/dashboard" class="menu-item">
                                <i class="fa-solid fa-gauge-high"></i> Dashboard
                            </a>
                            <a href="/admin/user" class="menu-item">
                                <i class="fa-solid fa-user-large"></i> User
                            </a>
                            <a href="/admin/deck" class="menu-item">
                                <i class="fa-solid fa-book-open"></i> Deck FlashCard
                            </a>
                            <a href="/admin/course" class="menu-item">
                                <i class="fa-solid fa-graduation-cap"></i> Course
                            </a>
                            <a href="/admin/role" class="menu-item  active">
                                <i class="fa-solid fa-cube"></i> Role
                            </a>
                        </nav>
                    </aside>

                    <main class="main-content">
                        <section class="content-body">

                            <div class="table-wrapper">

                                <!-- Toolbar -->
                                <div class="toolbar">
                                    <div class="search-box">
                                        <input type="text" placeholder="Search role...">
                                    </div>

                                    <!-- ONLY CREATE -->
                                    <button class="btn-create" onclick="openPopup()">Create Role</button>
                                </div>

                                <!-- Table -->
                                <table class="data-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Role Name</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="role" items="${roles}">
                                            <tr>
                                                <td>${role.id}</td>
                                                <td><strong>${role.name}</strong></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>

                        </section>
                    </main>
                </div>

                <!-- POPUP CREATE -->
                <!-- POPUP CREATE -->
                <div id="container-popup" class="container-popup" style="display:none;">
                    <form:form class="popup" action="/admin/role/create" method="post" modelAttribute="role">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <h3>Create Role</h3>

                        <label>Role Name</label>
                        <input name="name" placeholder="Enter role name" required>

                        <div class="popup-buttons">
                            <button type="submit">Create</button>
                            <button type="button" onclick="closePopup()">Cancel</button>
                        </div>
                    </form:form> <!-- ✅ FIX ở đây -->
                </div>

                <script>
                    function openPopup() {
                        document.getElementById("container-popup").style.display = "flex";
                    }

                    function closePopup() {
                        document.getElementById("container-popup").style.display = "none";
                    }
                </script>

            </body>

            </html>