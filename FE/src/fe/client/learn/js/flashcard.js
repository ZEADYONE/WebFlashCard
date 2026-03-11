// 
const questions = [
    {
        word: "Hello",
        phonetic: "/heˈləʊ/",
        image: "https://th.bing.com/th/id/OIP.tV1Yut2akoObzBb9nU_P-AHaD4?w=276&h=180&c=7&r=0&o=7&dpr=1.3&pid=1.7&rm=3",
        meaning: "Xin chào",
        definition: "Hello là một thán từ trong tiếng Anh dùng để chào hỏi hoặc bắt đầu cuộc trò chuyện, thường được sử dụng khi gặp ai đó, khi bắt đầu nói chuyện hoặc khi nghe điện thoại, với ý nghĩa tương đương “xin chào” trong tiếng Việt",
        example: "Hello, this is John speaking",
    },
    {
        word: "Lion",
        phonetic: "/ˈlaɪ.ən/",
        image: "https://th.bing.com/th/id/OIP.1v1LyOL7jzgNndpyVfDlGAHaFj?w=234&h=180&c=7&r=0&o=7&dpr=1.3&pid=1.7&rm=3",
        meaning: "Sư tử",
        definition: ""
    },
    {
        word: "Tree",
        phonetic: "/triː/",
        image: "https://th.bing.com/th/id/OIP.9JdJHb8SjAuBbZErEIEmbQHaFW?w=223&h=180&c=7&r=0&o=7&dpr=1.3&pid=1.7&rm=3",
        meaning: "Cây cối"
    },
    {
        word: "Pollution",
        phonetic: "/pəˈluː.ʃən/",
        image: "https://th.bing.com/th/id/OIP.7YBSNMI5DuG5XUqmzuHdiQHaDt?w=349&h=174&c=7&r=0&o=7&dpr=1.3&pid=1.7&rm=3",
        meaning: "Ô nhiễm"
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