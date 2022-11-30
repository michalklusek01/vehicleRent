function validate(){
    var licensePlate = document.getElementById("license-plate-input");
    var price = document.getElementById("price-input");
    var fuelType = document.getElementById("fuel-type");
    var transmissionType = document.getElementById("transmission-type");
    var airConditioning = document.getElementById("airConditioning");


    var flag = true;

        if(transmissionType.value == "MANUAL" || transmissionType.value == "AUTOMATIC"){
            transmissionType.style.background = "#ffffff";
        }else{
            transmissionType.style.background = "#ebb5b5";
            flag = false;
        }

            if(airConditioning.value == "true" || airConditioning.value == "false" ){
                airConditioning.style.background = "#ffffff";

            }else{
                airConditioning.style.background = "#ebb5b5";
                flag = false;
            }

    if(fuelType.value == "GASOLINE" || fuelType.value == "DIESEL" || fuelType.value == "GAS"){
        fuelType.style.background = "#ffffff";
    }else{
        fuelType.style.background = "#ebb5b5";
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