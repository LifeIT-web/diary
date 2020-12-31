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


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" 
integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" 
crossorigin="anonymous">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>

<style>
a { text-decoration:none;
   color:#BDBDBD;
   font-family:tvN 즐거운이야기;
   font-size:25pt;
}

a:hover {
   color:#FF7E7E;
   text-decoration:none;
}

.font1 {
   font-family:tvN 즐거운이야기;
   font-size:27pt;
   font-weight:bold;
}
.font3 {
   font-family:tvN 즐거운이야기;
   font-size:18pt;
   font-weight:bold;
}

.font2 {
   font-family:DX경필명조B;
   font-size:15pt;
}

.header {
   background-color:#FFF2E6;
}

.border {
   margin-left:65px;
   padding:10%;
   background-color:#FFEEE4;
   width:300px;
   height:450px;
}

.text {
   color:#CFCFCF;
   font-size:10pt;
   padding-left:10%;
}

.thfma {
			width:70%;
			height:70%;
}

</style>

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
$(function() {
   $("#write").mouseover(function() {
      $(this).attr('src', '../images/write2.png');
   });
   
   $("#mylist").mouseover(function() {
      $(this).attr('src', '../images/mylist2.png');
   });

   $("#search").mouseover(function() {
      $(this).attr('src', '../images/search2.png');
   });

   $("#write").mouseout(function() {
      $(this).attr('src', '../images/write1.png');
   });
   
   $("#mylist").mouseout(function() {
      $(this).attr('src', '../images/mylist1.png');
   });

   $("#search").mouseout(function() {
      $(this).attr('src', '../images/search1.png');
   });
});
</script>

</head>
<body link="#CFCFCF" vlink="#CFCFCF" alink="#CFCFCF">
<table style="width:100%;">
   <tr align="right">
      <td>
         <a href="/MemberLogoutAction.me">로그아웃</a>
      </td>
   </tr>
</table>
<table class="header" style="width:100%;">
   <tr>
      <td align="left">
         <font class="font1" style="color:#FF7E7E; font-size:35pt; margin-left:3%">오늘을 쓰다</font>
      </td>
      <td align="right">
         <a href="/BoardWriteView.bo"><img id="write" src="../images/write1.png" width="40px" height="40px"></a>&emsp;&emsp;
         <a href="/BoardMyListAction.bo"><img id="mylist" src="../images/mylist1.png" width="35px" height="40px"></a>&emsp;&emsp;
         <a href="검색하기 컨트롤러"><img id="search" src="../images/search1.png" width="40px" height="45px"></a>   &emsp;&emsp;
      </td>   
   </tr>
</table>

<br><br><br>

<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" align="center">
   <ol class="carousel-indicators">
       <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
       <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
       <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
   </ol>
   <div class="carousel-inner" width="100%" style="background-color:#CCCCCC50;">
   <%
	
	List goodlist = (List)request.getAttribute("goodlist");
   
   
  	 BoardBean board1 = (BoardBean) goodlist.get(0);
  	 BoardBean board2 = (BoardBean) goodlist.get(1);
  	 BoardBean board3 = (BoardBean) goodlist.get(2);
  	 
		String B_FILE1 = board1.getB_FILE();
		String B_FILE2 = board2.getB_FILE();
		String B_FILE3 = board3.getB_FILE();
   	
%>
      <div class="carousel-item active" width="100%">
   			<%
   			ServletContext context1 = request.getServletContext();
			String imagePath1 = context1.getRealPath("boardupload");
			int size1 = 1*1024*1024;
			
			ParameterBlock pb1 = new ParameterBlock();
			pb1.add(imagePath1+"/"+B_FILE1);
			RenderedOp rOp1 = JAI.create("fileload",pb1);
			
			
			BufferedImage bi1 = rOp1.getAsBufferedImage();
			BufferedImage thumb1 = new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g1 = thumb1.createGraphics();
			g1.drawImage(bi1, 0, 0, 400,400,null);
			
			File file1 = new File(imagePath1 + "/sm_" + B_FILE1);
			ImageIO.write(thumb1, "jpg", file1);
   			%>
   			<img class="thfma" src="./boardupload//sm_<%=B_FILE1%>" width="100" height="100">
     		
     		<div class="carousel-caption d-none d-md-block">
        	<h5 class="font1"><%=board1.getB_ID() %></h5>
        	<p class="font1"><%=board1.getB_TITLE() %></p>
      		</div>
     		
     
      </div>
      <div class="carousel-item" width="100%">
         	<%
   			ServletContext context2 = request.getServletContext();
			String imagePath2 = context2.getRealPath("boardupload");
			int size2 = 1*1024*1024;
			
			ParameterBlock pb2 = new ParameterBlock();
			pb2.add(imagePath2+"/"+B_FILE2);
			RenderedOp rOp2 = JAI.create("fileload",pb2);
			
			
			BufferedImage bi2 = rOp2.getAsBufferedImage();
			BufferedImage thumb2 = new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g2 = thumb2.createGraphics();
			g2.drawImage(bi2, 0, 0, 400,400,null);
			
			File file2 = new File(imagePath2 + "/sm_" + B_FILE2);
			ImageIO.write(thumb2, "jpg", file2);
   			%>
   			<img class="thfma" src="./boardupload//sm_<%=B_FILE2%>" width="100" height="100">
      	
     		<div class="carousel-caption d-none d-md-block">
        	<h5  class="font1"><%=board2.getB_ID() %></h5>
        	<p class="font1"><%=board2.getB_TITLE() %></p>
      		</div>      		
      		
      </div>
      <div class="carousel-item" width="100%">
            	<%
   			ServletContext context3 = request.getServletContext();
			String imagePath3 = context3.getRealPath("boardupload");
			int size3 = 1*1024*1024;
			
			ParameterBlock pb3 = new ParameterBlock();
			pb3.add(imagePath3+"/"+B_FILE3);
			RenderedOp rOp3 = JAI.create("fileload",pb3);
			
			
			BufferedImage bi3 = rOp2.getAsBufferedImage();
			BufferedImage thumb3 = new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g3 = thumb3.createGraphics();
			g3.drawImage(bi3, 0, 0, 400,400,null);
			
			File file3 = new File(imagePath3 + "/sm_" + B_FILE3);
			ImageIO.write(thumb3, "jpg", file3);
   			%>
   			<img class="thfma" src="./boardupload//sm_<%=B_FILE3%>" width="100" height="100">
      
     		<div class="carousel-caption d-none d-md-block">
        	<h5 class="font1"><%=board3.getB_ID() %></h5>
        	<p class="font1"><%=board3.getB_TITLE() %></p>
      		</div>
      </div>
   </div> 
   <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
   </a>
   <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
   </a>
</div>
<%
	BoardDAO boarddao = new BoardDAO();
	List list = (List)request.getAttribute("boardlist");
%>
<br><br><br><br><br><br><br>
<table>
	<tr>
      <td class="font1" style="color:#444F59">&emsp;&emsp;&nbsp;&nbsp;최신글</td>
   </tr>
   <tr>
   <%	
   		for(int i =0; i<list.size(); i++) { 
			
	   BoardBean board = (BoardBean) list.get(i);
			String B_FILE = board.getB_FILE();
   %>
      <td>
         <div class="border font2">
            	<table>
            		<tr>
            			<td><font class="font3">작성자:</font> <%=board.getB_ID() %></td>
            		</tr>
            		<tr>
            			<td><font class="font3" >제목:</font> <%=board.getB_TITLE() %></td>
            		</tr>
            		<% if(B_FILE!=null) { %>    
            		<tr><td height="30"></td></tr>        		
            		<tr>
            		<td>
            		<div style="border:2px solid white; width:130; padding:10px;">
            			<%
				ServletContext context = request.getServletContext();
			String imagePath = context.getRealPath("boardupload");
			int size = 1*1024*1024;
			
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
			<img src="./boardupload//sm_<%=B_FILE%>" width="100" height="100">
            		</div>
            		</td>
            		</tr>
            				<tr><td height="30"></td></tr>
            		<% }  %>
            		<tr>
            			<td><%=board.getB_CONTENT() %></td>
            		</tr>
            		
            	</table>
         </div>
            	<div valign="bottom" align="right" height="300px">
            	<a href="/BoardDetailAction.bo?num=<%=board.getB_NUM()%>">보기</a>
         		</div>
      </td>
      <% } %>
   </tr>
</table>
<br><br>

<table style="width:100%;">
   <tr>
      <td class="font1" align="right"> 
         <a href="/BoardAllListAction.bo">[전체 글 보기]&emsp;&nbsp;</a>
      </td>      
   </tr>
</table>
<br><br><br><br>
<br><br><br><br>

<h4 align="center" class="font1" style="color:#444F59">다양한 다이어리를 구경해보세요!</h4>
<div width="100%" height="100px" id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
   <ol class="carousel-indicators">
       <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
       <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
       <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
   </ol>
   <div class="carousel-inner">
      <div class="carousel-item active" align="center">
         <img width="80%" height="350px" src="../images/diary1.png">
      </div>
      <div class="carousel-item" align="center">
         <img height="350px" src="../images/diary4.jpg">
         <img height="350px" src="../images/diary5.jpg">
      </div>
      <div align="center" class="carousel-item">
         <img height="350px" src="../images/diary.jpg">
         <img height="350px" src="../images/diary3.jpg">
      </div>
   </div>
</div>
<br><br><br>
<footer>
   <div class="text">
      오늘을 쓰다 (주)저널스 /대표이사 조혜인 /사업자 등록번호 : 2179-08-5258<br>
      본사 : 서울시 강동구 천호대로 1095 현대코아 3층<br>
      Tel : 02-3345-7927 / 대표 Email : jojo@naver.com<br>
      조혜인 배지현 이명희 이지윤 엄재성<br>
      Copyright@ 1999 (주)저널스 All Rights Reserved.
      <img style="margin-right:10%" align="right" src="../images/sns.JPG">
   </div>
</footer>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>