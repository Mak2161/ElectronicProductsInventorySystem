// Call the function to handle action
handle();

function handle() {
  document.addEventListener("DOMContentLoaded", function () {
    if (fetchId() == null) {
      document.getElementById("submit").style.display = "block";
      document.getElementById("update").style.display = "none";
    } else {
      editData()
      document.getElementById("submit").style.display = "none";
      document.getElementById("update").style.display = "block";
    }
  });
}

function submitData() {
  var name = document.getElementById('name').value;
  var description = document.getElementById('description').value;
  var product_type_id = parseInt(document.getElementById('product_type').value);
  var isRefurbished = document.querySelector('input[name="Refurbished"]:checked').value;
  var launch_year = parseInt(document.getElementById('launch_year').value);
  var mfgDate = document.getElementById('mfgDate').value;
  var qty = parseInt(document.getElementById('qty').value);
  var loat_number = parseInt(document.getElementById('loat_number').value);

  jasonObject = {
    "name": name,
    "description": description,
    "refurbished": isRefurbished,
    "launchYear": launch_year,
    "mfgDate": mfgDate,
    "productType": {
      "id": product_type_id
    },
    "qty": qty,
    "loatNo": loat_number
  }
  var str = JSON.stringify(jasonObject);
  console.log(str);
  var url = 'http://localhost:8080/Shop/addProduct';
  var fetchOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: str
  };

  fetch(url, fetchOptions).then(response => JSON.stringify(response))
}

function editData() {
  //fetch data
  fetch("http://localhost:8080/Shop/getProduct/" + fetchId())
    .then((data) => {
      return data.json();
    }).then((obj) => {
      let name = obj.name;
      let description = obj.description;
      let refurbished = obj.refurbished;
      let launch_year = obj.launchYear;
      let mfgDate = obj.mfgDate;
      let product_type = obj.productType.id;
      let qty = obj.qty;
      let loat_number = obj.loatNo;

      document.getElementById("name").value = name;
      document.getElementById("description").value = description;
      document.querySelector(`input[name="Refurbished"][value="${obj.refurbished}"]`).checked = true;
      // document.querySelector('input[name="Refurbished"]').value = refurbished;
      document.getElementById("launch_year").value = launch_year;
      document.getElementById('mfgDate').value = mfgDate;
      document.getElementById('product_type').value = product_type;
      document.getElementById('qty').value = qty;
      document.getElementById('loat_number').value = loat_number;

    })
}

function putData() {
  var name = document.getElementById('name').value;
  var description = document.getElementById('description').value;
  var product_type_id = parseInt(document.getElementById('product_type').value);
  var isRefurbished = document.querySelector('input[name="Refurbished"]:checked').value;
  var launch_year = parseInt(document.getElementById('launch_year').value);
  var mfgDate = document.getElementById('mfgDate').value;
  var qty = parseInt(document.getElementById('qty').value);
  var loat_number = parseInt(document.getElementById('loat_number').value);

  jasonObject = {
    "name": name,
    "description": description,
    "refurbished": isRefurbished,
    "launchYear": launch_year,
    "mfgDate": mfgDate,
    "productType": {
      "id": product_type_id
    },
    "qty": qty,
    "loatNo": loat_number
  }
  console.log(jasonObject)
  var str = JSON.stringify(jasonObject);
  var url = 'http://localhost:8080/Shop/updateProduct/' + fetchId();
  var fetchOptions = {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: str
  };
  fetch(url, fetchOptions).then(response => JSON.stringify(response))
}

function resetFrom() {
  document.getElementById("submit").style.display = "block";
  document.getElementById("update").style.display = "none";
}

//fetch id
function fetchId() {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  let id = urlParams.get('id');
  return id;
}

//fetch dropdown(product_type)
function fillDropdown() {
  // Fetch data from server
  fetch('http://localhost:8080/medicine/allForm')
    .then((response) => response.json())
    .then((data) => {
      // Get reference to the select element
      const dropdown = document.getElementById('dropdown');
      // Loop through the data and create an option element for each item
      data.forEach((item) => {
        const option = document.createElement('option');
        option.value = item.id;
        option.text = item.form;
        // Append option to select element
        dropdown.appendChild(option);
      });
    })
    .catch((error) => console.error(error));
}

// to check Launch Year can not be after the MfgDate
function yearValidation() {
  var launchYear = document.getElementById("launch_year");
  var mfgDate = document.getElementById("mfgDate");

  var launchYear = parseInt(launchYear.value);
  var mfgDate = new Date(mfgDate.value);

  if (mfgDate.getFullYear() < launchYear) {
    alert("Launch Year cannot be after the MfgDate.");
    return false;
  }
  return true;
}






