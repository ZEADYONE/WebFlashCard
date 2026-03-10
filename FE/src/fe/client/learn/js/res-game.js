const questions = [
    { word: "library", phonetic: "/ˈlaɪ.brer.i/", image: "https://images.unsplash.com/photo-1521587760476-6c12a4b040da?w=500", hiddenIndices: [1, 2, 5] },
    { word: "doctor", phonetic: "/ˈdɒk.tər/", image: "https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=500", hiddenIndices: [1, 4] }
];

let currentIndex = 0;
let score = { correct: 0, wrong: 0 };
let isLocked = false;

function loadQuestion() {
    const q = questions[currentIndex];
    const display = document.getElementById('word-display');

    display.innerHTML = '';
    document.getElementById('card-image').src = q.image;
    document.getElementById('card-phonetic').innerText = q.phonetic;
    document.getElementById('progress-text').innerText = `${currentIndex + 1}/${questions.length}`;
    document.getElementById('check-btn').disabled = false;
    isLocked = false;

    q.word.split('').forEach((char, index) => {
        const input = document.createElement('input');
        input.type = 'text';
        input.maxLength = 1;
        input.classList.add('char-box');
        if (!q.hiddenIndices.includes(index)) {
            input.value = char;
            input.readOnly = true;
        }
        input.addEventListener('keyup', (e) => handleInput(e, index));
        display.appendChild(input);
    });
}

function handleInput(e, index) {
    const inputs = document.querySelectorAll('.char-box');
    if (e.key === "Backspace" && index > 0) {
        let prev = index - 1;
        while (prev >= 0 && inputs[prev].readOnly) prev--;
        if (prev >= 0) inputs[prev].focus();
    } else if (e.target.value.length === 1 && index < inputs.length - 1) {
        let next = index + 1;
        while (next < inputs.length && inputs[next].readOnly) next++;
        if (next < inputs.length) inputs[next].focus();
    }
}

function checkAnswer() {
    if (isLocked) return;
    isLocked = true;

    const q = questions[currentIndex];
    const inputs = document.querySelectorAll('.char-box');
    let isCurrentCorrect = true;

    inputs.forEach((input, i) => {
        if (input.value.toLowerCase() !== q.word[i].toLowerCase()) {
            isCurrentCorrect = false;
            input.classList.add('wrong');
            input.value = q.word[i];
        } else if (!input.readOnly) {
            input.classList.add('correct');
        }
    });

    // Cập nhật điểm
    if (isCurrentCorrect) score.correct++; else score.wrong++;

    // Kiểm tra nếu là câu cuối cùng
    setTimeout(() => {
        if (currentIndex < questions.length - 1) {
            currentIndex++;
            loadQuestion();
        } else {
            showResult();
        }
    }, 2000); // Đợi 2 giây để xem đáp án rồi mới chuyển
}



loadQuestion();