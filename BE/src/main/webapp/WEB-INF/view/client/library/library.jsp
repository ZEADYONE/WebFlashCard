<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="vi">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>English Learning Platform - My Library</title>
                <link rel="stylesheet" href="/css/client/style.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
            </head>

            <body>
                <header class="top-nav">
                    <div class="brand">
                        <h1>English Learning Platform</h1>
                        <p>Master English with Interactive Exercises</p>
                    </div>
                    <div class="nav-links">
                        <a href="homepage.html">HOME</a>
                        <a href="community.html">FLASHCARD</a>

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
                                <a href="/admin/user" class="dropdown-item">
                                    <i class="fa-regular fa-user"></i>
                                    <span>Admin</span>
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

                <div class="app-container">
                    <aside class="sidebar">
                        <div class="logo-section">
                            <i class="fas fa-layer-group main-logo"></i>
                        </div>
                        <nav class="side-nav">
                            <a href="/client/community" class="nav-item">
                                <i class="fas fa-users"></i>
                                <span>Community<small>Chia sẻ bộ flashcard</small></span>
                            </a>
                            <a href="/client/library" class="nav-item">
                                <i class="fas fa-book-open"></i>
                                <span>My Library<small>FlashCard của bạn</small></span>
                            </a>
                            <a href="/client/course" class="nav-item">
                                <i class="fas fa-graduation-cap"></i>
                                <span>Course <small>FlashCard từ Admin</small></span>
                            </a>
                        </nav>
                    </aside>

                    <main class="main-content">
                        <section class="library-section">
                            <div class="library-header">
                                <div class="title-area">
                                    <h2>My library</h2>
                                    <p>Manage your personal collections and study progress</p>
                                </div>
                                <div class="btn-create">
                                    <span>Create deck</span>
                                </div>
                            </div>

                            <div class="toolbar">
                                <div class="search-box">
                                    <i class="fas fa-search"></i>
                                    <input type="text" placeholder="Search library ...">
                                </div>
                                <div class="filter-container">
                                    <button class="filter-btn" id="filterBtn">
                                        <i class="fas fa-filter"></i>
                                    </button>
                                    <div class="filter-dropdown" id="filterDropdown">
                                        <form action="https://www.w3schools.com/action_page.php" method="GET"
                                            style="display:flex; flex-direction: column;">
                                            <!-- checked -->
                                            <label><input type="checkbox" name="scope" value="Public"> Public</label>
                                            <label><input type="checkbox" name="scope" value="Private"> Private</label>
                                            <label><input type="checkbox" name="scope" value="Public"> You</label>
                                            <label><input type="checkbox" name="scope" value="Private"> Admin</label>
                                            <label><input type="checkbox" name="scope" value="Private"> Other</label>
                                            <button type="submit">
                                                <i class="fas fa-filter dropdown-icon"></i>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>

                            <div class="deck-grid">
                                <c:forEach var="deck" items="${decks}">

                                    <div class="deck-card">

                                        <!-- CLICK vào deck -->
                                        <a href="/client/deck/${deck.id}">
                                            <div class="card-top">
                                                <img src="${deck.imageUrl}" />

                                                <!-- Scope icon -->
                                                <c:choose>
                                                    <c:when test="${deck.scope == 'PUBLIC'}">
                                                        <i class="fas fa-globe status-icon"></i>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <i class="fas fa-lock status-icon"></i>
                                                    </c:otherwise>
                                                </c:choose>

                                                <span class="card-count">${deck.totalCards} cards</span>
                                            </div>

                                            <div class="card-body">
                                                <h3>${deck.title}</h3>
                                                <p>${deck.description}</p>

                                                <!-- <div class="progress-container">
                                                    <div class="progress-bar" style="width: ${deck.progressPercent}%;">
                                                    </div>
                                                </div> -->

                                                <span class="progress-text">
                                                    ${deck.learnedCards}/${deck.totalCards}
                                                </span>
                                            </div>
                                        </a>

                                        <!-- Footer -->
                                        <div class="card-footer">
                                            <span>
                                                <i class="far fa-user"></i>
                                                ${deck.ownerName}
                                            </span>

                                            <div class="card-actions">

                                                <!-- CHỈ cho phép sửa nếu là owner -->
                                                <c:if test="${deck.ownerId == sessionScope.userId}">
                                                    <i class="fas fa-wrench fix_deck"></i>
                                                    <i class="fas fa-trash-alt trash"></i>
                                                </c:if>

                                            </div>
                                        </div>

                                    </div>

                                </c:forEach>
                            </div>
                        </section>
                    </main>
                </div>
                <div id="container-popup" class="container-popup">
                    <form class="popup" action="/client/deck/create" method="post" enctype="multipart/form-data">
                        <div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </div>

                        <h3 id="feature">Edit Deck</h3>
                        <label for="popup-title">Title</label>
                        <input id="popup-title" name="title" placeholder="Title">

                        <label for="popup-desc">Description</label>
                        <input id="popup-desc" name="des" placeholder="Description">

                        <label>Scope</label>
                        <select id="popup-scope" name="scope">
                            <option value="Public">Public</option>
                            <option value="Private">Private</option>
                        </select>

                        <label for="img">Image
                            <div id="popup-image" class="popup-image">
                            </div>
                            <input type="file" name="images" id="img" accept=".png, .jpg, .jpeg" hidden>
                        </label>

                        <div class="popup-buttons">
                            <button type="submit" id="save">Save change</button>
                            <button type="button" id="cancel">Cancel</button>
                        </div>
                    </form>


                </div>

                <script src="/js/client/script.js"></script>
            </body>

            </html>