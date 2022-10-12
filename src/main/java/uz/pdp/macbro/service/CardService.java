package uz.pdp.macbro.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.macbro.dto.CardDto;
import uz.pdp.macbro.entity.Card;
import uz.pdp.macbro.entity.CardType;
import uz.pdp.macbro.entity.User;
import uz.pdp.macbro.payload.Result;
import uz.pdp.macbro.repository.CardRepository;
import uz.pdp.macbro.repository.CardTypeRepository;
import uz.pdp.macbro.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardTypeRepository cardTypeRepository;

    @Autowired
    UserRepository userRepository;

    public List<Card> all() {
        return cardRepository.findAll();
    }

    public Card getId(Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        return optionalCard.orElseGet(Card::new);
    }

    public Result delete(Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent()){
            cardRepository.deleteById(id);
            return new Result("Card deleted",true);
        }
        return new Result("Card not found", false);
    }

    public Result add(@NotNull CardDto cardDto) {
        Optional<User> optionalUser = userRepository.findById(cardDto.getUserId());
        if (optionalUser.isEmpty()){
            return new Result("User not found",false);
        }
        User user = optionalUser.get();
        Optional<CardType> optionalCardType = cardTypeRepository.findById(cardDto.getCardTypeId());
        if (optionalCardType.isEmpty()){
            return new Result("Card type not found", false);
        }
        boolean existsByCardNumber = cardRepository.existsByCardNumber(cardDto.getCardNumber());
        if (existsByCardNumber){
            return new Result("Card Number already exist",false);
        }

        Card card = new Card(
                cardDto.getBankName(),
                cardDto.getCardNumber(),
                cardDto.getBalance(),
                cardDto.getTerm(),
                optionalCardType.get()
                );
        Card saveCard = cardRepository.save(card);
        List<Card> userCards = user.getCards();
        userCards.add(saveCard);
        userRepository.save(user);

        return new Result("Card added",true);
    }

    public Result update(Integer id, CardDto cardDto) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isEmpty()){
            return new Result("Card not found",false);
        }
        if (cardDto.getCardTypeId() != null){
            Optional<CardType> optionalCardType = cardTypeRepository.findById(cardDto.getCardTypeId());
            if (optionalCardType.isEmpty()){
                return new Result("Card type not found", false);
            }
        }
        Card card = optionalCard.get();
        if (cardDto.getBankName() != null){
            card.setBankName(cardDto.getBankName());
        }
        if (cardDto.getCardNumber() != null){
            card.setCardNumber(cardDto.getCardNumber());
        }
        if (cardDto.getBalance() != null){
           card.setBalance(cardDto.getBalance());
       }
        if (cardDto.getTerm() != null){
           card.setTerm(cardDto.getTerm());
       }
        cardRepository.save(card);
        return new Result("Card updated",true);


    }
}
