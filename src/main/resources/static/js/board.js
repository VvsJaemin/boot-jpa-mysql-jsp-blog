let index = {
    init: function () {
        $("#btn-save").on("click", () => { // this 바인딩을 위해 화살표 함수 사용
            this.save();
        });
    },

    save: function () {
        // alert("user의 save함수 호출됨");
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done(function (resp) {
            alert("글쓰기가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

index.init();