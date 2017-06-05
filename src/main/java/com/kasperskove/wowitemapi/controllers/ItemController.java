package com.kasperskove.wowitemapi.controllers;

import com.kasperskove.wowitemapi.entities.Item;
import com.kasperskove.wowitemapi.services.ItemRepository;
import com.kasperskove.wowitemapi.services.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@CrossOrigin
public class ItemController {

    final private ItemRepository items;

    final private StatRepository stats;

    @Autowired
    public ItemController(ItemRepository items, StatRepository stats) {
        this.items = items;
        this.stats = stats;
    }

    @CrossOrigin
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ArrayList<Iterable<Item>> index(Model model) {

        ArrayList<Iterable<Item>> jsonArray = new ArrayList<>();

        jsonArray.add(items.findAll());
        model.addAttribute("jsonArray", jsonArray);

        return jsonArray;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String items(){

        int itemInput = 1600;

        for (int i = 0; i < 1000; i++) {

            // KEY RATE LIMIT 100 Calls per second || 36,000 Calls per hour
            String itemUri = "https://us.api.battle.net/wow/item/"+ itemInput +
                    "?locale=en_US&apikey=6ffedrnuqxbvcfk2329d8hy3bs2u2w4m";

            RestTemplate restTemplate = new RestTemplate();
            Item itemJson;
            try {
                itemJson = restTemplate.getForObject(itemUri, Item.class);
                if (Objects.equals(itemJson.getInventoryType(), "20")) {
                    itemJson.setInventoryType("5"); }
                if (Objects.equals(itemJson.getImage(), null)) {
                    itemJson.setImage("inv_jewelry_ring_14");
                }
                if (!Objects.equals(itemJson.getInventoryType(), "0")) {
                    try {
                        items.save(itemJson);
                    } catch (NullPointerException ex) {
                        // ex.printStackTrace();
                    }
                }
            } catch (HttpClientErrorException ex) {
                // ex.printStackTrace();
            }
            itemInput++;
            i++;
        }

        System.out.println("POST REQUEST 200 OK");

        return "redirect:/";
    }

    private String stringify(String statName) {

        String statString;

        switch (statName) {

            case "0":
                statString = "Mana";
                break;
            case "1":
                statString = "Health";
                break;
            case "3":
                statString = "Agility";
                break;
            case "4":
                statString = "Strength";
                break;
            case "5":
                statString = "Intellect";
                break;
            case "6":
                statString = "Spirit";
                break;
            case "7":
                statString = "Stamina";
                break;
            case "12":
                statString = "Defense";
                break;
            case "13":
                statString = "Dodge";
                break;
            case "14":
                statString = "Parry";
                break;
            case "15":
                statString = "Block";
                break;
            case "16":
            case "17":
            case "18":
                statString = "Hit";
                break;
            case "19":
            case "20":
            case "21":
                statString = "Critical Strike";
                break;
            case "22":
            case "23":
            case "24":
                statString = "Hit Avoidance";
                break;
            case "25":
            case "26":
            case "27":
                statString = "Critical Strike Avoidance";
                break;
            case "28":
            case "29":
            case "30":
                statString = "Haste";
                break;
            case "31":
                statString = "Hit";
                break;
            case "32":
                statString = "Critical Strike";
                break;
            case "33":
                statString = "Hit Avoidance";
                break;
            case "34":
                statString = "Critical Strike Avoidance";
                break;
            case "35":
                statString = "PvP Resilience";
                break;
            default:
                statString = "Default";
                break;
        }

        return statString;
    }
}