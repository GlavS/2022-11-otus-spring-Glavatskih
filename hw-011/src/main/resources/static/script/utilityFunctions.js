function getSelectedOptionsArray(optionList) {

    let result = [];
    let options = optionList.options;

    Array.prototype.map.call(options, (opt) => {
        if (opt.selected) {
            result.push(opt.value);
        }
    });
    return result; // TODO: вернуть список сущностей
}

function getUrlParameterValue(paramName) {
    let paramString = window.location.search.substring(1),
        urlVariablesArray = paramString.split('&'),
        paramValueTuple,
        i;

    for (i = 0; i < urlVariablesArray.length; i++) {
        paramValueTuple = urlVariablesArray[i].split('=');

        if (paramValueTuple[0] === paramName) {
            return paramValueTuple[1] === undefined ? true : decodeURIComponent(paramValueTuple[1]);
        }
    }
    return false;
}

export {getSelectedOptionsArray, getUrlParameterValue};