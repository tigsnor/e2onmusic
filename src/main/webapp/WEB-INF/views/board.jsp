<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시판 목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function Mplay(src){
            var audio = document.getElementById('audio');
            audio.autoplay = true;
            audio.src = src;
            audio.load()
            audio.play();
        }
    </script>
    <script>
        function loginForm(form) {
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
        }
    </script>
</head>
<%@ include file="top.jsp" %>
<body>

<div class="container-fluid pt-5">
    <div class="row" style="margin-right: 20px; margin-left: 20px; margin-bottom: 100px">
        <%--    게시판 부분--%>
        <div class="col-sm-10" >
            <div id="posts_list">
                <table id="table" class="table table-horizontal">
                    <tr class="table-primary">
                        <th style="width: 10%">번호</th>
                        <th style="width: 30%">제목</th>
                        <th style="width: 20%">아티스트</th>
                        <th style="width: 20%">발매일</th>
                        <th style="width: 10%">재생</th>
                        <th style="width: 10%">다운로드</th>

                    </tr>

                    <c:if test="${empty board}">
                        <tr>
                            <td colspan="6">검색결과가 없습니다.</td>
                        </tr>
                    </c:if>

                    <c:forEach varStatus="status" var="board" items="${board}">
                        <c:set var="src" value="play/${board.musicfile}"/>
                        <tr>
                            <td>${status.count}</td>
                            <td style="text-align: left"><a href data-bs-toggle="modal" data-bs-target="#viewmodal${board.idx}" data-notifyid="${board}">
                                    ${board.musicname}</a></td>
                            <td>${board.singer}</td>
                            <td>${board.date}</td>

                            <c:choose>
                                <c:when test="${empty member}">
                                    <td><button class="btn btn-primary" id="btnPlay${board.idx}" type="button" onclick="alert('로그인후 사용해주세요')">
                                        <i class="bi bi-play-fill"/>
                                    </button></td>
                                    <td><button class="btn btn-primary" type="button" onclick="alert('로그인후 사용해주세요')">
                                        <i class="bi bi-arrow-down-circle-fill">
                                    </button></td>
                                </c:when>
                                <c:otherwise>
                                    <td><button class="btn btn-primary" id="btnPlay${board.idx}" type="button" onclick="Mplay('${src}');">
                                        <i class="bi bi-play-fill"/>
                                    </button></td>
                                    <td><button class="btn btn-primary" type="button" onclick="location.href='/download?filename=${board.musicfile}&fileoriname=${fn:replace(fn:replace(board.musicorifile, '&','%26'), '\'', '%27')}'">
                                        <i class="bi bi-arrow-down-circle-fill">
                                    </button></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>

                        <%--모달--%>
                        <%@ include file="view.jsp"%>

                    </c:forEach>
                </table>

                <%-- 페이징 버튼 --%>
                <ul class="pagination justify-content-center" style="margin:0 auto">
                    <c:if test="${!(empty board)}">
                        <c:set var="j" value="${totalPage}"/>
                        <c:set var="set" value="5"/>
                        <fmt:parseNumber integerOnly="true" var="end" value="${(pageNo/set)}"/>
                        <c:set var="endPage" value="${end*set+5}"></c:set>
                        <c:set var="startPage" value="${(endPage - set)+1}"/>
                        <c:if test="${endPage > totalPage}">
                            <c:set var="endPage" value="${totalPage}"/>
                        </c:if>
                        <c:forEach var="page" begin="${startPage-1}" end="${endPage-1}">
                            <c:if test="${!(startPage eq '1') && startPage eq page+1}">
                                <li class="page-item">
                                    <a class="page-link" href="?page=${page-1}">prev</a>
                                </li>
                            </c:if>
                            <c:choose>
                                <c:when test="${pageNo eq page}">
                                    <li class="page-item active disabled">
                                        <a class="page-link" href="?page=${page}">${page + 1}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link" href="?page=${page}">${page + 1}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${!(endPage eq totalPage) && endPage eq page+1}">
                                <li class="page-item">
                                    <a class="page-link" href="?page=${page+1}">next</a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
        </div>
        <c:choose>
            <c:when test="${empty member}">
                <div class="col-sm-2" align="center">
                    <c:if test="${2 eq result}">
                        <h5 style="color: red">아이디 비밀번호가 틀렸습니다.</h5>
                    </c:if>
                    <%@ include file="login.jsp" %>
                    <%@ include file="join.jsp" %>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-sm-2" align="center">
                    <h4>${member}님<br> 환영합니다!</h4>
                    <%@ include file="upload.jsp" %>
                    <button algin type="button" class="btn btn-danger" onclick="location.href='/logout'"
                        style="margin-top: 20px; width: 100px;" >
                        로그아웃
                    </button algin>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <nav class="navbar navbar-expand-sm bg-light fixed-bottom d-felx justify-content-center">
        <audio style="width: 50%;" id="audio" controls="controls">
        </audio>
    </nav>
</div>
</body>
</html>