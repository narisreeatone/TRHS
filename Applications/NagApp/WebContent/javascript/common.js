function IsEmpty($filedId){	
	error = false;
	fldValue = $filedId.val().trim();
    if(fldValue==""){  
    	$filedId.css("border","1px dotted red");    	   
    	error = true;
	}
    else{
    	$filedId.css("border","1px solid #7A7A7A");
    	$filedId.val(fldValue);    	
    }
    return error;        
}
function IsEmptyNotRequired($filedId){ 	
	error = false;
	fldValue = $filedId.val().trim();
    if(fldValue==""){
    	error = true;
	}
    else{    	
    	$filedId.val(fldValue);    	
    }
    return error;        
}
function validatePassword($filedId) {
    var error = false;   
    fldValue = $filedId.val().trim();
    var illegalChars = /[\W_]/; // allow only letters and numbers
 
    /*if (fldValue == "") {
    	fld.css("border","1px dotted red");        
        error = true;
 
    }else*/
    if (illegalChars.test(fldValue)) {       
       fld.css("border","1px dotted red");        
        error = true; 
    }
    return error;
}
function alphabetsOnly($filedId){
	error = false;
	fldValue = $filedId.val().trim();
	
	var whitespace = /[\s]/;
	var numbers = /[\d]/;
	var alphbets = /[\w]/;
	var specialChars = /[!@#$%^&*(){}'"|\\][?~`+-_]/;
	 if (fldValue == "") {
		$filedId.css("border","1px dotted red");	       
        error = true;	 
    }else if(numbers.test(fldValue)){
    	//alert("numbers");
    	$filedId.css("border","1px dotted red");	       
        error = true;
    }else if(whitespace.test(fldValue)){
    	//alert("whitespace");
    	$filedId.css("border","1px dotted red");	       
        error = true;
    }else if(specialChars.test(fldValue)){
    	//alert("specialChars");
    	$filedId.css("border","1px dotted red");	       
        error = true;
    }else{
    	$filedId.css("border","1px solid #7A7A7A");
    	$filedId.val(fldValue);
    	//alert("no error");
        error = false;
    }
	 
	 return error;
}
function currencyOnly($filedId){
	
	var currency = /[\d+\.\d\d]/;
	var error = false;
	fldValue = $filedId.val().trim();	
	if (fldValue == "") {		
		$filedId.css("border","1px dotted red");	       
        error = true;	 
        return error;
    }else if(!currency.test(fldValue)){		
		$filedId.css("border","1px dotted red");	       
        error = true;        
	}else{		
		$filedId.css("border","1px solid #7A7A7A");
    	$filedId.val(fldValue);        
	}	
	return error;
}
function numberOnly($filedId){
	
	var numbers = /[\d]/;
	var error = false;
	fldValue = $filedId.val().trim();	
	if (fldValue == "") {
		$filedId.css("border","1px dotted red");	       
        error = true;	 
    }else if(!numbers.test(fldValue)){
		$filedId.css("border","1px dotted red");	       
        error = true;        
	}else{
		$filedId.css("border","1px solid #7A7A7A");
    	$filedId.val(fldValue);      
	}	
	return error;
}
function validListBox($filedId){
	var error = false;
	fldValue = $filedId.val();	
	if(fldValue == "select" || fldValue == "Select" ){
		$filedId.css("border","1px dotted red");
		error = true;
	}else{
		$filedId.css("border","1px solid #7A7A7A");
	}
	return error;
}
function checkEmailFormat($filedId){
	var error = false;
	fldValue = $filedId.val().trim();	
	var emailtest=/^\w+([\.-]?\w+)*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i;		
	if(!emailtest.test(fldValue)){
		$filedId.css("border","1px dotted red");	       
        error = true;
	}else{
		$filedId.css("border","1px solid #7A7A7A");
    	$filedId.val(fldValue);        
	}
	return error;
}
function checkMobileNumber($filedId){
	var error = false;
	fldValue = $filedId.val().trim();
	var mobNumberfilter=/\d{10}/;	
	if(!mobNumberfilter.test(fldValue)){
		$filedId.css("border","1px dotted red");	       
        error = true;
	}else{
		$filedId.css("border","1px solid #7A7A7A");
    	$filedId.val(fldValue);        
	}
	return error;
}
/*function validText($filedId){
	
}*/