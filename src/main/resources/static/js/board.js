let index = {
    init: function () {
        $("#btn-save").on("click", () => { // this 바인딩을 위해 화살표 함수 사용
            this.save();
        });
        $("#btn-delete").on("click", () => { // this 바인딩을 위해 화살표 함수 사용
            this.deleteById();
        });
        $("#btn-update").on("click", () => { // this 바인딩을 위해 화살표 함수 사용
            this.update();
        });
        $("#btn-reply-save").on("click", () => { // this 바인딩을 위해 화살표 함수 사용
            this.replySave();
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
    },
    deleteById: function () {
        let id = $("#id").text();
        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,
            dataType : "json"
        }).done(function (resp) {
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        let id = $("#id").val();
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }

        $.ajax({
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done(function (resp) {
            alert("수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    replySave: function () {
        // alert("user의 save함수 호출됨");
        let data = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val()
        };

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done(function (resp) {
            alert("댓글 작성이 완료되었습니다.");
            location.href = `/board/${data.boardId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
}

index.init();