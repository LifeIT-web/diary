<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Logo Page</title>
<style>
body {
   background-position:250px 0px;
   background-image:url('../images/bodyImage1.png');
   background-size:1000px;
   background-repeat:no-repeat;
   background-color:#FFF2E6;
   margin:20%;
}

h3 {
   text-align:center;
}

font {
   font-size:100pt;
   color:#FFA7A7;
}

.font1 {
   font-family:tvN 즐거운이야기;
   font-size:25pt;
}
</style>
</head>
<body link="#CFCFCF" vlink="#CFCFCF" alink="#CFCFCF">

<form name="mainform" action="/MemberLoginView.me" method="post">

<h3 class="font1"><font>오늘을 쓰다</font></h3>
<table align="center" style="margin-top:100px">
   <tr>
      <td class="font1">
         <a href="javascript:mainform.submit()">Login</a>&emsp;
         <a href="/MemberJoinView.me">Join</a>
      </td>
   </tr>
</table>

</form>

</body>
</html>