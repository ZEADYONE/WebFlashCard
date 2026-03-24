<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="vi">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>User Management - English Learning Platform</title>
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
                <link rel="stylesheet" href="/css/admin/user.css">
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
                        <span class="user-name">${currentUser.fullName != null ? currentUser.fullName : 'Admin'}</span>
                        <i class="fa-solid fa-chevron-down mini-arrow"></i>

                        <div class="info-dropdown" id="infoDropdown">
                            <a href="/admin/profile">
                                <div class="dropdown-item"><i class="fa-solid fa-circle-info"></i> Information</div>
                            </a>
                            <a href="/">
                                <div class="dropdown-item"><i class="fa-regular fa-user"></i> Client</div>
                            </a>
                            <form action="/logout" method="post" style="display: inline;">
                                <button type="submit" class="dropdown-item"
                                    style="border:none; background:none; width:100%; text-align:left; cursor:pointer;">
                                    <i class="fa-solid fa-right-from-bracket"></i> Logout
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
                            <a href="/admin/user" class="menu-item active">
                                <i class="fa-solid fa-user-large"></i> User
                            </a>
                            <a href="/admin/deck" class="menu-item">
                                <i class="fa-solid fa-book-open"></i> Deck FlashCard
                            </a>
                            <a href="/admin/course" class="menu-item">
                                <i class="fa-solid fa-graduation-cap"></i> Course
                            </a>
                            <a href="/admin/role" class="menu-item">
                                <i class="fa-solid fa-cube"></i> Role
                            </a>
                        </nav>
                    </aside>

                    <main class="main-content">
                        <section class="content-body">
                            <div class="table-wrapper">
                                <div class="toolbar">
                                    <div class="search-filter-group">
                                        <form action="/admin/users/search" method="get" class="search-box">
                                            <i class="fa-solid fa-magnifying-glass"></i>
                                            <input type="text" name="keyword" placeholder="Search..."
                                                value="${param.keyword}">
                                        </form>

                                        <div class="filter-dropdown-container">
                                            <button class="filter-btn" id="filterBtn">
                                                <i class="fa-solid fa-filter"></i>
                                            </button>
                                            <form class="filter-dropdown" id="filterMenu" action="/admin/users/filter"
                                                method="get">
                                                <label><input type="checkbox" name="status" value="BANNED">
                                                    Banned</label>
                                                <label><input type="checkbox" name="status" value="ACTIVE">
                                                    Active</label>
                                                <label><input type="checkbox" name="role" value="ADMIN"> Admin</label>
                                                <label><input type="checkbox" name="role" value="USER"> User</label>
                                                <button type="submit" class="filter-btn-submit">Apply</button>
                                            </form>
                                        </div>
                                    </div>
                                    <a href="/admin/user/create">
                                        <button class="btn-create">Create user</button>
                                    </a>
                                </div>

                                <table class="user-table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>UserName</th>
                                            <th>Email</th>
                                            <th>Phone Number</th>
                                            <th>Role</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${listUser}">
                                            <tr>
                                                <td>${user.id}</td>
                                                <td>${user.userName}</td>
                                                <td>${user.email}</td>
                                                <td>${user.phoneNumber}</td>
                                                <td>${user.role.name}</td>
                                                <td>${user.status}</td>
                                                <td class="actions">
                                                    <a href="/admin/user/view/${user.id}"><i
                                                            class="fa-regular fa-eye btn-view"></i></a>
                                                    <a href="/admin/user/update/${user.id}"><i
                                                            class="fa-solid fa-wrench btn-edit"></i></a>

                                                    <form action="/admin/user/status/${user.id}" method="POST"
                                                        style="display:inline;">
                                                        <div style="display: none;">
                                                            <input type="hidden" name="${_csrf.parameterName}"
                                                                value="${_csrf.token}" />
                                                        </div>
                                                        <button type="submit"
                                                            style="background: none; border: none; padding: 0; cursor: pointer;">
                                                            <c:choose>
                                                                <c:when test="${user.status == true}">
                                                                    <i class="fa-solid fa-lock-open btn-unlock"></i>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <i class="fa-solid fa-lock btn-lock"></i>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <div class="pagination">
                                    <c:forEach begin="1" end="${totalPages}" var="i">
                                        <a href="/admin/users?page=${i}"
                                            class="${currentPage == i ? 'active' : ''}">${i}</a>
                                    </c:forEach>
                                </div>
                            </div>
                        </section>
                    </main>
                </div>
                <script src="/js/admin/style.js"></script>
                <script src="/js/admin/user.js"></script>
            </body>

            </html>