<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function joinForm(form) {
            if (form.id.value == "") {
                alert("아이디를 입력하세요.");
                form.id.focus();
                return false;
            }
            if (form.password.value == "") {
                alert("비밀번호를 입력하세요");
                form.password.focus();
                return false;
            }
            if (form.name.value == "") {
                alert("이름을 입력하세요");
                form.name.focus();
                return false;
            }
        }
    </script>
</head>
<body>

<div class="container mt-3">
    <h3></h3>
    <button style="font-size: 14px; height: 40px; width: 90px" type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#joinmodal">
        회원가입
    </button>
</div>

<!-- The Modal -->
<%-- 회원가입 모달 --%>
<div class="modal fade" id="joinmodal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h1 class="modal-title">회원가입</h1>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <div class="member_login">
                    <form action="/signup" method="post" onsubmit="return joinForm(this);">
                        <div class="mb-3 mt-3">
                            <input type="text" class="form-control" name="id" placeholder="아이디" >
                        </div>

                        <div class="mb-3">
                            <input type="password" class="form-control" name="password" placeholder="비밀번호">
                        </div>

                        <div class="mb-3">
                            <input type="text" class="form-control" name="name" placeholder="이름">
                        </div>

                        <div class="member_login_btn">

                            <input type="submit" class="btn btn-secondary" id="btn-join" value="회원가입하기">

                            <button class="btn btn-light" type="button" data-bs-dismiss="modal">닫기</button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
            </div>

        </div>
    </div>
</div>

</body>
</html>