document.getElementById("order").onclick = function(e) {
	if (e.target.nodeName == "BUTTON") {
		orderby = e.target.parentElement.getAttribute("data-order");
		var url = "board?action=orderby&order=" + orderby;
		
		fetch(url)
			.then((response) => response.json())
			.then((data) => makeList(data));
		
		document.querySelectorAll("li").forEach(function(element) {
			element.className = "e-order";
		});
		e.target.parentElement.className = "e-order active";
	}
}

function makeList(data) {
	let articles = data;
	let contents = ``;
	articles.forEach((article) => {
		contents += `
			<tr onClick="location.href='board?action=detail&article_no=${article.articleNo}'">
				<td>${article.subject}</td>
				<td>${article.userId}</td>
				<td>${article.hit}</td>
				<td>${article.registerTime}</td>
			</tr>
		`;
	});
	
	document.getElementById("tbody").innerHTML = contents;
}