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
</head>
<body>

<div class="container mt-3">
    <h3></h3>
    <button style="width: 90px" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
        로그인
    </button>
</div>

<!-- The Modal -->
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h1 class="modal-title">로그인</h1>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <div class="member_login">

                    <form action=/login1 method="post" onsubmit="return loginForm(this);">
                        <div class="mb-3 mt-3">
                            <input type="text" class="form-control" name="username" placeholder="아이디" >
                        </div>

                        <div class="mb-3">
                            <input type="password" class="form-control" name="password" placeholder="비밀번호">
                        </div>

                        <div class="member_login_btn">

                            <input type="submit" class="btn btn-secondary" id="btn-login" value="로그인">

                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                취소
                            </button>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>