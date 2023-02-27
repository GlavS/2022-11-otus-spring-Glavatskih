import {authorsListFormatter, genresListFormatter} from './displayFunctions.js';

fetch('/api/books')
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

function commentsListFormatter(commentsArray) {
    return `${commentsArray.map((elem) =>
        `<li><span>[${elem.authorNick}] on ${new Date(elem.date).toLocaleDateString("ru-RU")}: ${elem.text}</span></li>`
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
            <a href="/book-show?id=${rowOfData.id}">${rowOfData.title}</a>
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