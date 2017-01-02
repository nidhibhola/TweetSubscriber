var socket = new WebSocket(
		"ws://localhost:8080/com.twittertest.restapi/hashtagtweets");
socket.onmessage = onMessage;

function searchHashtag() {
	var hashtag = document.getElementById("hashtag").value;
	console.log("hashtag: " + hashtag);
	socket.send(hashtag);

	generateTable();
}

function onMessage(event) {
	var status = event.data;
	var parsedTweet = JSON.parse(status);
	addTableRow(parsedTweet.username, parsedTweet.text, parsedTweet.createdAt);
}

var tweets;
var table;
var columnCount;
var row;
function generateTable() {
	// Build an array containing Customer records.
	tweets = new Array();
	tweets.push([ "Username", "Tweet text", "Time" ]);

	// Create a HTML Table element.
	table = document.createElement("TABLE");
	table.border = "1";

	// Get the count of columns.
	columnCount = tweets[0].length;

	// Add the header row.
	row = table.insertRow(-1);
	for (var i = 0; i < columnCount; i++) {
		var headerCell = document.createElement("TH");
		headerCell.innerHTML = tweets[0][i];
		row.appendChild(headerCell);
	}

	var dvTable = document.getElementById("content");
	dvTable.innerHTML = "";
	dvTable.appendChild(table);
}

function addTableRow(username, text, createdAt) {
	// Add the data rows.
	row = table.insertRow(-1);
	var cell = row.insertCell(-1);
	cell.innerHTML = username;

	cell = row.insertCell(-1);
	cell.innerHTML = text;

	cell = row.insertCell(-1);
	cell.innerHTML = createdAt;

	row.appendChild(cell);

}