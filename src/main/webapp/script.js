function placeOrder() {

    let item = document.getElementById("item").value;
    let quantity = document.getElementById("quantity").value;

    fetch("order", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: "item=" + item + "&quantity=" + quantity
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById("result").innerText = data;
    });
}
