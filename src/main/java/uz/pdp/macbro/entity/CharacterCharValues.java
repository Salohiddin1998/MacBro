package uz.pdp.macbro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"character_id", "character_value_id"})
})
@Entity(name = "characters_ch_values")
public class CharacterCharValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    @ManyToOne
    @JoinColumn(name = "character_value_id")
    private CharacterValue characterValue;
}
