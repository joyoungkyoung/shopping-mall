function setCookie(name, value, expire) {
    const date = new Date();

    date.setTime(date.getTime() + expire);
    document.cookie = name + "=" + value + ";expires=" + date.toUTCString() + ";path=/";
}

function getCookie(name) {
    const value = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");

    return value ? value[2] : null;
}

function deleteCookie(name) {
    document.cookie = name + "=; expires=Thu, 01 Jan 1999 00:00:10 GMT;";
}
