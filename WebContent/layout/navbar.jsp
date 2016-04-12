<nav class="navbar navbar-default"  style="font-size:18px;">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#" data-toggle="popover" data-placement="bottom">Login</a></li>
            <li><a href="register.jsp">Register</a></li>
	</ul>
    </div>
</nav>


<div id="popover-content" class="hide">
  <form class="form-horizontal">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Login</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Login">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Pass</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Sign in</button>
    </div>
  </div>
</form>
</div>

<script>
$(document).ready(function(){
    $('[data-toggle="popover"]').popover({
    	html : true,
    	container: 'body',
    	content: function() {
    		 return $("#popover-content").html();
    	}
    });  
});
</script>