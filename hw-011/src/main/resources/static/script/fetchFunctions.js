const request = function (url = '', params = {}, method = 'GET') {
    let options = {
        method
    };
    if ((method === 'GET') || (method === 'DELETE')) {
        url += '?' + (new URLSearchParams(params)).toString();
    } else {
        options.body = JSON.stringify(params);
        options.headers = {
            'Content-Type': 'application/json; charset=UTF-8',
            'Accept': 'application/json'
        };
    }

    return fetch(url, options).then(response => response.json());
};

const get = function (url, params) {
    return request(url, params, 'GET');
};

const post = function (url, params) {
    return request(url, params, 'POST');
};

const patch = function (url, params) {
  return request(url, params, 'PATCH')
};

const del = function (url, params){
    return request(url, params, 'DELETE')
}

export {get, post, patch, del};


