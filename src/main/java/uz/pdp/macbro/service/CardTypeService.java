package uz.pdp.macbro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.macbro.entity.CardType;
import uz.pdp.macbro.payload.Result;
import uz.pdp.macbro.repository.CardTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CardTypeService {
    @Autowired
    CardTypeRepository cardTypeRepository;

    public List<CardType> all() {
        return cardTypeRepository.findAll();
    }

    public CardType getId(Integer id) {
        Optional<CardType> optionalCardType = cardTypeRepository.findById(id);
        return optionalCardType.orElseGet(CardType::new);
    }

    public Result delete(Integer id) {
        Optional<CardType> optionalCardType = cardTypeRepository.findById(id);
        if (optionalCardType.isPresent()){
            cardTypeRepository.findById(id);
            return new Result("Card Type deleted", true);
        }
        return new Result("Card Type not found",false);
    }

    public Result add(CardType cardType) {
        boolean existsByName = cardTypeRepository.existsByName(cardType.getName());
        if (existsByName){
            return new Result("Card type already exist",false);
        }
        CardType cardType1 = new CardType();
        cardType1.setName(cardType.getName());
        cardTypeRepository.save(cardType1);
        return new Result("Card type added",true);
    }

    public Result update(Integer id, CardType cardType) {
        Optional<CardType> optionalCardType = cardTypeRepository.findById(id);
        if (optionalCardType.isEmpty()){
            return new Result("Card type not found", false);
        }
        boolean existsByName = cardTypeRepository.existsByName(cardType.getName());
        if (existsByName){
            return new Result("Card type already exist", false);
        }
        CardType updateCardType = optionalCardType.get();
        updateCardType.setName(cardType.getName());
        cardTypeRepository.save(updateCardType);
        return new Result("Card type updated", true);

    }
}
