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

    if ("/src/fe/client/" + link.getAttribute("href") === currentPage) {
        link.classList.add("active");
    }

});


const fixDeck = document.querySelectorAll(".fix_deck");

const popupContainer = document.getElementById("container-popup");

const popupTitle = document.getElementById("popup-title");
const popupDesc = document.getElementById("popup-desc");
const popupImg = document.getElementById("popup-img");

const saveBtn = document.getElementById("save");
const cancelBtn = document.getElementById("cancel");

let currentCard = null;

fixDeck.forEach(item => {

    item.addEventListener("click", function () {

        const card = item.closest(".deck-card");


        currentCard = card;

        const title = card.querySelector("h3").innerText;
        const desc = card.querySelector("p").innerText;
        const img = card.querySelector("img").src;

        popupTitle.value = title;
        popupDesc.value = desc;
        popupImg.src = img;
        popupContainer.style.display = "flex";

    });

});

const scopeSelect = document.getElementById("popup-scope");
const icon = document.querySelector(".status-icon");

scopeSelect.addEventListener("change", function () {

    if (scopeSelect.value === "Private") {
        icon.classList.remove("fa-globe");
        icon.classList.add("fa-lock");
    } else {
        icon.classList.remove("fa-lock");
        icon.classList.add("fa-globe");
    }

});

// SAVE CHANGE
saveBtn.addEventListener("click", function () {

    if (!currentCard) return;

    currentCard.querySelector("h3").innerText = popupTitle.value;
    currentCard.querySelector("p").innerText = popupDesc.value;

    popupContainer.style.display = "none";

});


// CANCEL
cancelBtn.addEventListener("click", function () {
    popupContainer.style.display = "none";
});


// CLICK OUTSIDE POPUP
popupContainer.addEventListener("click", function (e) {

    if (e.target === popupContainer) {
        popupContainer.style.display = "none";
    }

});

const trash = document.querySelectorAll(".trash");

trash.forEach(item => {
    item.addEventListener("click", function () {
        const deck = item.closest(".deck-card");
        deck.style.display = "none";
    })
})


