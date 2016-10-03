  // remove string's leading spaces, then trailing spaces
  String.prototype.trim = function () {  
    return this.replace(/^\s*/, "").replace(/\s*$/, "");
  }

  function dataCheck(form) {
    widget = form.tf_lastName;
    if (widget.value.trim() == "") {
      alert("You must enter your last name");
      widget.focus();
      widget.select();
      return false;
    }
    widget = form.tf_firstName;
    if (widget.value.trim() == "") {
      alert("You must enter your first name");
      widget.focus();
      widget.select();
      return false;
    }
    widget = form.cbo_discipline;
    if (widget.selectedIndex == 0) {
      alert("You must select a discipline");
      widget.focus();
      return false;
    }

    return true;
  }