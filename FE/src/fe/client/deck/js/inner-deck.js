const wordData = [
    { eng: "Hello", vie: "Xin chào", img: "https://th.bing.com/th/id/OIP.tV1Yut2akoObzBb9nU_P-AHaD4?w=276&h=180&c=7&r=0&o=7&dpr=1.3&pid=1.7&rm=3" },
    { eng: "Lion", vie: "Sư tử", img: "https://th.bing.com/th/id/OIP.1v1LyOL7jzgNndpyVfDlGAHaFj?w=234&h=180&c=7&r=0&o=7&dpr=1.3&pid=1.7&rm=3" },
    { eng: "Tree", vie: "Cây cối", img: "https://th.bing.com/th/id/OIP.9JdJHb8SjAuBbZErEIEmbQHaFW?w=223&h=180&c=7&r=0&o=7&dpr=1.3&pid=1.7&rm=3" },
    { eng: "Pollution", vie: "Ô nhiễm", img: "https://th.bing.com/th/id/OIP.7YBSNMI5DuG5XUqmzuHdiQHaDt?w=349&h=174&c=7&r=0&o=7&dpr=1.3&pid=1.7&rm=3" }
];

const cardsGrid = document.getElementById('cardsGrid');

function renderCards() {
    cardsGrid.innerHTML = wordData.map(word => `
        <div class="card">
            <a href="/src/fe/client/deck/view.html">
                <div class="card-bg" style="background-image: url('${word.img}')"></div>

                <div class="card-content">
                    <h3>${word.eng}</h3>
                    <p>${word.vie}</p>
                </div>
            </a>
           
            <div class="audio-icon" onclick="playAudio('${word.eng}')">
                <i class="fa-solid fa-volume-high"></i>
            </div>
            <div class="card-action">
                <a href="/src/fe/client/deck/update.html" style="display: flex;">
                    <i class="fas fa-wrench fix_deck"></i>
                </a>
                <i class="fas fa-trash-alt trash"></i>
            </div>
        </div>
    `).join('');
}

function playAudio(word) {
    // Giả lập phát âm thanh (Có thể dùng Web Speech API)
    const utterance = new SpeechSynthesisUtterance(word);
    utterance.lang = 'en-US';
    window.speechSynthesis.speak(utterance);
}

// Chạy hàm render khi trang web tải xong
document.addEventListener('DOMContentLoaded', renderCards);

//Delete
cardsGrid.addEventListener("click", function (e) {
    console.log(e);
    if (e.target.classList.contains("trash")) {

        const card = e.target.closest(".card");

        card.remove();

    }

});