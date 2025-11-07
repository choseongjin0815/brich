/**
 * 
 */
$().ready(function() {
    var urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get("sortCol") !== null) {
        column = urlParams.get("sortCol");
        order = urlParams.get("order");
        
        if (order === 'asc') {
            $(`[data-sort-type="${column}"]`).addClass("desc").removeClass("asc");
        }
        else if (order === 'desc') {
            $(`[data-sort-type="${column}"]`).addClass("asc").removeClass("desc");
        }
    }

    $(".sort").on("click", function() {
        order = $(this).attr("class").includes("asc") ? "asc" : "desc";
        col = $(this).data("sort-type");
        
        if (window.location.search === "") {
            url = window.location.pathname + "?order=" + order
                                           + "&sortCol=" + col;
         }
         else {
             url = new URL(window.location.href);
             searchParam = url.searchParams;
             searchParam.set("order", order);
             searchParam.set("sortCol", col);
             url = url.toString();
         }
        
        window.location.href = url;
    });
});