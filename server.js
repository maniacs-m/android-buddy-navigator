var http = require("http");
var querystring = require("querystring");

const PORT = 8080;
var userIDs = ["Jemboy"];

var server = http.createServer(function (request, response) {
	response.writeHead(200, "Header", {"Content-Type": "application/x-www-form-urlencoded"});

	request.on("data", function (data) {
		post = querystring.parse(data.toString());
		var request = post["request"];
		console.log(request);
		if (request == "upload") {
			var localID = post["localID"];
			if (userIDs.indexOf(localID) > -1) {
				response.end("Fail");
			}
			else {
				var pastID = post["pastID"];
				if (userIDs.indexOf(pastID) > -1)
					userIDs.splice(userIDs.indexOf(pastID), 1);
				userIDs.push(localID);
				response.end("Success");
			}
		}

		if (request == "download") {
			var remoteID = post["remoteID"];
			if (userIDs.indexOf(remoteID) > -1) {
				response.end("Success");
			}
			else {
				response.end("Fail");
			}
		}

		if (request == "delete") {
			var targetID = post["targetID"];
			if (userIDs.indexOf(targetID) > -1)
				userIDs.splice(userIDs.indexOf(targetID), 1);
		}
		console.log(userIDs);
	});
	
}).listen(PORT, function () {
	console.log("Server listening at: http://localhost:" + PORT);
});

/*
var jsonString = '[]';
var jsonArray = JSON.parse(jsonString);
jsonArray.push({"userId" : request["userId"], "latitude" : request["latitude"], "longitude" : request["longitude"]});
var jsonObject = {"latitude" : 40.891662, "longitude" : 29.378559};
response.end(JSON.stringify(jsonObject));
*/