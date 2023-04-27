loadPage();
function loadPage() {
  fetch("http://localhost:8080/Shop/getallProduct")
    .then((data) => {
      return data.json();
    })
    .then((objectData) => {
      renderTable(objectData);
    });
}

function sort() {
  fetch("http://localhost:8080/Shop/getallProduct")
    .then((data) => {
      return data.json();
    })
    .then((objectData) => {
      objectData.sort((a, b) => {
        const dateA = new Date(a.launchYear);
        const dateB = new Date(b.launchYear);
        return dateB - dateA;
      });
      renderTable(objectData);
    });
}

function selectOption() {
  var dropdown = document.getElementById("active");
  var selectedOption = dropdown.options[dropdown.selectedIndex].value;
  // Call different functions based on selected option
  switch (selectedOption) {
    case "All":
      all();
      break;
    case "Active":
      active();
      break;
    case "InActive":
      inActive();
      break;
  }
}

function all() {
  fetch("http://localhost:8080/Shop/getallProduct")
    .then((data) => {
      return data.json();
    })
    .then((objectData) => {
      renderTable(objectData);
    });
}

function active() {
  fetch("http://localhost:8080/Shop/getallActiveProduct")
    .then((data) => {
      return data.json();
    })
    .then((objectData) => {
      renderTable(objectData);
    });
}

function inActive() {
  fetch("http://localhost:8080/Shop/getallInActiveProduct")
    .then((data) => {
      return data.json();
    })
    .then((objectData) => {
      renderTable(objectData);
    });

}

function renderTable(objectData) {
  let tableData = "";
  objectData.map((values) => {
    tableData += `<tr>
            <td style="text-align: center; border: 2px solid;" >${values.id}</td>
            <td style="text-align: center; border: 2px solid;" >${values.name}</td>
            <td style="text-align: center; border: 2px solid;" >${values.description}</td>
            <td style="text-align: center; border: 2px solid;" >${values.refurbished}</td>
            <td style="text-align: center; border: 2px solid;" >${values.launchYear}</td>
            <td style="text-align: center; border: 2px solid;" >${values.productType.name}</td>
            <td style="text-align: center; border: 2px solid;" >${values.mfgDate}</td>
            <td style="text-align: center; border: 2px solid;" >${values.loatNo}</td>
            <td style="text-align: center; border: 2px solid;" >${values.qty}</td>
            <td style="text-align: center; border: 2px solid;">
            <button onclick="editData(this)" class="btn btn-secondary">Edit</button>
            <button onclick="deleteData(this)" class="btn btn-danger">Delete</button>
            </td>
            </tr>
            `;
  });
  document.getElementById("data").innerHTML = tableData;
}

function deleteData(td) {

  var row = td.parentElement.parentElement;
  var id = row.cells[0].textContent;
  console.log(id);
  if (confirm("Are you sure you want to delete this data?")) {
    var url = 'http://localhost:8080/Shop/deleteProduct/' + id;
    var fetchOptions = {
      method: 'Delete'
    };
    // Send the fetch request
    fetch(url, fetchOptions).then(response => { loadPage(); });
  }
}

function editData(td) {
  var row = td.parentElement.parentElement;
  var id = row.cells[0].textContent;
  console.log(id);
  url = 'addProduct.html?id=' + encodeURIComponent(id);
  window.location.href = url;
}








