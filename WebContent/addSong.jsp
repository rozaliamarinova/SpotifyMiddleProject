<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "DB.*" %>
    <%@ page import = "Model.*" %> 
    <%@ page import = "java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<% HttpSession sessionn = request.getSession();%>
<%String username = (String)sessionn.getAttribute("username"); %>

      	<% String songId =request.getParameter("songId");%>
       	<% String playlistId =request.getParameter("playlistId");%>
       	<% String albumId =request.getParameter("albumId");%>
        <%	int j = Integer.parseInt(songId);%>
         <%	int f = Integer.parseInt(songId);%>
        <%	int g= Integer.parseInt(playlistId);%>
        
        <%if(PlayListDAO.getInstance().existsInPlaylist(j, g)){ %>
     	 	<%PlayListDAO.getInstance().addSongInPlayList(g, j); %>
      		<jsp:forward page = "user.jsp">
      		<jsp:param value="1" name="1"/>
      		</jsp:forward>
      		<%} else{ %>
      		<jsp:forward page = "user.jsp">
      		<jsp:param value="1" name="1"/>
      		</jsp:forward>
<%} %>
      

</body>
</html>