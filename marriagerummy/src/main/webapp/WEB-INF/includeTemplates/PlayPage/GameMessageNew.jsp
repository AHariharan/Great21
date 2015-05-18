<div id="NewMessageModal" class="modal fade">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">New Message</h4>
			</div>
			<div class="modal-body">
				<div class="newmessage" >
				        <div class="NewMessageTO">
						<label style="/* margin: 0px auto; */padding-top: 7px;"> To : </label>
						<div class="newmessage-selectednickname">
						   <!-- <div> Hariharan <span> x </span></div>
						   <div> Rathika <span> x </span></div> -->
						</div>
						<label id="getFriends" class="btn btn-link">Select Friends</label>
						</div>
						<div class="NewMessageContent">
						  <label> Message : </label>
						  <textarea class="form-control" rows="5"></textarea>
						</div> 
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<button id="SendMessageNow" type="button" class="btn btn-primary">Send Message</button>
			</div>
		</div>
	</div>
	<!-- /.modal-dialog -->
</div>

<div id="SelectFriendsListModal" class="modal fade">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">My Friends</h4>
			</div>
			<div class="modal-body">
				<div id="selectfriendList">
					<!-- <div id="friend">
						<div class="selectcheckbox">
							<input type="checkbox" />
						</div>
						<div class="friendImage">
							<span><i class="fa fa-user fa-5x"></i>
						</div>
						<div class="friendContent">
							<h4>Nick Name</h4>
							<h5>
								Email Address <span
									style="color: rgb(74, 148, 115); font-size: 11px;"> <i
									class="fa fa-clock-o"></i> 1 day ago
								</span>
							</h5>
						</div>
						<div class="friendRating">
							<h6>Rating</h6>
							<h4>D+</h4>
						</div>
					</div> -->
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<button id="SelectedFriends" type="button" class="btn btn-primary">Done</button>
			</div>
		</div>
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- <script>
  var selectedFriends = new Array();
  $(document).ready(function(){
         $('#NewMessageModal').modal('show');
  });
  $('#getFriends').on("click",function(){
         $('#FriendsListModal').modal('show');
  });
  $('#SelectedFriends').on("click",function(){
          selectedFriends = new Array();
          $('#FriendsListModal').modal('hide');  
          $('.friend input[type="checkbox"]').filter(':checked').each(
			            function(){
			                    var friendnode = $(this).parent().parent();
			                    var nickname =  friendnode.children().filter('div.friendContent').children().filter('h4').html().trim();
			                    selectedFriends.push(nickname);
			 });
			 });
 
</script> -->