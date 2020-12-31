<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Page</title>
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
input:focus { outline:none;}
</style>

</head>
<body link="#CFCFCF" vlink="#CFCFCF" alink="#CFCFCF">

<h3 style="margin-left:550px" class="font1">Join Page</h3>
<form name="joinform" action="/MemberJoinAction.me" method="post">
<table style="margin-left:470px;">
   <tr class="font1">
      <td align="left">*ID : </td>
      <td><input class="font2" type="text" name="M_ID" id="M_ID" required/></td>
   </tr>
   <tr class="font1">
      <td align="left">*PASSWORD : </td>
      <td><input class="font2" type="password" name="M_PW" id="M_PW" required/></td>
   </tr>
   <tr class="font1">
      <td align="left">*NAME : </td>
      <td><input class="font2" type="text" name="M_NAME" id="M_NAME" required/></td>
   </tr>
   <tr class="font1">
      <td align="left">&nbsp;AGE : </td>
      <td><input class="font2" type="text" name="M_AGE" id="M_AGE"/></td>
   </tr>
   <tr class="font1">
      <td align="left">&nbsp;GENDER : </td>
      <td><input class="font2" type="radio" name="M_GENDER" id="M_GENDER" value="남" checked/>남
      <input class="font2" type="radio" name="M_GENDER" id="M_GENDER" value="여" checked/>여</td>
   </tr>
   <tr class="font1">
      <td align="left">&nbsp;EMAIL : </td>
      <td><input class="font2" type="email" name="M_EMAIL" id="M_EMAIL"/></td>
   </tr>
   <tr class="font1" style="font-size:20pt">
      <td align="right" colspan="2">(*은 필수 입력란입니다)</td>
   </tr>
   <tr><td><br></td></tr>
   <tr>
      <td colspan="2" align="center">
         <a class="font1" href="javascript:joinform.submit()">Join</a>&emsp;
         <a class="font1" href="./index.jsp">Main</a>
      </td>
   </tr>
</table>
</form>
</body>
</html>