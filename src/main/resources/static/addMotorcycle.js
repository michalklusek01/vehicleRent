function validate(){
    var licensePlate = document.getElementById("license-plate-input");
    var price = document.getElementById("price-input");
    var type = document.getElementById("type");
    var transmissionType = document.getElementById("transmission-type");


    var flag = true;

        if(transmissionType.value == "MANUAL" || transmissionType.value == "AUTOMATIC"){
            transmissionType.style.background = "#ffffff";
        }else{
            transmissionType.style.background = "#ebb5b5";
            flag = false;
        }


    if(type.value == "SCOOTER" || type.value == "SPORT" || type.value == "STREET"
        || type.value == "CROSS"|| type.value == "CRUISER"|| type.value == "CHOPPER"){
        type.style.background = "#ffffff";
    }else{
        type.style.background = "#ebb5b5";
        flag = false;
    }

    if(licensePlate.value == ""){
        licensePlate.style.background = "#ebb5b5";
        flag = false;
    }else{
        licensePlate.style.background = "#ffffff";
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