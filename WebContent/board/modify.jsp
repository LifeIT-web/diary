<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="d.board.db.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Page</title>
<style>
.h {
   width : 100%;
   height : 8%;
   top:0;
   left:0;
   position :fixed;
   background-color :#FFB4B4;
   color:white;
   font-family:tvN 즐거운이야기;
   font-size:30pt;
   font-weight:bold;
   padding-top:0.5%;
   padding-left:0.5%;
}

.f {
   width : 100%;
   height : 10%;
   bottom : 0;
   position : fixed;
   background-color :#FFF2E6;
   left:0;
}

.all {
   text-align:center;
   font-family:tvN 즐거운이야기;
   font-size:18pt;
}

td {
	font-size:25pt;
}

.img1 {
	padding-right:3%;
	padding-top:0.8%;
}

.img2 {
	padding-right:1%;
}

input[type=text], textarea {
   font-family:DX경필명조B;
   font-size:15pt;
   border:2px solid #FFB4B4;
   width:700px;
}

input[type=text] {
   height:30px;
}

input[type=file] {
   font-family:DX경필명조B;
   font-size:15pt;
}


input:focus, textarea:focus { outline:none; }
</style>

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
$(function() {
	$("#ok").click(function() {
		$(this).attr('src', '../images/ok2.png');
	});
	
	$("#back").click(function() {
		$(this).attr('src', '../images/back2.png');
	});
});
</script>
</head>
	<%
		BoardBean board = (BoardBean)request.getAttribute("board");
	%>
<body leftmargin="0">
<form action="/BoardModifyAction.bo" method = "post" name = "modifyform">
<input type="hidden" name="B_NUM" value="<%=board.getB_NUM() %>" />
<div class = "h">오늘을 쓰다</div>
<br><br><br>
<table class="all" align="center"> 
   <tr>
      <td>일기 제목</td>
      <td>
        <input type="text" size="100" name="B_TITLE" value="<%=board.getB_TITLE()%>">
      </td>
   </tr>
   
   <tr>
      <td>일기 내용</td>
      <td>
         <pre><textarea rows="50" cols="100" name="B_CONTENT" style="resize:none"><%=board.getB_CONTENT() %></textarea></pre>
      </td>
   </tr>
  
</table>
<br><br><br><br>   
<div class="f" align="right">
	<a></a>
	<a href="/BoardMainAction.bo"><img id="back" class="img2" width="50" height="45" src="../images/back1.png"></a>   
	<a href="javascript:modifyform.submit();"><img id="ok" class="img1" width="45" height="45" src="../images/ok1.png"></a>
</div>

</form>
</body>
</html>