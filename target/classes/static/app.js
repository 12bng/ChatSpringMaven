var stompClient = null;

function setConnected(connected) {
	
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        //stompClient.send("/app/username", {}, JSON.stringify({'nombre': $("#nombre").val()}));
    	stompClient.send("/app/oldmessajes");
    	$("#nombre").hide();
        $("#conversation").show();
        $("#mensajebox").show();
        //$("#greetings").html("");
        
        sessionStorage.setItem("name", $("#nombre").val());
       
    }
    else {
    	$("#nombre").show();
        $("#conversation").hide();
        $("#mensajebox").hide();
        //$("#greetings").html("");
    }
    
}
function hide(){
	 $("#conversation").hide();
     $("#mensajebox").hide();
}
function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/topic/oldMessajes', function (messages) {
            showOldGreetings(messages);
        });
        
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    sessionStorage.setItem("hecho", false);
    console.log("Disconnected");
}

function sendMessage() {
    stompClient.send("/app/hello", {}, JSON.stringify({'mensaje':$("#mensaje").val(), 'remitente': sessionStorage.getItem("name")}));
   // document.write(JSON.stringify({'name': $("#mensaje").val(),'mensaje': $("#mensaje").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>"+ message + "</td></tr>");
}
function showOldGreetings(messages) {
	//TODO que no cargue a todos los usuarios
	console.log(sessionStorage.getItem("hecho"));
	if(messages!=null&&(sessionStorage.getItem("hecho")==null||sessionStorage.getItem("hecho")==false)){
		 sessionStorage.setItem("hecho", true);
		var objetos = JSON.parse(messages.body);
		var cantidad = objetos.length;
		for(var i = 0;cantidad>i; i++){
			$("#greetings").append("<tr><td>" + objetos[i].remitente + ": " + objetos[i].mensaje + "</td></tr>");
		}
	}
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});