function checkTheBox() {
  var checkBox = document.getElementById("myCheck");
  var text = document.getElementById("endDate");
  if (checkBox.checked == true){
    text.style.display = "block";
  } else {
     text.style.display = "none";
  }
}