<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.awt.Graphics2D" %>
<%@ page import="java.awt.image.renderable.ParameterBlock" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.media.jai.JAI" %>
<%@ page import="javax.media.jai.RenderedOp" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@page import="d.board.db.*" %> 
<%
	BoardBean board = (BoardBean)request.getAttribute("board");

%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Page</title>
<style>
td {
   color:#444F59;
}


a:hover {
   color:#444F59;
}


a { text-decoration:none;
	color:#CFCFCF;
	font-family:tvN 즐거운이야기;
	font-size:25pt;
}

.table1 {
   width:60%;
   height:80%;
   border:2px solid #FF7E7E;
}

.h {
   width : 100%;
   height : 8%;
   top :0;
   position :fixed;
   background-color :#FFB4B4;
   left:0;
   font-family: tvN 즐거운이야기;
   font-size: 30pt;
   color:white;
}

#view{
		font-family: DX경필명조B;
   font-size: 15pt;
}

.fix {
   position : fixed;
   width : 100%;
   bottom : 0%;
   left:0;
   background-color : #FFF2E6;
}

.bottom {
	margin-bottom:0.7%;
}

img {
	cursor:pointer;
}
</style>

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">

$(function() {
	$("#good").click(function() {
			$(this).attr('src', '../images/good2.png');
	});
	
	$("#back").click(function() {
			$(this).attr('src', '../images/back2.png');
	});
});
</script>
<script>
function deleteCheck() {
	
	if (confirm('정말 삭제하시겠습니까?')) {
		return true;
	}else {
		return false;
	}
}

</script>
</head>
<body>
<form action="" method="">
<div align="center" class = "h"><%=board.getB_TITLE() %></div>
<br><br><br>

<table class="table1" align="center">
	<tr>
		<td height="50px">
		</td>
	</tr>
		<% if(board.getB_FILE() !=null)  {%>
	<tr>
		<td align="center" height="500px">
			<%
				ServletContext context = request.getServletContext();
			String imagePath = context.getRealPath("boardupload");
			int size = 1*1024*1024;
			String B_FILE = board.getB_FILE();
			
			ParameterBlock pb = new ParameterBlock();
			pb.add(imagePath+"/"+B_FILE);
			RenderedOp rOp = JAI.create("fileload",pb);
			
			
			BufferedImage bi = rOp.getAsBufferedImage();
			BufferedImage thumb = new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g = thumb.createGraphics();
			g.drawImage(bi, 0, 0, 400,400,null);
			
			File file = new File(imagePath + "/sm_" + B_FILE);
			ImageIO.write(thumb, "jpg", file);
			
			
			%>
			<img src="./boardupload//sm_<%=B_FILE%>">
		</td>
	</tr>
	<% } %>
	<tr>
		<td id="view" height="500px" align="center">
			<%=board.getB_CONTENT() %>
		</td>
	</tr>
</table>
<table align="center">
	<tr>
		<td>
			<a href="/BoardModifyView.bo?num=<%=board.getB_NUM()%>">[수정하기]</a>
			<a href="/BoardDeleteAction.bo?num=<%=board.getB_NUM()%>" onclick="return deleteCheck();">[삭제하기]</a>
		</td>
	</tr>
</table>
<br><br><br>

<footer>
<div class="fix" align ="left">

	<a href="/BoardMainAction.bo">메인화면으로</a>
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;
	<a href="javascript:history.go(-1);">
		<img class="bottom" id="back" style="padding-right:0.5%" width="45" height="45" src="../images/back1.png">
	</a>
	<a href="./BoardGoodAction.bo?num=<%=board.getB_NUM()%>">
		<img id="good" style="padding-right:3%" width="60" height="75" src="../images/good1.png">
	</a>      
</div>
</footer>   
</form>
</body>
</html>