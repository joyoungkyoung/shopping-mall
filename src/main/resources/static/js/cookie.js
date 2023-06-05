function setCookie(name, value, expire) {
    const date = new Date();

    date.setTime(date.getTime() + expire);
    document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value) + ";expires=" + date.toUTCString() + ";path=/";
}

function getCookie(name) {
    const value = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");

    return value ? decodeURIComponent(value[2]) : null;
}

function deleteCookie(name) {
    document.cookie = encodeURIComponent(name) + "=; expires=Thu, 01 JAN 1999 00:00:10 GMT;path=/;";
}
