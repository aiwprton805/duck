$(function () {

    var proposalTable = $("#proposals-table");

    var moreBtn = $("#more-btn").on("click", function () {
        window.location = proposalTable.find("tbody tr.table-primary").data("id");
    });

    var removeBtn = $("#remove-btn").on("click", function () {
        window.location = "remove/" + proposalTable.find("tbody tr.table-primary").data("id");
    });

    proposalTable.find("tbody tr").on("click", function () {
        $(this).toggleClass("table-primary").siblings().removeClass("table-primary");
        if($(this).hasClass("table-primary")){
            moreBtn.prop("disabled", false);
            removeBtn.prop("disabled", false);
        }else{
            moreBtn.prop("disabled", true);
            removeBtn.prop("disabled", true);
        }
    });
});
