<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <style>
        form div{
            margin : 5px;
        }
        label{
            width : 50px;
            display : inline-block;
        }
        .btns{
            margin-left : 100px;
        }
    </style>
    <title>board</title>
</head>
<body>
<h1>게시판 글 수정</h1>
<form id="editForm" method="post">
    <input th:value="${board.boardno}" hidden="hidden">
    <div>
        <label for="title">제목</label>
        <input type="text" id="title" name="title" th:value="${board.title}">
    </div>
    <div>
        <label for="writer">작성자</label>
        <input type="text" id="writer" name="writer" th:value="${board.writer}">
    </div>
    <div>
        <label for="content">내용</label>
        <textarea id="content" name="content" th:text="${board.content}">
			</textarea>
    </div>
</form>
<div class="btns">
    <button type="button" onclick="window.history.go(-1); return false;">취소</button>
    <button type="submit" form="editForm">저장</button>
</div>
</body>
</html>