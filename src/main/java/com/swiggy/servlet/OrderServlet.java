package com.swiggy.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private static final Map<String, Integer> MENU_PRICES = new HashMap<>();

    static {
        MENU_PRICES.put("Burger", 120);
        MENU_PRICES.put("Pizza", 250);
        MENU_PRICES.put("Biryani", 200);
        MENU_PRICES.put("Dosa", 80);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String item = req.getParameter("item");
        String quantityParam = req.getParameter("quantity");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            int quantity = Integer.parseInt(quantityParam);
            if (quantity <= 0) throw new NumberFormatException();

            int price = MENU_PRICES.getOrDefault(item, 0);
            int total = price * quantity;

            // Build JSON response manually
            String jsonResponse = "{"
                    + "\"status\":\"success\","
                    + "\"item\":\"" + item + "\","
                    + "\"quantity\":" + quantity + ","
                    + "\"price\":" + price + ","
                    + "\"total\":" + total
                    + "}";

            resp.getWriter().write(jsonResponse);

        } catch (NumberFormatException e) {
            String errorResponse = "{"
                    + "\"status\":\"error\","
                    + "\"message\":\"Invalid quantity.\""
                    + "}";
            resp.getWriter().write(errorResponse);
        }
    }
}
