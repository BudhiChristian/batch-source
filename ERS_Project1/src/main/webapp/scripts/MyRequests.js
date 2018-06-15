document.addEventListener("DOMContentLoaded", function () {
    sendAjaxGet("http://localhost:8082/ERS_Project1/DisplayMyRequests", function (xhr) {
        let info = JSON.parse(xhr.response);
        if(info.staff.length < 1) {
            document.getElementById("goToManageRequests").setAttribute("hidden", true);
        }
        //console.log(info);

        let pendingTable = document.getElementById("pendingTable");
        let resolvedTable = document.getElementById("resolvedTable");
        for (let request of info.requests) {
            let row = document.createElement("tr");

            let temp = document.createElement("td");
            temp.innerHTML = "$" + request.amount;
            row.appendChild(temp);

            temp = document.createElement("td");
            let details = document.createElement("details");
            let summary = document.createElement("summary");
            summary.innerHTML = "Details";
            let reqInfo = document.createElement("p");
            summary.innerHTML = request.description;
            details.appendChild(summary);
            details.appendChild(reqInfo);
            temp.appendChild(details);
            row.appendChild(temp);

            temp = document.createElement("td");
            let date = new Date(request.dateRequested);
            temp.innerHTML = date.getMonth() + "-" + date.getDate() + "-" + date.getFullYear();
            row.appendChild(temp);

            temp = document.createElement("td");
            temp.innerHTML = request.status;
            row.appendChild(temp);

            if(request.status != "PENDING") {
                temp = document.createElement("td");
                    date = new Date(request.dateResolved);
                    temp.innerHTML = date.getMonth() + "-" + date.getDate() + "-" + date.getFullYear();
                    row.appendChild(temp);
                    resolvedTable.appendChild(row)
            } else {
                pendingTable.appendChild(row);
            }
        }
    });
});


function sendAjaxGet(url, func) {
    let xhr = (new XMLHttpRequest || new ActiveXObject("Microsoft.HTTPRequest"));
    xhr.onreadystatechange = function () {
        if (this.status == 200 & this.readyState == 4) {
            func(this);
        }
    }

    xhr.open("GET", url);
    xhr.send();
}