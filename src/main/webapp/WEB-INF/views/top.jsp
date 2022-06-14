<%@page contentType="text/html; charset=UTF-8" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="container-fluid-xl">
        <%--  위쪽메뉴 --%>
        <div class="p-3 bg-light text-black text-center">
            <h1>E2on 음원 재생 사이트</h1>
            <p>김성범 음원 재생 사이트 프로젝트입니다.</p>
        </div>
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <div class="container-fluid">
                <%-- 로고 --%>
                <a class="navbar-brand" href="/">로고자리</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="mynavbar">

                    <ul class="navbar-nav me-auto">
                    </ul>

                    <form class="d-flex" action="/search" method="POST" role="search" autocomplete="off">
                        <select style="width: 80px" class="form-select" name="musicselect">
                            <option id="musicname" value="musicname">제목</option>
                            <option id="artist" value="artist">가수</option>
                        </select>
                        <input class="form-control" type="text" name="keyword" id="search">
                        <button class="btn btn-primary bi bi-search"></button>
                    </form>
                </div>
            </div>
        </nav>
    </div>
</body>
</html>
