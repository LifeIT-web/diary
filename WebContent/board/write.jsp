<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<% String id = request.getParameter("B_ID");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write Page</title>
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
	
	$("#cancel").click(function() {
		$(this).attr('src', '../images/cancel2.png');
	});
});
</script>
</head>
<body leftmargin="0">
<form action="/BoardWriteAction.bo" method = "post" name = "writeform" enctype="multipart/form-data">
<input type="hidden" name="B_ID" value="<%=id %>" />
<div class = "h">오늘을 쓰다</div>
<br><br><br>
<table class="all" align="center"> 
   <tr>
      <td>일기 제목</td>
      <td>
         <input type = "text" size="80" name="B_TITLE">
      </td>
   </tr>
   
   <tr>
      <td>일기 내용</td>
      <td>
        <pre><textarea rows="50" cols="80" name="B_CONTENT" style="resize:none"></textarea></pre>
      </td>
   </tr>
   <tr>
   	  <td>사진/그림</td>
      <td colspan = "2" align = "center">
            <input type="file" name = "B_FILE" >
      </td>
   </tr>   
</table>
<br><br><br><br>   
<div class="f" align="right">
	<a href = "/BoardMainAction.bo"><img id="cancel" class="img2" width="47" height="45" src="../images/cancel1.png"></a>   
	<a href = "javascript:writeform.submit()"><img id="ok" class="img1" width="45" height="45" src="../images/ok1.png"></a>
</div>
</form>
</body>
</html>