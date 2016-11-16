$(document).ready(function(){
    if (contentURL) {
        $.getJSON(contentURL)
            .done(function(data) {
                var items = [];
                $.each(data, function(key, val) {
                    items.push("<tr><td>" +val.name+"</td><td>"+val.status+"</td><td>"+val.sourceLang+"</td><td>"+val.targetLangs.join(", ")+"</td><tr>");
                });
                console.info(items);
                var tableHeader = "<tr><th>Name</th><th>Status</th><th>Source language</th><th>Target languages</th>";
                $("#api-response-content").replaceWith("<table>"+tableHeader+items.join("")+"</table>");
            })
            .fail(function( jqxhr, textStatus, error ) {
                  var err = (jqxhr.responseJSON.message) ? jqxhr.responseJSON.message : textStatus + ", " + error;
                  console.info("Request Failed: " + err);
                  $("#api-response-error").html(err);
                  $("#api-response-error").show();

            }).always(function() {
                  console.log("AJAX complete");
                  $("#api-response-loader").hide();
            });
     }
});
