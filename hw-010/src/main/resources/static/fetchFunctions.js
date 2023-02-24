const request = function (url = '', params = {}, method = 'GET'){
    let options = {
        method
    };
    if(method === 'GET'){
        url += '?' + (new URLSearchParams(params)).toString();
    } else {
        options.body = JSON.stringify(params);
    }

    return fetch(url, options).then(response => response.json());
};

const get = function (url, params){
    return request(url, params, 'GET');
};

const post = function (url, params) {
    return request(url, params, 'POST');
};

export {get, post};


