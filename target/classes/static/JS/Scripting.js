function checkTheBox() {
  var checkBox = document.getElementById("myCheck");
  var text = document.getElementById("endDate");
  if (checkBox.checked == true){
    text.style.display = "block";
  } else {
     text.style.display = "none";
  }
}
function topBar() {
  var x = document.getElementById("homeTopnav");
  if (x.className === "topnav") {
    x.className += " responsive";
  } else {
    x.className = "topnav";
  }
}

 $(document).ready(function(){
        $('body').css('display', 'none');
        $('body').fadeIn(350);
    });

function areYouSure() {
  var sure = confirm("Are you sure you want to delete this?")
  if(sure==true){



  }
}