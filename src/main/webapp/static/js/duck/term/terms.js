$(function () {
    /* ------------------------------ SEARCH FIELDS ------------------------------ */
    var ruSearch = $("#ru-search");
    var deSearch = $("#de-search");

    deSearch.on("change", function () {
        if($(this).val() !== ""){
            ruSearch.prop("disabled", true);
        }else{
            ruSearch.prop("disabled", false);
        }
    });

    ruSearch.on("change", function () {
        if($(this).val() !== ""){
            deSearch.prop("disabled", true);
        }else{
            deSearch.prop("disabled", false);
        }
    });

    /* --------------------------------- BUTTONS --------------------------------- */
    var newTermBtn = $("#new-term-btn").on("click", function () {
        window.location = "proposal/add";
    });

    var searchBtn = $("#search-btn").on("click", function () {
        var lang = null;
        var query
        if(ruSearch.val() !== ""){lang = "RU"; query = ruSearch.val();}
        if(deSearch.val() !== ""){lang = "DE"; query = deSearch.val();}
        if(lang === null) return;
        getTerms(query, lang);
    });


    var termTable = $("#term-table");

    function populateTermTable(data) {
        var table = termTable.find("tbody").empty();
        $.each(data, function (i, group) {
            var row = $('<tr>').data("groupId", group.id).appendTo(table);
            $.each(group.terms, function (i, term) {
                $('<td>').data("termId", term.id).text(term.name).appendTo(row);
            })
        });
    }

    function getTerms(query, lang) {
        $.getJSON("terms/search", {query: query, lang: lang} ,function (data) {
            populateTermTable(data);
        }).fail(function (jqXHR, textStatus) {
            console.error("error TermController.search() /term/search Ajax");
            console.error("status: " + textStatus + "; status code: " + jqXHR.status);
        });
    }
});
