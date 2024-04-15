<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #list-area{
        border: 1px solid white;
        text-align: center;
    }

    .outer a{
        color: white;
        text-decoration: none;
    }

    #search-area{
        margin-bottom: 24px;
    }
</style>
</head>
<body>
    <jsp:include page="../common/menubar.jsp" />

    <div class="outer" align="center">
        <br>
        <h1 align="center">게시판</h1>
        <br>

        <div id="search-area">
            <form action="search.bo">
                <input type="hidden" name="cpage" value="1">
                <!-- 서치 키워드 뿐만 아니라 이 리스트 페이지로 다시 돌아와야 하므로 페이지 정보도 같이 넘겨줘야 함 -->
                <select name="condition">
                    <option value="writer">작성자</option>
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                </select>
                <input type="text" name="keyword" value="${keyword}">
                <!--  EL이니까 값이 없으면 빈칸으로 들어가기 때문에 문제 없음 -->
                <button type="submit">검색</button>
            </form>
        </div>
        
        <c:if test="${not empty condition}">
            <script>
                window.onload = function(){
                    const opt = document.querySelector("#search-area option[value=${condition}]");
                    opt.setAttribute("selected", true);
                }
                // window 함수를 걸어주는 게 안전함
                // 실행하는 애와 돔트리 그리는 애는 서로 기다려주지 않고 쭉 그리는데, 만약 실행이 먼저 되면 돔트리가 없어 이 식별자 못 찾음
                // 따라서 window에 함수로 걸어주는 게 안전
            </script>
        </c:if>

        <table id="list-area">
            <thead>
                <tr>
                    <th>글번호</th>
                    <th width="400">제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
	            <c:forEach var="b" items="${list}">
	                <tr>
	                    <td>${b.boardNo}</td>
	                    <td><a href="detail.bo?bno=${b.boardNo}">${b.boardTitle}</a></td>
	                    <td>${b.boardWriter}</td>
	                    <td>${b.count}</td>
	                    <td>${b.createDate}</td>
	                </tr>
	            </c:forEach>
            </tbody>
            
        </table>
        
        <br>
        
        <div id="paging-area">
	        <c:if test="${pi.currentPage ne 1}">
	        	<a href="list.bo?cpage=${pi.currentPage - 1}">[이전]</a>
	        </c:if>
	        
	        <c:forEach var="i" begin="${pi.startPage }" end="${pi.endPage }">
	        	<c:choose>
		        	<c:when test="${empty condition }">
		        		<a href="list.bo?cpage=${i}">${i}</a>
		        	</c:when>
		        	<c:otherwise>
		        		<a href="search.bo?cpage=${i}&condition=${condition}&keyword=${keyword}">${i}</a>
		        	</c:otherwise>
	        	</c:choose>
	        </c:forEach>
	        
	        <c:if test="${pi.currentPage ne pi.maxPage}">
	        	<a href="list.bo?cpage=${pi.currentPage + 1}">[다음]</a>
	        </c:if>  
    </div>
</body>
</html>