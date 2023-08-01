package com.example.exbeginner.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.exbeginner.domain.Item;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/exam06")
public class ShoppingCartController {
    @Autowired
    private HttpSession session;
    @Autowired
    private ServletContext application;
    
    
    @GetMapping("")
    public String index(Model model) {
        List<Item> itemNamePrice = (List<Item>) application.getAttribute("itemNamePrice");
        if(itemNamePrice==null) {
            Item item1 = new Item();
            Item item2 = new Item();
            Item item3 = new Item();
            item1.setName("手帳ノート");
            item1.setPrice(1000);
            item2.setName("文房具セット");
            item2.setPrice(1500);
            item3.setName("ファイル");
            item3.setPrice(2000);
            itemNamePrice = new LinkedList<>();
            itemNamePrice.add(0,item1);
            itemNamePrice.add(1,item2);
            itemNamePrice.add(2,item3);
            application.setAttribute("itemNamePrice", itemNamePrice);
        }
        List<Item> cartItems = (List<Item>) session.getAttribute("cartItems");
        if(cartItems==null) {
            cartItems = new LinkedList<>();
            session.setAttribute("cartItems", cartItems);
        }
        int totalPrice = calcTotalPrice(cartItems);
        model.addAttribute("totalPrice",totalPrice);
        return "item-and-cart";
    }
    public int calcTotalPrice(List<Item> cartItems) {
        int totalPrice = 0;
        List<Item> itemNamePrice = (List<Item>) application.getAttribute("itemNamePrice");
        for(Item itemCart:cartItems) {
            for(Item item:itemNamePrice) {
                if(item.getName().equals(itemCart.getName())) {
                    totalPrice += item.getPrice();
                    break;
                }
            }
        }
        return totalPrice;
    }
    @PostMapping("/add")
    public String inCart(@RequestParam("index") int index) {
        LinkedList<Item> itemNamePrice = (LinkedList<Item>) application.getAttribute("itemNamePrice");

        // ショッピングカート(sessionスコープ内のリスト)を取得
        LinkedList<Item> cartItems = (LinkedList<Item>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new LinkedList<>();
            session.setAttribute("cartItems", cartItems);
        }

        // 送られてきたindex番号を基に商品を取得し、ショッピングカートに追加
        if (itemNamePrice != null && index >= 0 && index < itemNamePrice.size()) {
            Item selectedItem = itemNamePrice.get(index);
            cartItems.add(selectedItem);
        }
        session.setAttribute("cartItems", cartItems);
        return "redirect:/exam06";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("index") int index) {
        // ショッピングカート(sessionスコープ内のリスト)を取得
        List<Item> cartItems = (List<Item>) session.getAttribute("cartItems");
        if (cartItems != null && index >= 0 && index < cartItems.size()) {
            // 送られてきたindex番号を基に商品を削除
            cartItems.remove(index);
        }
        session.setAttribute("cartItems", cartItems);
        return "redirect:/exam06";
    }
    
}
