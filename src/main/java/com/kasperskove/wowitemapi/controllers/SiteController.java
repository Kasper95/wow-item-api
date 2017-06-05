package com.kasperskove.wowitemapi.controllers;

import com.kasperskove.wowitemapi.entities.Item;
import com.kasperskove.wowitemapi.services.ItemRepository;
import com.kasperskove.wowitemapi.services.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class SiteController {

    final private ItemRepository items;

    final private StatRepository stats;

    @Autowired
    public SiteController(ItemRepository items, StatRepository stats) {
        this.items = items;
        this.stats = stats;
    }

    @RequestMapping(path = "/items", method = RequestMethod.GET)
    public String index(Model model) {

        ArrayList<Iterable<Item>> jsonArray = new ArrayList<>();
        jsonArray.add(items.findAll());
        for (Object item : jsonArray){
            model.addAttribute("item", item);
        }
        model.addAttribute("items", jsonArray);

        return "index";
    }
}
