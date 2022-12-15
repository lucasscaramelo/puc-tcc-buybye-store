$(function () {
  "use strict";

$(document).ready(function() {

  /* jQueryKnob */
  $(".knob").knob();

  function getDashboardStatistics(){
      $.ajax({
          url: "api/dashboard/statistics",
          'dataType': "json",
        }).done(function(data) {
            $('#count_product').text(data.count_product);
            $('#count_producttype').text(data.count_producttype);
            $('#count_user').text(data.count_user);
            $('#count_visitors').text(data.count_visitors);
      });
  }
  getDashboardStatistics();
  });
});

