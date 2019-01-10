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


							<div class="widget wgreen">

								<div class="widget-head">
									<div class="pull-left">Forms</div>
									<div class="widget-icons pull-right">
										<a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
										<a href="#" class="wclose"><i class="fa fa-times"></i></a>
									</div>
									<div class="clearfix"></div>
								</div>

								<div class="widget-content">
									<div class="padd">

										<br />
										<!-- Form starts.  -->
										<form id="commentForm" class="form-horizontal" role="form">
										
											<div class="form-group">
												<label class="col-lg-2 control-label">Price *</label>
												<div class="col-lg-2">
													<input type="text" class="form-control" placeholder="Price"
														name="price" id="price" required="required">
												</div>
											</div>										

											<div class="form-group">
												<label class="col-lg-2 control-label">Title *</label>
												<div class="col-lg-4">
													<input type="text" class="form-control" placeholder="Title"
														name="title" id="title" required="required">
												</div>
												<label class="col-lg-2 control-label"
													style="text-align: left;">Event Location *</label>
												<div class="col-lg-4">
													<input type="text" class="form-control"
														placeholder="Event Location" name="eventLocation" id="eventLocation" required="required">
												</div>
											</div>

											<div class="form-group">
												<label class="col-lg-2 control-label">Short
													Description</label>
												<div class="col-lg-4">
													<input type="text" class="form-control"
														placeholder="Short Description" name="shortDesc" id="shortDesc">
												</div>
												<label class="col-lg-2 control-label"
													style="text-align: left;">Image URL</label>
												<div class="col-lg-4">
													<input type="text" class="form-control"
														placeholder="Image URL" name="imageURL" id="imageURL">
												</div>
											</div>

											<div class="form-group">
												<label class="col-lg-2 control-label">Description *</label>
												<div class="col-lg-4">
													<textarea class="form-control" rows="5"
														placeholder="Description" name="description" id="description" required="required"></textarea>
												</div>
												<div class="col-lg-6">
													<div class="row">
														<label class="col-lg-5 control-label"
															style="text-align: left;">Hidden</label>
														<div class="col-lg-1">
															<label class="checkbox-inline"> <input
																name="hidden" type="checkbox" id="hidden" value="1">
															</label>
														</div>
													</div>
													<div class="row">
														<label class="col-lg-5 control-label"
															style="text-align: left;">Upsell/Upgrade Event</label>
														<div class="col-lg-1">
															<label class="checkbox-inline"> <input
																name="upsellOrUpgradeEvent" type="checkbox"
																id="upsellOrUpgradeEvent" value="1">
															</label>
														</div>
													</div>
													<div class="row">														
														<label class="col-lg-5 control-label"
															style="text-align: left;">Featured</label>
														<div class="col-lg-1">
															<label class="checkbox-inline"> <input
																name="featured" type="checkbox"
																id="featured" value="1">
															</label>
														</div>															
													</div>
													<div class="row">														
														<label class="col-lg-5 control-label"
															style="text-align: left;">Event Type *</label>
														<div class="col-lg-3">
															<select name="eventType" id="eventType" class="form-control" required="required">
						                                      <option>Bus</option>
						                                      <option>Pub</option>
						                                      <option>Sail</option>
						                                    </select>
														</div>															
													</div>													
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label">Line up</label>
												<div class="col-lg-4">
													<textarea class="form-control" rows="3"
														placeholder="Line up" name="lineup" id="lineup"></textarea>
												</div>
												<label class="col-lg-2 control-label"
													style="text-align: left;">For State</label>
												<div class="col-lg-3">
													<div class="radio">
														<label> <input type="radio" name="eventState"
															id="eventStateSF" value="SF" checked> San
															Francisco
														</label>
													</div>
													<div class="radio">
														<label> <input type="radio" name="eventState"
															id="eventStateNY" value="NY"> New York
														</label>
													</div>
												</div>
											</div>

											<div class="form-group">
												<label class="col-lg-2 control-label">Category *</label>
												<div class="col-lg-2">
													<select multiple class="form-control" name="eventcategory" id="eventcategory" required="required">
														<option value="Sports">Sports</option>
														<option value="Football">Football</option>
														<option value="Pro">Pro</option>
														<option value="College">College</option>
														<option value="Concert">Concert</option>
													</select>
												</div>
												<label class="col-lg-2 control-label"
													style="text-align: left;">Min Passenger *</label>
												<div class="col-lg-2">
													<input type="text" class="form-control" placeholder="Title"
														name="minPassenger" id="minPassenger" required="required">
												</div>
												<label class="col-lg-2 control-label"
													style="text-align: left;">Max Passenger</label>
												<div class="col-lg-2">
													<input type="text" class="form-control" placeholder="Title"
														name="maxPassenger" id="maxPassenger" required="required">
												</div>
											</div>

											<div class="form-group">
												<label class="col-lg-2 control-label">Event Date *</label>
												<div class="col-lg-4">
													<input id="eventDate" type="text" class="form-control"
														placeholder="Title" name="eventDate" id="eventDate" required="required">
												</div>
												<label class="col-lg-1 control-label"
													style="text-align: left;">Expiration *</label>
												<div class="col-lg-4">
													<input id="expirationDate" type="text" class="form-control"
														placeholder="Title" name="expirationDate" id="expirationDate" required="required">
												</div>
											</div>
											<div class="form-group">
												<div class="col-lg-offset-1 col-lg-10">
													<div>
														<div id='jqxWidget'>
															<div id="jqxgrid"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-lg-offset-2 col-lg-6">
													<button id="submit" type="button" class="btn btn-lg btn-primary">Submit</button>
												</div>
											</div>
										</form>
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

			<!-- Matter ends -->

		</div>

		<!-- Mainbar ends -->
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
		<c:choose>
	    	<c:when test="${param.mode eq 'EDIT'}">
	    		var mode='EDIT';
	    		var eventId = <c:out value="${param.eventId}"></c:out>;
	    	</c:when>
	    	<c:when test="${param.mode eq 'CLONE'}">
	    		var mode='CLONE';
	    		var eventId = <c:out value="${param.eventId}"></c:out>;
	    	</c:when>
	    	<c:otherwise>
	    		var mode='CREATE';
	    		var eventId;
	    	</c:otherwise>
		</c:choose>
		var selectedRowIndex;
		var Event = Backbone.Model.extend({
			synced : false,
			sync : function(){
				var theModel = this;
				$.ajax({
					url : 'rest/RW/event/submit',
					data : JSON.stringify(theModel),
					contentType : 'application/json',
					type : 'POST',
					processData : false,
					success : function(data){
						if(data.status == 'FAIL')
							alert('Unable to save the event. Message: '+data.err);
						else{
							alert('Submit successfull');
							window.location.href = 'http://'+window.location.host+'/bus-trip/index.jsp';
						}
					}, error : function(){
						alert("Unable to sync this event. Please contact support.");
					}						
				});
			}
		});
		var currentEvent = new Event();
		populateForm = function() {
			var url = 'rest/events/';
	    	<c:if test="${param.mode eq 'EDIT' or param.mode eq 'CLONE'}">
	    		url = 'rest/events/'+<c:out value="${param.eventId}"></c:out>;
    		</c:if>
				
			$.getJSON(url, function(data){
				currentEvent.set({'synced' : true});
				currentEvent.set("id", data.id);
				currentEvent.set("price", data.price);
				currentEvent.set("title", data.title);
				if(currentEvent.get('featured') == 1)
					$('#featured').prop('featured', true);
				currentEvent.set("eventType", data.eventType);
				currentEvent.set("hidden", data.hidden);
				currentEvent.set("eventLocation", data.eventLocation);
				currentEvent.set("upsellOrUpgradeEvent", data.upsellOrUpgradeEvent);
				currentEvent.set("eventDateTime", data.eventDateTime);
				currentEvent.set("shortDesc", data.shortDesc);
				currentEvent.set("description", data.description);
				currentEvent.set("lineup", data.lineup);
				currentEvent.set("imageURL", data.imageURL);
				currentEvent.set("minPassenger", data.minPassenger);
				currentEvent.set("maxPassenger", data.maxPassenger);
				currentEvent.set("expiration", data.expiration);
				currentEvent.set("eventState", data.eventState);
				currentEvent.set("eventcategory", data.eventcategory);
				currentEvent.set("pickupLocations", data.pickupLocations);	
				console.log(currentEvent);
				$('#title').val(currentEvent.get('title'));
				$('#price').val(currentEvent.get('price'));
				if(currentEvent.get('featured') == 1)
					$('#featured').prop('checked', true);
				$("#eventType").val(currentEvent.get('eventType')); 
				if(currentEvent.get('hidden') == 1)
					$('#hidden').prop('checked', true);
				$('#eventLocation').val(currentEvent.get('eventLocation'));
				if(currentEvent.get('upsellOrUpgradeEvent') == 1)
					$('#upsellOrUpgradeEvent').prop('checked', true);
				$('#eventDate').val(currentEvent.get('eventDateTime'));
				$('#shortDesc').val(currentEvent.get('shortDesc'));
				$('#description').val(currentEvent.get('description'));
				$('#lineup').val(currentEvent.get('lineup'));
				$('#imageURL').val(currentEvent.get('imageURL'));
				$('#minPassenger').val(currentEvent.get('minPassenger'));
				$('#maxPassenger').val(currentEvent.get('maxPassenger'));
				$('#expirationDate').val(currentEvent.get('expiration'));
				$('#eventState').val(currentEvent.get('eventState'));
				$("#eventcategory").val(currentEvent.get('eventcategory'));
				$('#eventDate').datetimepicker({
					inline : true
				});
				$('#expirationDate').datetimepicker({
					inline : true
				});				
				/* $.each(currentEvent.get('eventcategory'), function(index, item){
					$("#eventcategory").val(currentEvent.get(item)); 
				}); */
			});
		};		
		$(document).ready(new function(){
			if(mode == 'CREATE'){
				$('#eventDate').datetimepicker({
					inline : true
				});
				$('#expirationDate').datetimepicker({
					inline : true
				});
			}
            var source =
            {
                datatype: "json",
                datafields: [
                    { name: 'neighborhoodName', type: 'string' },
                    { name: 'information', type: 'string' },
                    { name: 'pickupName', type: 'string' },
                    { name: 'longitude', type: 'string' },
                    { name: 'latitude', type: 'string' },
                    { name: 'pickupAddress', type: 'string' },
                    { name: 'pickupTime', type: 'string' }
                ],
                id: 'id',
                url: mode=='CREATE'?'resources/blank.txt':'rest/events/pickups/'+eventId
            };
            var dataAdapter = new $.jqx.dataAdapter(source);
            $("#jqxgrid").jqxGrid({
                 width: 900,
                 source: dataAdapter,
                 columnsresize: true,
                 editable: true,
                 enabletooltips: true,
                 selectionmode: 'singlerow',
                 showtoolbar: true,
                 rendertoolbar: function (toolbar) {
                     var me = this;
                     var container = $("<div style='margin: 5px;'></div>");
                     var addRowInput = $("<button type='button' class='btn btn-sm btn-default jqx-widget-content-bootstrap'>Add More Pickups</button>");
                     var removeRowInput = $("<button type='button' class='btn btn-sm btn-default jqx-widget-content-bootstrap'>Remove Pickups</button>");
                     toolbar.append(container);
                     container.append(addRowInput);
                     container.append(removeRowInput);
                     addRowInput.on('click', function(){
                    	 var rows = $('#jqxgrid').jqxGrid('getrows');
                    	 var lastRow = rows[rows.length-1];
                    	 console.log(lastRow);
                    	 console.log(lastRow.neighborhoodName == '' || lastRow.pickupTime == '' || lastRow.pickupAddress == '' || lastRow.latitude == ''
                			 || lastRow.longitude == '' || lastRow.pickupName == '');
                    	 
                    	 if((lastRow.neighborhoodName == '' || lastRow.pickupTime == '' || lastRow.pickupAddress == '' || lastRow.latitude == ''
                    			 || lastRow.longitude == '' || lastRow.pickupName == '')
                    			 || (lastRow.neighborhoodName == 'undefined' || lastRow.pickupTime == 'undefined' || lastRow.pickupAddress == 'undefined' || lastRow.latitude == 'undefined'
                        			 || lastRow.longitude == 'undefined' || lastRow.pickupName == 'undefined')){
							alert('Please complete the last row, before requesting for new pickup details!');
							return false;
                    	 }
                    	 var id = $('#jqxgrid').jqxGrid('getrowid', rows.length-1);
                    	 $('#jqxgrid').jqxGrid('addrow', id+1, {});
                     });
                     removeRowInput.on('click', function(){
                    	 if(selectedRowIndex<=0){
                    		 alert('You must have at least one pickup!!');
                    		 return false;
                    	 }
                    	 var rows = $('#jqxgrid').jqxGrid('getrows');
                    	 var selectedRow = rows[selectedRowIndex];
                    	 console.log(selectedRow);
                    	 $('#jqxgrid').jqxGrid('deleterow', selectedRow.id);
                     });                     
                 },
                 columns: [
                   { text: 'Neighborhood', datafield: 'neighborhoodName', columntype: 'textbox', width: 120 },
                   { text: 'Departure Time', datafield: 'pickupTime', columntype: 'textbox', width: 120 },
                   { text: 'Information', datafield: 'information', columntype: 'textbox', width: 120},
                   { text: 'Pickup Name', datafield: 'pickupName', columntype: 'textbox', width: 150 },
                   { text: 'Address', datafield: 'pickupAddress', columntype: 'textbox', width: 200 },
                   { text: 'Latitude', datafield: 'latitude', columntype: 'textbox', minwidth: 60 },
                   { text: 'Longitude', datafield: 'longitude', columntype: 'textbox', minwidth: 60 }
               ]
             }); 
            $("#jqxgrid").on('rowselect', function (event) {
            	selectedRowIndex = event.args.rowindex;
            });
            $('#submit').click(function(){
            	submitEvent();
            });
            if(mode != 'CREATE')
            	populateForm();
			$('#eventHome').click(function(){
				window.location.href = 'http://'+window.location.host+'/bus-trip/index.jsp';
			});
			$('#eventEditor').click(function(){
				window.location.href = 'http://'+window.location.host+'/bus-trip/forms.jsp';
			});
		});
		submitEvent = function(){
			populateEvent();
			currentEvent.sync();
		};
		populateEvent = function(){
			if(mode == 'CLONE' || mode == 'CREATE')
				currentEvent.set("id", -1);
			currentEvent.set("price", $('#price').val());
			currentEvent.set("title", $('#title').val());
			currentEvent.set("featured", $('#featured').prop('checked')?1:0);
			currentEvent.set("eventType", $('#eventType').val());
			currentEvent.set("hidden", $('#hidden').prop('checked')?1:0);
			currentEvent.set("eventLocation", $('#eventLocation').val());
			currentEvent.set("upsellOrUpgradeEvent", $('#upsellOrUpgradeEvent').prop('checked')?1:0);
			currentEvent.set("eventDateTime", $('#eventDate').val());
			currentEvent.set("shortDesc", $('#shortDesc').val());
			currentEvent.set("description", $('#description').val());
			currentEvent.set("lineup", $('#lineup').val());
			currentEvent.set("imageURL", $('#imageURL').val());
			currentEvent.set("minPassenger", $('#minPassenger').val());
			currentEvent.set("maxPassenger", $('#maxPassenger').val());
			currentEvent.set("expiration", $('#expirationDate').val());
			currentEvent.set("eventState", $('[name="eventState"]').val());
			currentEvent.set("eventcategory", $('#eventcategory').val());
			currentEvent.set("pickupLocations", $("#jqxgrid").jqxGrid('getrows'));
			console.log(currentEvent);
			console.log(JSON.stringify(currentEvent));
		}
	</script>
</body>
</html>