<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>글상세</title>
</head>
<body>
<div class="modal fade" id="viewmodal${board.idx}">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">상세보기</h4>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <div class="member_view">
                    <div>
                        <div style="float: left; width: 40%">
                            <img class="imag-fluid" style="width: fit-content" src="/picture/${board.imgfile}" class="rounded">
                        </div>
                        <div style="float: left; width: 15%; font-weight: bold; font-size: 20px">
                            노래제목<br><br>
                            아티스트<br><br>
                            앨범명<br><br>
                            발매일<br><br>
                            장르
                        </div>
                        <div id="test" style="float: left; width: 45%; font-size: 20px">
                            ${board.musicname}<br><br>
                            ${board.singer}<br><br>
                            ${board.albumname}<br><br>
                            ${board.date}<br><br>
                            ${board.genre}
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button class="btn btn-light" type="button" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>