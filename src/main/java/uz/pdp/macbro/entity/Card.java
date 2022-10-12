package uz.pdp.macbro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bankName;

    private String cardNumber;

    private double balance;

    private String term;

    @ManyToOne
    private CardType cardType;

    @ManyToOne
    private User user;

    public Card(String bankName, String cardNumber, double balance, String term, CardType cardType) {
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.term = term;
        this.cardType = cardType;
    }
}
