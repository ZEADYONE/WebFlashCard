
function playAudio(soundFile) {
    if (!soundFile || soundFile === "") {
        alert("Chưa có file âm thanh cho từ này!");
        return;
    }

    // Đường dẫn phải khớp với thư mục bạn lưu trong UploadService
    const audioPath = " /sound/client/" + soundFile;

    const audio = new Audio(audioPath);
    audio.play().catch(e => {
        console.error("Lỗi phát âm thanh: ", e);
        alert("Không thể phát file âm thanh. Hãy kiểm tra lại đường dẫn!");
    });
}