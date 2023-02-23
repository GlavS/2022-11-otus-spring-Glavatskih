fetch('http://localhost:8080/api/book/all')
    //fetch('/api/book/all')
    .then(data => {
        return data.json()
    })
    .then(dataInJson => {
        let tableRowData = '';
        dataInJson.map(rowOfData => {
            tableRowData += tableFormatTemplateString(rowOfData);
        })
        insertData("table-body", tableRowData);
        console.log('success!');
    })
    .catch((error) => {
        console.log(error);
    });

function insertData(id, data) {
    document.getElementById(id).innerHTML = data;
}

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

function commentsListFormatter(commentsArray) {
    return `${commentsArray.map((elem) => 
        `<li>[${elem.authorNick}] on ${new Date(elem.date).toLocaleDateString("ru-RU")}: ${elem.text}</li>`
    ).join('')}`;
}

function tableFormatTemplateString(rowOfData) {
    let authorsArray = rowOfData['authors'];
    let genresArray = rowOfData['genres'];
    let commentsArray = rowOfData['comments'];
    return `
    <tr>
        <td>
             ${rowOfData.id}
        </td>
        <td>
            <ul>
                ${authorsListFormatter(authorsArray)}
            </ul>
        </td>
        <td>
            <a href="/book-edit?id=${rowOfData.id}">${rowOfData.title}</a>
        </td>
        <td>
            <ul>
                ${genresListFormatter(genresArray)}
            </ul>
        </td>
        <td>
            <ul>
                ${commentsListFormatter(commentsArray)}
            </ul>  
        </td>
    </tr>
      `
}