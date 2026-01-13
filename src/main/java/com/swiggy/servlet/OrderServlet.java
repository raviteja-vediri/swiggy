package com.swiggy.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String item = req.getParameter("item");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        int price = switch (item) {
            case "Burger" -> 120;
            case "Pizza" -> 250;
            case "Biryani" -> 200;
            case "Dosa" -> 80;
            default -> 0;
        };

        int total = price * quantity;

        resp.setContentType("text/plain");
        resp.getWriter().println(
                "Order Confirmed!\n" +
                        "Item: " + item + "\n" +
                        "Quantity: " + quantity + "\n" +
                        "Total Price: ₹" + total
        );
    }
}
