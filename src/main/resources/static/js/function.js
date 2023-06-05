function validEmail(email) {
    const form = /^([\w\.\_\-])*[a-zA-Z0-9]@([\w\.\_\-])*[a-zA-Z0-9]+\.+([a-zA-Z0-9]{2,8})$/;

    return form.test(email);
}
