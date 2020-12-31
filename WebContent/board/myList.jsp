<%@page import="java.util.List"%>
<%@page import="d.board.db.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String id = null;
	if(session.getAttribute("id") != null){
		id = (String)session.getAttribute("id");
	}
	
	List boardList = (List)request.getAttribute("boardlist");
	int nowpage = ((Integer)request.getAttribute("page")).intValue();
	int maxpage = ((Integer)request.getAttribute("maxpage")).intValue();
	int startpage = ((Integer)request.getAttribute("startpage")).intValue();
	int endpage = ((Integer)request.getAttribute("endpage")).intValue();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My List</title>
<style>

.all {
   width : 100%;
   font-family:tvN 즐거운이야기;
   font-size:25pt;
}

.main { 
   text-decoration:none;
   color:white;
   font-family:tvN 즐거운이야기;
   font-size:25pt;
   font-weight:bold;
}

.write {
   padding-right:7.5%;
   text-decoration:none;
   color:#FF7E7E;
   font-family:tvN 즐거운이야기;
   font-size:25pt;
   font-weight:bold;
}

a:hover {
   color:#36589C;
}

a{
   text-decoration:none;
}

.h {
   width : 100%;
   height : 8%;
   top : 0;
   left:0;
   position :fixed;
   background-color :#FFB4B4;
   font-family:tvN 즐거운이야기;
   font-size:30pt;
   font-weight:bold;
   padding-top:0.5%;
   padding-left:0.5%;
   color:white;
}

.f {
   width : 100%;
   height : 10%;
   bottom : 0;
   left:0;
   position : fixed;
   background-color :#FFF2E6;
}

#tab {
   padding-left:75%;
}
</style>

</head>
<body link="#444F59" vlink="#CFCFCF" alink="#FFB4B4">
<div class="h">마이 리스트
<img src="../images/mylist1.png" width="35px" height="40px">
<a href="./BoardMainAction.bo" id="tab" class="main">
메인 화면으로</a>
</div>
<br><br><br>

<!-- main -->
<table class = "all">
   <tr>
      <td>
         <div> 번호 </div>    
      </td>   
      <td>
         <div> 제목 </div>    
      </td>
      <td>
         <div> 작성자 </div>    
      </td>
      <td>
         <div> 날짜 </div>    
      </td>
      <td>
         <div> 조회수 </div>    
      </td>
   </tr>
   <%
       for(int i = 0; i < boardList.size(); i++){
      		BoardBean bl = (BoardBean)boardList.get(i);
      	System.out.println(boardList.size());
   %>
   <tr>
      <td>
         <%=bl.getB_NUM()  %>
      </td>
   		
      <td>
         <div>
			<a href="./BoardDetailAction.bo?num=<%=bl.getB_NUM() %>">
			<%=bl.getB_TITLE() %>
			</a>
         </div>
      </td>
      
      <td>
         <div> <%=bl.getB_ID() %> </div>
      </td>
      <td>
         <div> <%=bl.getB_DATE() %></div>
      </td>
      <td>
         <div> <%=bl.getB_READCOUNT() %></div>
      </td>
   </tr>
   <%} %>
   <tr>
      <td>
		<% if(nowpage <= 1) { %>
			이전 &lt; &nbsp;
		<% } else { %>
		<a href="/BoardMyListAction.bo?page=<%=nowpage-1 %>">이전 &lt;</a>&nbsp;
		<%} %>
		
		<% for(int a = startpage; a <= endpage; a++) {
			if(a == nowpage) {
		%>
		[<%=a %>]
		<%} else { %>
		<a href="/BoardMyListAction.bo?page=<%=a %>"><%=a %></a>
		&nbsp;
		<%} %>
		<%} %>
		
		<% if(nowpage >= maxpage) { %>
			&gt; 다음
			<%}else { %>
			<a href="/BoardMyListAction.bo?page=<%=nowpage+1 %>">&gt; 다음</a>
			<% }  %>
      </td>
   </tr>
</table>

<footer align="right">
   <div class="f">
      <br>
      <a class="write" href="/BoardWriteView.bo"> 일기 쓰기 </a>
   </div>         
</footer>
</body>

</html>