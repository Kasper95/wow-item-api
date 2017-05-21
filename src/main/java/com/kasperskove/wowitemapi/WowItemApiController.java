package com.kasperskove.wowitemapi;

import com.kasperskove.wowitemapi.entities.Item;
import com.kasperskove.wowitemapi.services.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class WowItemApiController {

    @Autowired
    private
    ItemRepository items;

    @CrossOrigin
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ArrayList<Iterable<Item>> index(Model model) {

        ArrayList<Iterable<Item>> jsonArray = new ArrayList<>();
        jsonArray.add(items.findAll());
        model.addAttribute("jsonArray", jsonArray);
        return jsonArray;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String items() {

        for (int i = 0; i < 100; i++) {

            String itemUri = "https://us.api.battle.net/wow/item/" + i + "?locale=en_US&apikey=yz98b2qzp8qfp62axbgrmzsuzjkwbgc8";
            RestTemplate restTemplate = new RestTemplate();
            Item itemJson = restTemplate.getForObject(itemUri, Item.class);

            try { items.save(itemJson);
            } catch (NullPointerException | HttpClientErrorException ex) {
                ex.printStackTrace();
            }
        }
        return "redirect:/";
    }
}