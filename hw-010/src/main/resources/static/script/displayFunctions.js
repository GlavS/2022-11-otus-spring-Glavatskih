
function authorsListFormatter(authorsArray) {
    return `${authorsArray.map((elem) =>
        `<li>${elem.name} ${elem.surname}</li>`
    ).join('')}`;
}

function genresListFormatter(genresArray) {
    return `${genresArray.map((elem) =>
        `<li>${elem.name}</li>`
    ).join('')}`;
}



export {authorsListFormatter, genresListFormatter};