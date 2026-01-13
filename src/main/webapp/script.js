function placeOrder() {
    let itemSelect = document.getElementById("item");
    let item = itemSelect.value;
    let price = itemSelect.options[itemSelect.selectedIndex].getAttribute("data-price");
    let quantity = parseInt(document.getElementById("quantity").value);

    let total = price * quantity;

    // Display locally first
    document.getElementById("result").innerText = `You ordered ${quantity} x ${item}\nTotal: ₹${total}`;

    // Send order to server
    fetch("order", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `item=${item}&quantity=${quantity}`
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById("result").innerText += `\n\nServer Response: ${data}`;
    })
    .catch(err => {
        document.getElementById("result").innerText += `\n\nError: Could not place order`;
        console.error(err);
    });
}
