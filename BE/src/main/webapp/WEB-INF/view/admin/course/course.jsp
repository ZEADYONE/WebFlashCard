<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="vi">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Course Management - English Learning Platform</title>
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
                <link rel="stylesheet" href="/css/admin/course.css">
                <link rel="stylesheet" href="/css/admin/header-slide.css">
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
                                    style="width: 100%; border: 0px none; background-color: white; cursor: pointer; text-align: left;">
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
                            <a href="/admin/course" class="menu-item active">
                                <i class="fa-solid fa-graduation-cap"></i> Course
                            </a>
                            <a href="/admin/role" class="menu-item">
                                <i class="fa-solid fa-cube"></i> Role
                            </a>
                        </nav>
                    </aside>

                    <main class="main-content">
                        <section class="library-section">
                            <div class="library-header">
                                <div class="btn-create">
                                    <a href="/admin/course/create" style="text-decoration: none; color: inherit;">
                                        <span>Create Course</span>
                                    </a>
                                </div>
                            </div>

                            <div class="toolbar">
                                <div class="search-box">
                                    <i class="fas fa-search"></i>
                                    <input type="text" placeholder="Search library ...">
                                </div>
                                <div class="filter-dropdown-container">
                                    <button class="filter-btn" id="filterBtn">
                                        <i class="fa-solid fa-filter"></i>
                                    </button>
                                    <form class="filter-dropdown" id="filterMenu" style="right: 0px; left: auto;">
                                        <label><input type="checkbox" name="scope" value="Public">Public</label>
                                        <label><input type="checkbox" name="scope" value="Private">Private</label>
                                        <button type="submit" class="filter-btn-submit">Apply</button>
                                    </form>
                                </div>
                            </div>

                            <div class="deck-grid">
                                <c:forEach var="course" items="${listCourse}">
                                    <div class="deck-card">
                                        <a href="/admin/course/view/${course.id}">
                                            <div class="card-top">
                                                <img src="/images/client/${course.image}" alt="${course.title}">

                                                <c:choose>
                                                    <c:when test="${course.scope == 'Private'}">
                                                        <i class="fas fa-lock status-icon"></i>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <i class="fas fa-globe status-icon"></i>
                                                    </c:otherwise>
                                                </c:choose>

                                                <span class="card-count">${course.card.size()} card</span>
                                            </div>

                                            <div class="card-body">
                                                <h3>${course.title}</h3>
                                                <p>${course.des}</p>
                                            </div>
                                        </a>

                                        <div class="card-footer">
                                            <span><i class="far fa-user"></i> Admin</span>
                                            <div class="card-actions">
                                                <a href="/admin/course/update/${course.id}"><i
                                                        class="fas fa-wrench fix_deck"></i></a>
                                                <a href="/admin/course/delete/${course.id}"><i
                                                        class="fas fa-trash-alt trash"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </section>
                    </main>
                </div>

                <script src="/js/admin/style.js"></script>
            </body>

            </html>