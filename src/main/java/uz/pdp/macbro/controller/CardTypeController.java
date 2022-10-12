package uz.pdp.macbro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.macbro.entity.CardType;
import uz.pdp.macbro.payload.Result;
import uz.pdp.macbro.service.CardTypeService;

import java.util.List;

@RestController
@RequestMapping("/CardType")
public class CardTypeController {
    @Autowired
    CardTypeService cardTypeService;

    @GetMapping
    public List<CardType> all(){
        return cardTypeService.all();
    }

    @GetMapping("/{id}")
    public CardType getId(@PathVariable Integer id){
        return cardTypeService.getId(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return cardTypeService.delete(id);
    }

    @PostMapping
    public Result add(@RequestBody CardType cardType){
        return cardTypeService.add(cardType);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody CardType cardType){
        return cardTypeService.update(id,cardType);
    }

}
