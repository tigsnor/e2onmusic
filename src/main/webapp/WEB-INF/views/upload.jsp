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
    </script>
</head>
<body>

<div class="container mt-3">
    <h3></h3>
    <button type="button" style="color: azure; width: 100px; font-size: 13px" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#uploadmodal">
        음원 업로드
    </button>
</div>

<!-- The Modal -->
<%-- 회원가입 모달 --%>
<div class="modal fade" id="uploadmodal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h1 class="modal-title">음원 업로드</h1>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <div class="member_login">
                    <form action="/upload" method="post" enctype="multipart/form-data">
                        <div class="mb-3" align="left">
                            음원파일
                            <input type="file" class="form-control" name="musicf">
                        </div>

                        <div class="mb-3" align="left">
                            썸네일
                            <input type="file" class="form-control" name="imgf">
                        </div>

                        <div class="mb-3">
                            <input type="text" class="form-control" name="musicname" placeholder="음악 제목">
                        </div>

                        <div class="mb-3">
                            <input type="text" class="form-control" name="albumname" placeholder="앨범 제목">
                        </div>

                        <div class="mb-3">
                            <input type="text" class="form-control" name="singer" placeholder="가수">
                        </div>

                        <div class="mb-3">
                            <input type="text" class="form-control" name="date" placeholder="발매일">
                        </div>

                        <div class="mb-3">
                            <select class="form-select" name="genre" id="genre">
                                <option value="ballad">발라드</option>
                                <option value="dance">댄스</option>
                                <option value="hiphop">랩/힙합</option>
                                <option value="R&B">R&B/soul</option>
                                <option value="indi">인디음악</option>
                                <option value="rock">록/메탈</option>
                                <option value="trot">트로트</option>
                                <option value="blues">포크/블루스</option>
                            </select>
                        </div>

                        <div class="music_upload_btn">

                            <input type="submit" class="btn btn-secondary" id="btn-upload" value="업로드하기">

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