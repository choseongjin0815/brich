function makeCheckedList(insertClassName) {
    $(insertClassName).empty();
    for(i = 0; i < checkedList.length; i++) {
        cityBlock = $("<div></div>");
        cityBlock.addClass("check-city");
        
        cityName = $("<div></div>")
        cityName.text(checkedList[i]);  
        
        deleteCity = $("<div>x</div>");
        deleteCity.addClass("delete-city");
        deleteCity.on("click", function() {
            city = $(this).closest(".check-city").children("div:first-child").text();
            checkedList = checkedList.filter((e) => e !== city);
            $(this).closest(".check-city").remove();
        });
        
        cityBlock.append(cityName);
        cityBlock.append(deleteCity);
        
        $(insertClassName).append(cityBlock);
    }
}

var checkedList = [];

$().ready(function() {
    
    $("input[name=do-city]").on("click", function() {
        var id = $(this).attr("id");
        
        $.get("/adv/campaign/write/" + id, function(response) {
            $(".city-gu-gun").empty();
            if(response.body) {
                city = response.body;
                for(i = 0; i < city.length; i++) {
                    cityBox = $("<input>");
                    cityBox.attr("type", "checkbox");
                    cityBox.attr("name", "city-gu-gun");
                    cityBox.attr("id", city[i].cdNm);
                    cityBox.on("click", function() {
                        doName = $("input[name=do-city]:checked").data("do-name");
                        cityName = doName + " " + $(this).attr("id");
                        
                        var arCnt = 3;
                        if(checkedList.includes(cityName)) {
                            // 리스트 특정 값 제거 방법 : 삭제해야하는 value와 안겹친 경우만 array로 다시 반환
                            checkedList = checkedList.filter((e) => e !== cityName);
                        }
                        else if (checkedList.length === arCnt) {
                            alert(arCnt + "개를 선택하셨습니다.");
                        }
                        else {
                            checkedList.push(cityName);
                        }
                        
                        makeCheckedList(".checked-cities");
                    });
                    
                    cityLabel = $("<label></label>");
                    cityLabel.attr("for", city[i].cdNm);
                    cityLabel.text(city[i].cdNm);
                    
                    $(".city-gu-gun").append(cityBox);
                    $(".city-gu-gun").append(cityLabel);
                }
            }
        });
    });
    
    $(".area").children("div").find("button").on("click", function() {
        $(".modal").css("display", "flex");
        makeCheckedList(".checked-cities");
    });
    
    $(".modal-submit").on("click", function() {
        $(".modal").css("display", "none");
        makeCheckedList(".area-list");
    });
    
    $("input[name=address-check]").on("change", function() {
        loadAddr = $("input[name=loadAddress]");
        detailAddr = $("input[name=detailAddress]");
        
        if ($(this).attr("id") === "address-check") {
            loadAddr.removeAttr("disabled");
            detailAddr.removeAttr("disabled");
        }
        else if ($(this).attr("id") === "address-uncheck") {
            loadAddr.val("");
            detailAddr.val("");
            loadAddr.attr("disabled", "disabled");
            detailAddr.attr("disabled", "disabled");
        }
    });
    
    $("input[name=rcrtPrsnn]").on("keyup", function() {
        var price = parseInt($(this).data("person-price"));
        $(".rcrtPrsnn-price").text($(this).val() * price);
        console.log(checkedList);
    });
    
    $(".submit-button").on("click", function() {
        for(i = 0; i < checkedList.length; i++) {
            hidden = $("<input type='hidden' name='area' />");
            hidden.attr("value", checkedList[i]);
            $(".hidden-area-list").append(hidden);
        }
        $("#campaign-submit").submit();
    });
    
    $("input[type=number]").on("keyup", function() {
        if ($(this).val() < 0) {
            $(this).val(0);
        }
        if ($(this).val() === "0") {
            $(this).val("");
        }
    });
    
    $("input[name=rcrtPrsnn]").on("keyup", function() {
        if ($(this).val() > 1000) {
            $(this).val(999);
        }
    });
    
    $("input[name=offrPrc]").on("keyup", function() {
        if ($(this).val() > 100000000) {
            $(this).val(99999999);
        }
    });
});