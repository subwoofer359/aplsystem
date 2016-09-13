/**
 * To sort a HTML table
 * @access public
 * @param {Object} table HTML table element
 * @param {string||number} columnToSortBy the column of the table to be sorted 
 */
function tableSort(table, columnToSortBy) {
    "use strict";

    var thead,
        tbody,
        row,
        rows,
        arr,
        i,
        j,
        rowlen,
        rlen,
        cellLength,
        cells,
        cell;

    //Presuming the table header element in a thead element
    if (table.tagName.toLowerCase() !== 'table') {
        while (table.tagName.toLowerCase() !== 'table' && table.parentNode !== null) {
            table = table.parentNode;
        }
    }

    //Check to see if columnToSortBy is a string and set columnToSortBy to the corresponding column index
    if (typeof columnToSortBy === 'string') {
        if (table.tHead) {
            thead = table.tHead.rows;
            if (thead.length > 0) {
                row = thead[0];
                for (i = 0, rlen = row.cells.length; i < rlen; i += 1) {
                    if (row.cells[i].innerHTML.indexOf(columnToSortBy) !== -1) {
                        columnToSortBy = i;
                        break;
                    }
                }
            }
        }
    }

    tbody = table.tBodies;
    //Check that there is at least one tbody defined
    if (tbody.length < 1) {
        return;
    }

    tbody = tbody[0];

    rowlen = tbody.rows.length;

    //Check the number of row in the table if the table is empty just return to caller
    if (rowlen < 1) {
        return;
    }

    rows = tbody.rows;

    arr = [];
    for (i = 0; i < rowlen; i += 1) {
        cells = rows[i].cells;
        arr[i] = [];
        for (j = 0, cellLength = cells.length; j < cellLength; j += 1) {
            arr[i][j] = cells[j].innerHTML;
        }
    }

    arr.sort(function (a, b) {
        if (a[columnToSortBy] < b[columnToSortBy]) {
            return -1;
        }

        if (a[columnToSortBy] > b[columnToSortBy]) {
            return 1;
        }
        return 0;
    });

    for (i = 0; i < rowlen; i += 1) {
        for (j = 0; j < rlen; j += j) {
            cell = rows[i].cells[j];
            cell.innerHTML = arr[i][j];
        }
    }
}