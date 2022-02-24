let index = {
    init: function () {
        $("#btn-save").on("click", () => { // this 바인딩을 위해 화살표 함수 사용
            this.save();
        });
    },

    save: function () {
        // alert("user의 save함수 호출됨");
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()

        }

        // console.log(data);
        // ajax 호출시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청

        $.ajax({
            type: "POST",
            url: "/api/user",
            data: JSON.stringify(data), // body data
            contentType: "application/json; charset=utf-8",
            // dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 문자열 (생긴게 json이라면)
        }).done(function (resp) {
            alert("회원가입이 완료되었습니다.");
            console.log(resp);
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

index.init();