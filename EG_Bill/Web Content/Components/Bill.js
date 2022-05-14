//Response-Alert  
$(document).ready(function() {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});
//Valid Input Checker
function validateBilForm() {

  

    if ($("#cusName").val().trim() == "") {
        return "Bill Must Have A Customer Name";
    }
    if ($("#cusAcc").val().trim() == "") {
        return "Bill Must Have A Account No";
    }
    if ($("#cusDate").val().trim() == "") {
        return "BilL Must Have A Date";
    }
    if ($("#units").val().trim() == "") {
        return "BilL Must Have A Units";
    }
    if ($("#totalPrice").val().trim() == "") {
        return "Total Price Can't be Empty";
    }
    
    return true;
}
//Save

$(document).on("click", "#btnSave", function(event) {
    //Clear alerts
    $("#alertSuccess").text("");
    $("#alertError").text("");
    $("#alertSuccess").hide();
    $("alertError").hide();
    
    //Form validation
    var status = validateBilForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }

        var method = ($("#billid").val() == "") ? "POST" : "PUT";

        $.ajax({
            url: "BillAPI",
            type: method,
            data: $("#formBil").serialize(),
            dataType: "text",
            complete: function(response, status) {

                onBillSaveSuccess(response.responseText, status);
            }
        });
    });

function onBillSaveSuccess(response, status) {
    if (status == "success") {
    	
        var resultset = JSON.parse(response);
        
        if (resultset.status.trim() == "success") {
            $("#alertSuccess").text("Saved Successfully!");
            $("#alertSuccess").show;
            $("#divBilGrid").html(status.data);

        } else if (resultset.status.trim() == "error") {
            $("#alertError").text(status.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Saving Error!");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unkown Error while Saving!");
        $("#alertError").show();
    }
    $("#billid").val("");
    $("#formBil")[0].reset();
}
//Update Bill
$(document).on("click", "#btnUpdate", function(event) {
	console.log("Update Button Invoked");
    $("#billid").val($(this).closest("tr").find('#hiddenbilIDUpdate').val());
    $("#cusName").val($(this).closest("tr").find('td:eq(0)').text());
    $("#cusAcc").val($(this).closest("tr").find('td:eq(1)').text());
    $("#cusDate").val($(this).closest("tr").find('td:eq(2)').text());
    $("#units").val($(this).closest("tr").find('td:eq(3)').text());
    $("#totalPrice").val($(this).closest("tr").find('td:eq(4)').text());
});
//Remove Bill
$(document).on("click", "#btnRemove", function(event) {
    $.ajax({
        url: "BillAPI",
        type: "DELETE",
        data: "billid=" + $(this).data("billid"),
        dataType: "text",
        complete: function(response, status) {
            onBillDeleteSuccess(response.responseText, status);
        }
    })
});

function onBillDeleteSuccess(response, status) {
    if (status == "success") {
        var resultset = JSON.parse(response);
        if (resultset.status.trim() == "success") {
            $("#alertSuccess").text("Deleted Successfully!");
            $("#alertSuccess").show;
            $("#divBilGrid").html(status.data);

        } else if (resultset.status.trim() == "error") {
            $("#alertError").text(resultset.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Deleting Error!");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unkown Error while Deleting!");
        $("#alertError").show();
    }
}