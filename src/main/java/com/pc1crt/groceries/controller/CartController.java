package com.pc1crt.groceries.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pc1crt.groceries.model.Cart;
import com.pc1crt.groceries.model.Product;
import com.pc1crt.groceries.service.CartManager;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartManager cartManager;

    @RequestMapping("/add")
    public String add(HttpSession session, @RequestParam("id") Product product,
                             @RequestParam(value = "qty", required = false, defaultValue = "1") int qty){
        Cart cart = cartManager.getCart(session);
        cart.addItem(product, qty);
        return "cart";
    }

    @RequestMapping("/remove")
    public String remove(HttpSession session, @RequestParam("id") Product product){
        Cart cart = cartManager.getCart(session);
        cart.removeItem(product);
        return "cart";
    }

    @RequestMapping("/update")
    public String update(HttpSession session, @RequestParam("id") Product product, @RequestParam("qty") int qty){
        Cart cart = cartManager.getCart(session);
        cart.updateItem(product, qty);
        return "cart";
    }
}
