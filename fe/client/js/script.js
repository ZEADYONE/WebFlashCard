// Xử lý bật/tắt dropdown bộ lọc
const filterBtn = document.getElementById('filterBtn');
const filterDropdown = document.getElementById('filterDropdown');

filterBtn.addEventListener('click', (e) => {
    filterDropdown.classList.toggle('show');
    e.stopPropagation();
});

// Đóng dropdown khi nhấn ra ngoài
window.addEventListener('click', () => {
    filterDropdown.classList.remove('show');
});

filterDropdown.addEventListener('click', (e) => {
    e.stopPropagation();
});

const currentPage = window.location.pathname;

document.querySelectorAll(".nav-item").forEach(link => {

    if (link.getAttribute("href") === currentPage) {
        link.classList.add("active");
    }

});