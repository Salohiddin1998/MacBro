package uz.pdp.macbro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CardDto {

    private String bankName;

    private BigInteger cardNumber;

    private Double balance;

    private String term;

    private Integer cardTypeId;

    private Integer userId;
}
