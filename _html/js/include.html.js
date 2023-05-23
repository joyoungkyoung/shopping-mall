//HTML 인클루드
function includeHTML(cb) {
    var z, i, elmnt, file, xhttp;
    z = document.getElementsByTagName("*");
    for (i = 0; i < z.length; i++) {
        elmnt = z[i];
        file = elmnt.getAttribute("data-include");
        if (file) {
            xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    elmnt.innerHTML = this.responseText;
                    elmnt.removeAttribute("data-include");
                    includeHTML(cb);
                }
            };
            xhttp.open("GET", file, true);
            xhttp.send();
            return;
        }
    }
    if (cb) cb();
}
