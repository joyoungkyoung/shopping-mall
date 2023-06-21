function validEmail(email) {
    const form = /^([\w\.\_\-])*[a-zA-Z0-9]@([\w\.\_\-])*[a-zA-Z0-9]+\.+([a-zA-Z0-9]{2,8})$/;

    return form.test(email);
}

function getParamMap(queryString) {
    const splited = queryString.replace("?", "").split(/[=?&]/);
    const param = {};

    for (let i = 0; i < splited.length; i++) {
        param[splited[i]] = splited[++i];
    }

    return param;
}
