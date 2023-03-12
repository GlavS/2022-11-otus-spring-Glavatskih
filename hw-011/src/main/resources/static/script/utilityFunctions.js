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

export {getSelectedOptionsArray};