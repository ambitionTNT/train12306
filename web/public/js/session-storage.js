SessionStorage = {
    //从session中取出对象，同时对
    get: function (key) {
        //localstorage是不会消失的即使浏览器关闭了。
        var v = sessionStorage.getItem(key);
        if (v && typeof(v) !== "undefined" && v !== "undefined") {
            return JSON.parse(v);
        }
    },
    //将对象转成json然后存到session中
    set: function (key, data) {
        sessionStorage.setItem(key, JSON.stringify(data));
    },
    remove: function (key) {
        sessionStorage.removeItem(key);
    },
    clearAll: function () {
        sessionStorage.clear();
    }
};
