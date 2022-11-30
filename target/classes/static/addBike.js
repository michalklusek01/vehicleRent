function validate(){
    var price = document.getElementById("price-input");
    var type = document.getElementById("type");

    var flag = true;

    if(type.value == "ELECTRIC" || type.value == "ROAD" || type.value == "MOUNTAIN"
        || type.value == "UTILITY"){
        type.style.background = "#ffffff";
    }else{
        type.style.background = "#ebb5b5";
        flag = false;
    }

    if(price.value != ""){
        if(isNaN(price.value)){
           price.style.background = "#ebb5b5";
           flag = false;
        }else{
                 price.style.background = "#ffffff";
        }
    }

    return flag;
}