// Dữ liệu mẫu
const questions = [
    {
        word: "Library",
        phonetic: "/ˈlaɪ.brer.i/",
        image: "https://images.unsplash.com/photo-1521587760476-6c12a4b040da?auto=format&fit=crop&w=500&q=80",
        meaning: "Thư viện: Nơi chứa sách và các tài liệu để đọc hoặc mượn."
    },
    {
        word: "Hospital",
        phonetic: "/ˈhɒs.pɪ.təl/",
        image: "https://images.unsplash.com/photo-1586773860418-d37222d8fce2?auto=format&fit=crop&w=500&q=80",
        meaning: "Bệnh viện: Nơi chăm sóc và điều trị người bệnh."
    },
    {
        word: "School",
        phonetic: "/skuːl/",
        image: "https://images.unsplash.com/photo-1546410531-bb4caa6b424d?auto=format&fit=crop&w=500&q=80",
        meaning: "Trường học: Nơi học sinh đến để học tập."
    }
];

let currentIndex = 0;

const flashcard = document.getElementById('flashcard');
const wordEl = document.getElementById('card-word');
const phoneticEl = document.getElementById('card-phonetic');
const imageEl = document.getElementById('card-image');
const meaningEl = document.getElementById('card-meaning');
const progressEl = document.getElementById('progress-text');

// Hàm lật thẻ
function flipCard() {
    flashcard.classList.toggle('flipped');
}

// Hàm chuyển câu tiếp theo
function nextQuestion() {
    // Hiệu ứng nhẹ: Luôn để mặt trước hiện ra khi sang câu mới
    flashcard.classList.remove('flipped');

    // Đợi hiệu ứng lật (nếu có) rồi mới đổi nội dung
    setTimeout(() => {
        currentIndex++;

        if (currentIndex >= questions.length) {
            alert("Bạn đã hoàn thành bài học!");
            currentIndex = 0; // Quay lại từ đầu
        }

        updateUI();
    }, 150);
}

// Cập nhật giao diện
function updateUI() {
    const q = questions[currentIndex];
    wordEl.innerText = q.word;
    phoneticEl.innerText = q.phonetic;
    imageEl.src = q.image;
    meaningEl.innerText = q.meaning;
    progressEl.innerText = `${currentIndex + 1}/${questions.length}`;
}

// Khởi tạo lần đầu
updateUI();