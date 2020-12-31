<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style>
body {
   background-position:350px 10px;
   background-image:url('../images/bodyImage2.png');
   background-size:1000px;
   background-repeat:no-repeat;
   background-color:#FFF2E6;
   margin-left:50px;
   margin-top:150px;
}

.font1 {
   font-family:tvN 즐거운이야기;
   font-size:25pt;
}

.font2 {
   font-family:DX경필명조B;
   font-size:15pt;
}

input[type=text], input[type=password], input[type=email]{
   border:2px solid #FF7E7E;
   width:150px;
}

input:focus {  outline:none;}
</style>

</head>
<body link="#CFCFCF" vlink="#CFCFCF" alink="#CFCFCF">

<h3 style="margin-left:550px" class="font1">Login Page</h3>
<form name="loginform" action="/MemberLoginAction.me" method="post">
<table style="margin-left:470px;">
   <tr class="font1">
      <td align="left">ID : </td>
      <td><input class="font2" type="text" name="M_ID" id="M_ID"></td>
   </tr>
   <tr class="font1">
      <td class="font1">PASSWORD : </td>
      <td><input class="font2" type="password" name="M_PW" id="M_PW"></td>
   </tr>
   <tr><td><br></td></tr>
   <tr>
      <td colspan="2" align="center">
         <a class="font1" href="javascript:loginform.submit()">Login</a>&emsp;
         <a class="font1" href="./index.jsp">Main</a>
      </td>
   </tr>
</table>
</form>
</body>
</html>