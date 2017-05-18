<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="error.jsp"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@ page import="DB.*"%>
<%@ page import="Model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Karol Hotar  http://livebooster.com | info@livebooster.com">
<meta name="keywords" content="webdesign, design, ux, ui, html, css, jquery, website">
<meta name="description" content="Karol Hotár creative Web designer">
<meta name="googlebot" content="all">
<meta name="robots" content="index, follow">

<!-- Page Title -->
<title>Moods and Genres</title>
<!--================================================= 
        Bootstrap core CSS
       ==================================================-->
<link rel="stylesheet" href="bootstrap.css">
<!--=================================================  
        Style CSS
        ==================================================-->
<link href="style.css" rel="stylesheet">
<link href="font-awesome.min.css" rel="stylesheet">
<!--=================================================  
        Google Fonts
         ==================================================-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,600,300' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Roboto:400,100,700subset=latin,cyrillic' rel='stylesheet' type='text/css'>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    
    <link href="flatnav.css" rel="stylesheet">
  <link href="flatnav2.css" rel="stylesheet">
		<meta name="robots" content="noindex,follow" />
    
</head>
<body>

<% HttpSession sessionn = request.getSession();%>
<%String username = (String)sessionn.getAttribute("username"); %>


<body background = "bg.png"/>
<div id="slide-menu">
<ul class="navigation">
<li><a href="user.jsp">Profile</a></li>
<li><a href="genres.jsp">Browse</a></li>
<li><a href="#">My Playlists</a></li>
<li><a href="#">Users</a></li>
</ul>
</div>


<div class="container">
</div>

<ul class="nav">
		<li id="settings">
			<a href="#"><img src="settings.png" /></a>
		</li>
		<li id="search">
			<form action="search.jsp" method="get">
				<input type="text" name="search_text" id="search_text" placeholder="Search"/>
				<input type="button" name="search_button" id="search_button"></a>
			</form>
		</li>
		<li id="options">
			<a href="#"><%=username%></a>
			<ul class="subnav">
				<li><a href="#">Update Profile</a></li>
				<li><a href="#">Log out</a></li>
			</ul>
		</li>
	</ul>

<div class="container">
<div class = "row">
<p> </p>
</div>
</div>

<h2> Albums</h2>

<ul class="nav2">
		<li>
			
		</li>
		</ul>
		
<div class="spinner"></div>
<div class="bg-content">
  <div id="content">
    <div class="container">
      <div class="row">
        <div class="clear"></div>
        <ul class="portfolio clearfix">
        <% int i=10; %>
        <% String genreId =request.getParameter("Id");%>
        <%	int j = Integer.parseInt(genreId);%>
        <% for(Album album : AlbumDAO.getInstance().getGenreAlbums(j)) { %>
        <% String picture = "img/"+(i++)+".jpg"; %>
        <li class="box"><h5><%= "&nbsp&nbsp&nbsp&nbsp"+album.getTitle()   
        %></h5>
         <a href=<%= "\"songs.jsp?Id1="+album.getId()+"\""%> class="magnifier"><img alt="" width=260px; height = 250px; src="<%=picture %>" ></a></li>
		<% }%>     
        </ul>
      </div>
    </div>
  </div>
</div>

<script src="js/bootstrap.js"></script>
<script src="prefixfree-1.0.7.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
				$(document).ready(function () {
						var $navigacia = $('body, #slide-menu'),
								val = $navigacia.css('left') === '250px' ? '0px' : '250px';
						$navigacia.animate({
								left: val
						}, 300)
		
				});

	   </script> 
</body>
</html>