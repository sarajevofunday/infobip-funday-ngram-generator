<!doctype html>
<html>
	<head>

		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

	</head>
	<body>
		<div class="container">

			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<div class="jumbotron" style="text-align: center;">
						<h1>NGRAM</h1>
						<p>Automagic text genetator</p>
					</div>
				</div>
				<div class="col-md-2"></div>
			</div>
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-4 col-sm-4"></div>
							<div class="col-md-4 col-sm-4 input-group input-group-sm">
								<input id="ngram-input" type="text" class="form-control" placeholder="Word count">
								<span class="input-group-btn">
									<button onclick="setText($(this))" class="btn btn-default" type="button">Default</button>
								</span>
							</div>
						</div>
					</div>
					<div id="ngram-text" class="panel-body">
					</div>
				</div>

			</div>


			<hr>

			<footer>
				<p>&copy; Team Automation 2014</p>
			</footer>

		</div>
		<script type="text/javascript">
			$(function(){
				$('#ngram-input').focus();
				$('#ngram-input').keydown(function(e){
					if (e.keyCode == 13) {
						setPanelText($('#ngram-input').val());
					}
				});
			});
			function setText(button) {
				var span = button.parent();
				var input = span.prev();
				var value = input.val();
				setPanelText(value);
			}
			function setPanelText(value) {
				$.get( "/getText/"+value, function( data ) {
				  $( ".result" ).html( data );
				  $('#ngram-text').text(data);
				});
			}
		</script>
	</body>


</html>
