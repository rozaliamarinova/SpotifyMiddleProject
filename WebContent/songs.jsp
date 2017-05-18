<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="error.jsp"%>
    <%@page import = "DB.*" %>
     <%@page import = "Model.*" %>
     <%@page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Karol Hotar  http://livebooster.com | info@livebooster.com">
<meta name="keywords" content="webdesign, design, ux, ui, html, css, jquery, website">
<meta name="description" content="Karol Hotár creative Web designer">
<meta name="googlebot" content="all">
<meta name="robots" content="index, follow">
<!-- Page Title -->
<title>jQuery Off-canvas Navigation Example</title>
<!--================================================= 
        Bootstrap core CSS
       ==================================================-->
<link rel="stylesheet" href="bootstrap.css">
<!--=================================================  
        Style CSS
        ==================================================-->
<link href="style.css" rel="stylesheet">
<link href="font-awesome.min.css" rel="stylesheet">
<link href="csss.css" rel="stylesheet">
<link href="button.css" rel="stylesheet">
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

 <% String albumId =request.getParameter("Id1");%>
 <%int j = Integer.parseInt(albumId);%>
 <% Album a = null; %>
 
 <%for(Integer x : AlbumDAO.getInstance().getAllGenres().keySet()){%>
 <%for(Album album : AlbumDAO.getInstance().getGenreAlbums(x)) { %>
 <%if(album.getId()==j){ %>
 <%a = album;%>
 <%}}} %>
 
 
 <div class="bg-content">
  <div id="content">
    <div class="container">
   
      <div class="row">
        <div class="clear"></div>
        <ul class="portfolio clearfix">
        <% int c=10; %>
        <% String genreId =request.getParameter("Id");%>
     
        <li class="box">
         <a href=<%= "\"songs.jsp?Id1="+a.getId()+"\""%> class="magnifier"><img alt="" ></a></li>
              
        </ul>
      </div>
    </div>
  </div>
</div>

<div class='center-container'>
  <div class='center'>
    <div id='ui'>
  
       <%User u = UserDAO.getInstance().getUser(username); %>  
      <%List<Playlist> pl = PlayListDAO.getInstance().getUserPlaylists(u.getId());%>
    
      <header>
        <h1><%=a.getArtist() + " - "+ " ' " + a.getTitle()+ " ' "%></h1>
      </header>
      <main>
        <div class='split-l'></div>
        <div class='split-l'></div>
        <div id='songs'>
          <div class='song-set'>
          <% int i = 1; %>
          <%for(Song song : SongDAO.getInstance().getAllSongsFromAlbum(j)) { %>
          <%int playlistId = 0; %>
          <%if(pl!=null && pl.size()>0){ %>
		<% playlistId = pl.get(0).getPlaylistId();%>
		<%} %>
		<form action = <%= "\"addSong.jsp?playlistId="+playlistId+"&songId="+song.getId()+"&albumId="+a.getId()+"\""%> method = "post">
		<%int songId = song.getId(); %>	
       <select name="playlistName" >    
       </select>
     <input type="submit" name="playlistName" value="+"/>
    </form>		
          	 <div class='song'>
              <audio src='<%=i++%>.mp3'></audio>
              <img src='musicc.png' width=46px; height=40px;>
              <div class='details'>
                <div class='name'><%= song.getTitle() %></div>
                <div class='producer'><%= song.getArtist() %></div>
              </div>
              <div class='play'>
                <i class='material-icons'>play_arrow</i>
              </div>
              <div class='controlls'>
                <div class='pos'>0:00</div>
                <i class='material-icons prv'>fast_rewind</i>
                <i class='material-icons nxt'>fast_forward</i>
                <div class='length'>0:00</div>  
              </div>
             
            </div>
             <div class='split-l'></div>
            <%} %>
            </div>
          </div>
      </main>
    </div>
  </div>
</div>



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
<script type="text/javascript" src="jquery-1.8.3.js"></script>
<script type="text/javascript">

var selectedGenre = "hardstyle";
var isPlaying = 0;

$(".play").click(function(e)
{
	if ($(e.target).parent().hasClass("playing"))
	{
		$(".song").find("audio").trigger("pause");
		isPlaying = 0;
		$(".song").removeClass("playing");
		$(".play").find("i").html("play_arrow");
		
		e.stopPropagation();
	}
});
$(".play i").click(function(e)
{
	if ($(e.target).parent().parent().hasClass("playing"))
	{
		$(".song").find("audio").trigger("pause");
		isPlaying = 0;
		$(".song").removeClass("playing");
		$(".play").find("i").html("play_arrow");
		
		e.stopPropagation();
	}
});

$(".song").click(function(e)
{
	var songCard = e.target;
	while (!$(songCard).hasClass("song")) songCard = $(songCard).parent();
	
	if (!$(songCard).hasClass("playing"))
	{
		$(".song").find("audio").trigger("pause");
		$(".song").removeClass("playing");
		$(".play").find("i").html("play_arrow");
		
		var del = 300 * isPlaying;
		
		var len = '' + Math.floor($(songCard).find("audio")[0].duration / 60) + ':';
		if (Math.ceil($(songCard).find("audio")[0].duration % 60) < 10)
			len = len + '0' + Math.ceil($(songCard).find("audio")[0].duration % 60);
		else
			len = len + Math.ceil($(songCard).find("audio")[0].duration % 60);
		
		$(songCard).find(".length").html(len);
		
		setTimeout(function()
		{
			$(songCard).find("audio").trigger("play");
			isPlaying = 1;
			$(songCard).addClass("playing").delay(1000);
			$(songCard).find(".play").find("i").html("pause");
		}, del);
	}
	
	e.stopPropagation();
});

$(".prv").click(function(e)
{
	var songCard = e.target;
	while (!$(songCard).hasClass("song")) songCard = $(songCard).parent();
	
	$(songCard).find("audio").prop("currentTime", 0);
	
	$(songCard).find(".pos").html(Math.floor($(songCard).find("audio").prop("currentTime") / 60) + ":" + Math.ceil($(songCard).find("audio").prop("currentTime") % 60));
	
	e.stopPropagation();
});

$(".nxt").click(function(e)
{
	var songCard = e.target;
	while (!$(songCard).hasClass("song")) songCard = $(songCard).parent();
	
	$(songCard).find("audio").prop("currentTime", $(songCard).find("audio").prop("currentTime") + 10);
	
	$(songCard).find(".pos").html(Math.floor($(songCard).find("audio").prop("currentTime") / 60) + ":" + Math.ceil($(songCard).find("audio").prop("currentTime") % 60));
	
	e.stopPropagation();
});

function selectGenre(idNow, now)
{
	$(".song").find("audio").trigger("pause");
	$(".song").removeClass("playing");
	$(".play").find("i").html("play_arrow");
	
	var del = 300 * isPlaying;
	
	setTimeout(function()
	{
		$("#songs").css("left", 600 * now * -1 + "px");
		
		$("#" + idNow).css("left", "0px");
		$("#" + idNow).css("opacity", "1");
	}, del);
	
	isPlaying = 0;
	
	selectedGenre = idNow;
}

$(".genre").click(function(e)
{
	$(".genre").removeClass("selected");
	$(e.target).addClass("selected");
	
	selectGenre($(e.target).html().toLowerCase().replace(" ", "-"), $(e.target).index());
});

$(document).ready
{
	selectGenre("hardstyle", 0);
	
	setInterval(function()
	{
		$(".playing").each(function()
		{
			var pos = '' + Math.floor($(this).find("audio").prop("currentTime") / 60) + ':';
			if (Math.ceil($(this).find("audio").prop("currentTime") % 60) < 10)
				pos = pos + '0' + Math.ceil($(this).find("audio").prop("currentTime") % 60);
			else
				pos = pos + Math.ceil($(this).find("audio").prop("currentTime") % 60);
			
			$(this).find(".pos").html(pos);
			
			if ($(this).find("audio").prop("currentTime") >= $(this).find("audio")[0].duration)
			{
				$(".song").find("audio").trigger("pause");
				$(".song").removeClass("playing");
				$(".play").find("i").html("play_arrow");
			}
		})
	}, 1000);
}

</script>

</body>
</html>
