package uz.pdp.macbro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.macbro.dto.CardDto;
import uz.pdp.macbro.entity.Card;
import uz.pdp.macbro.payload.Result;
import uz.pdp.macbro.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping
    public List<Card> allC(){
        return cardService.all();
    }

    @GetMapping("/{id}")
    public Card getId(@PathVariable Integer id){
        return cardService.getId(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return cardService.delete(id);
    }

//    @PostMapping
//    public Result add(@RequestBody CardDto cardDto){
//        return cardService.add(cardDto);
//    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody CardDto cardDto){
        return cardService.update(id,cardDto);
    }

}
