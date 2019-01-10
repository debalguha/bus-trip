<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!-- Title and other stuffs -->
<title>Forms - MacAdmin</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">


<!-- Stylesheets -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<!-- Font awesome icon -->
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<!-- jQuery UI -->
<link rel="stylesheet" href="resources/css/jquery-ui.css">
<!-- Calendar -->
<link rel="stylesheet" href="resources/css/fullcalendar.css">
<!-- prettyPhoto -->
<link rel="stylesheet" href="resources/css/prettyPhoto.css">
<!-- Star rating -->
<link rel="stylesheet" href="resources/css/rateit.css">
<!-- Date picker -->
<link rel="stylesheet"
	href="resources/css/bootstrap-datetimepicker.min.css">
<!-- CLEditor -->
<link rel="stylesheet" href="resources/css/jquery.cleditor.css">
<!-- Data tables -->
<link rel="stylesheet" href="resources/css/jquery.dataTables.css">
<!-- Bootstrap toggle -->
<link rel="stylesheet" href="resources/css/jquery.onoff.css">
<!-- Main stylesheet -->
<link href="resources/css/style.css" rel="stylesheet">
<!-- Widgets stylesheet -->
<link href="resources/css/widgets.css" rel="stylesheet">
<!-- date Time picker -->
<link rel="stylesheet" href="resources/css/jquery.datetimepicker.css">

<link rel="stylesheet" href="resources/jqx/styles/jqx.bootstrap.css">
<link rel="stylesheet" href="resources/jqx/styles/jqx.base.css">

<script src="resources/js/respond.min.js"></script>
<script src="resources/js/jquery-DateFormat.min.js"></script>
<!--[if lt IE 9]>
  <script src="resources/js/html5shiv.js"></script>
  <![endif]-->

<!-- Favicon -->
<link rel="shortcut icon" href="resources/img/favicon/favicon.png">
</head>

<body class="bootstrap">



	<!-- Header starts -->
	<header>
		<div class="container">
			<div class="row">

				<!-- Logo section -->
				<div class="col-md-4">
					<!-- Logo. -->
					<div class="logo">
						<h1>
							<a href="#">Event<span class="bold">Dashboard</span></a>
						</h1>
						<p class="meta">List events, Create events, Update
							Events...all in one place!!</p>
					</div>
					<!-- Logo ends -->
				</div>

				<!-- Button section -->
				<div class="col-md-4">

					<!-- Buttons -->
					<ul class="nav nav-pills">

						<!-- Comment button with number of latest comments count -->
						<li class="dropdown dropdown-big"><a class="dropdown-toggle"
							href="#" data-toggle="dropdown"> <i class="fa fa-comments"></i>
								Chats..coming up <span class="label label-info">6</span>
						</a></li>

						<!-- Message button with number of latest messages count-->
						<li class="dropdown dropdown-big"><a class="dropdown-toggle"
							href="#" data-toggle="dropdown"> <i class="fa fa-envelope"></i>
								Inbox..coming up <span class="label label-primary">6</span>
						</a></li>

					</ul>

				</div>

				<!-- Data section -->

				<div class="col-md-4">
					<div class="header-data">

						<!-- Traffic data -->
						<div class="hdata">
							<div class="mcol-left">
								<!-- Icon with red background -->
								<i class="fa fa-signal bred"></i>
							</div>
							<div class="mcol-right">
								<!-- Number of visitors -->
								<p>
									<a href="#">7000</a> <em>visits</em>
								</p>
							</div>
							<div class="clearfix"></div>
						</div>

						<!-- Members data -->
						<div class="hdata">
							<div class="mcol-left">
								<!-- Icon with blue background -->
								<i class="fa fa-user bblue"></i>
							</div>
							<div class="mcol-right">
								<!-- Number of visitors -->
								<p>
									<a href="#">3000</a> <em>users</em>
								</p>
							</div>
							<div class="clearfix"></div>
						</div>

						<!-- revenue data -->
						<div class="hdata">
							<div class="mcol-left">
								<!-- Icon with green background -->
								<i class="fa fa-money bgreen"></i>
							</div>
							<div class="mcol-right">
								<!-- Number of visitors -->
								<p>
									<a href="#">5000</a><em>orders</em>
								</p>
							</div>
							<div class="clearfix"></div>
						</div>

					</div>
				</div>

			</div>
		</div>
	</header>

	<!-- Header ends -->

	<!-- Main content starts -->

	<div class="content">

		<!-- Sidebar -->
		<div class="sidebar">
			<div class="sidebar-dropdown">
				<a href="#">Navigation</a>
			</div>

			<!--- Sidebar navigation -->
			<!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
			<ul id="nav">
				<li class="has_sub"><a id="eventHome" href="#"><i class="fa fa-list-alt"></i>
						List Events </a>
				</li>
				<li class="has_sub"><a id="eventEditor" href="#"><i class="fa fa-file-o"></i>
						Create Event</a>
				</li>
				<li class="has_sub"><a id="eventPast" href="#"><i class="fa fa-file-o"></i>
						Past Events</a>
				</li>				
			</ul>
		</div>

		<!-- Sidebar ends -->

		<!-- Main bar -->
		<div class="mainbar">

			<!-- Matter -->

			<div class="matter">
				<div class="container">

					<div class="row">

						<div class="col-md-12">

							<div class="widget">
							
				                <div class="widget-head">
				                  <div class="pull-left">Events</div>
				                  <div class="widget-icons pull-right">
				                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
				                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
				                  </div>  
				                  <div class="clearfix"></div>
				                </div>
								
							
								<div class="widget-content">
									<div class="padd">
										<div class="page-tables">
											<div class="table-responsive">
												<table id="data-table" style="font-size: 12px;line-height: 22px;border-top: 3px solid #eee;padding-top: 37px;-webkit-font-smoothing: antialiased;">
												<!-- <table class="table table-striped table-bordered table-hover" style="font-size: 12px;"> -->
													<thead>
														<tr>
															<th>Event Name</th>
															<th>Featured</th>
															<th>Image</th>
															<th>Event Location</th>
															<th>Event Date</th>
															<th>Expiration Date</th>
															<th>Price</th>
															<th></th>
															<th></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${events}" var="item">
															<tr>
																<td><c:out value="${item.title}"></c:out></td>
																<c:choose>
																	<c:when test="${item.featured eq 1}">
																		<td><input type="checkbox" id="inlineCheckbox1" value="option1" checked="checked"></td>
																	</c:when>
																	<c:otherwise>
																		<td><input type="checkbox" id="inlineCheckbox1" value="option1" checked="checked"></td>
																	</c:otherwise>
																</c:choose>
																<td><c:out value="${item.imageURL}"></c:out></td>
																<td><c:out value="${item.eventLocation}"></c:out></td>
																<td><fmt:formatDate pattern="yyyy-MMM-dd HH:mm" value="${item.eventDateTime}" /></td>
																<td><fmt:formatDate pattern="yyyy-MMM-dd HH:mm" value="${item.expiration}" /></td>
																<td><c:out value="${item.price}"></c:out></td>
																<td><button class="btn btn-sm btn-primary" id="editBtn" onclick="javascript:editEvent(<c:out value="${item.id}"></c:out>)" type="button" class="btn btn-sm btn-info">Edit</button></td>
																<td><button class="btn btn-sm btn-primary" id="cloneBtn" onclick="javascript:cloneEvent(<c:out value="${item.id}"></c:out>)" type="button" class="btn btn-sm btn-info">Clone</button></td>									
															</tr>
														</c:forEach>													
													</tbody>												
												</table>
											</div>
										</div>
									</div>
								</div>
								<div class="widget-foot">
									<!-- Footer goes here -->
								</div>
						
							</div>
						
						</div>

					</div>

				</div>

			</div>
		</div>
		<div class="clearfix"></div>

	</div>


	<!-- Content ends -->

	<!-- Footer starts -->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<!-- Copyright info -->
					<p class="copy">
						Copyright &copy; 2012 | <a href="#">Your Site</a>
					</p>
				</div>
			</div>
		</div>
	</footer>

	<!-- Footer ends -->

	<!-- Scroll to top -->
	<span class="totop"><a href="#"><i class="fa fa-chevron-up"></i></a></span>

	<!-- JS -->
	<script src="resources/js/jquery.js"></script>
	<!-- jQuery -->
	<script src="resources/js/bootstrap.min.js"></script>
	<!-- Bootstrap -->
	<script src="resources/js/jquery-ui.min.js"></script>
	<!-- jQuery UI -->
	<script src="resources/js/fullcalendar.min.js"></script>
	<!-- Full Google Calendar - Calendar -->
	<script src="resources/js/jquery.rateit.min.js"></script>
	<!-- RateIt - Star rating -->
	<script src="resources/js/jquery.prettyPhoto.js"></script>
	<!-- prettyPhoto -->
	<script src="resources/js/jquery.slimscroll.min.js"></script>
	<!-- jQuery Slim Scroll -->
	<script src="resources/js/jquery.dataTables.min.js"></script>
	<!-- Data tables -->

	<!-- jQuery Flot -->
	<script src="resources/js/excanvas.min.js"></script>
	<script src="resources/js/jquery.flot.js"></script>
	<script src="resources/js/jquery.flot.resize.js"></script>
	<script src="resources/js/jquery.flot.pie.js"></script>
	<script src="resources/js/jquery.flot.stack.js"></script>

	<!-- jQuery Notification - Noty -->
	<script src="resources/js/jquery.noty.js"></script>
	<!-- jQuery Notify -->
	<script src="resources/js/themes/default.js"></script>
	<!-- jQuery Notify -->
	<script src="resources/js/layouts/bottom.js"></script>
	<!-- jQuery Notify -->
	<script src="resources/js/layouts/topRight.js"></script>
	<!-- jQuery Notify -->
	<script src="resources/js/layouts/top.js"></script>
	<!-- jQuery Notify -->
	<!-- jQuery Notification ends -->

	<script src="resources/js/sparklines.js"></script>
	<!-- Sparklines -->
	<script src="resources/js/jquery.cleditor.min.js"></script>
	<!-- CLEditor -->
	<script src="resources/js/bootstrap-datetimepicker.min.js"></script>
	<!-- Date picker -->
	<script src="resources/js/jquery.onoff.min.js"></script>
	<!-- Bootstrap Toggle -->
	<script src="resources/js/filter.js"></script>
	<!-- Filter for support page -->
	<script src="resources/js/custom.js"></script>
	<!-- Custom codes -->
	<script src="resources/js/charts.js"></script>
	<!-- Charts & Graphs -->
	<script src="resources/js/jquery.datetimepicker.js"></script>
	<script src="resources/jqx/jqx-all.js"></script>
	<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.7.0/underscore.js"></script>
	<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/backbone.js/1.1.2/backbone.js"></script>
	<!-- Date time picker -->
	<script type="text/javascript">
		editEvent = function(eventId){
			window.location.href = 'http://'+window.location.host+'/bus-trip/forms.jsp?mode=EDIT&eventId='+eventId;
		}
		cloneEvent = function(eventId){
			window.location.href = 'http://'+window.location.host+'/bus-trip/forms.jsp?mode=CLONE&eventId='+eventId;
		}
		$(document).ready(function(){
			$('#eventHome').click(function(){
				window.location.href = 'http://'+window.location.host+'/bus-trip/index.jsp';
			});
			$('#eventEditor').click(function(){
				window.location.href = 'http://'+window.location.host+'/bus-trip/forms.jsp';
			});
			$('#eventPast').click(function(){
				window.location.href = 'http://'+window.location.host+'/bus-trip/index.jsp?isPast=true';
			});			
		}); 
	</script>
</body>
</html>