package itcen.backapi.restapi.Entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class Card {

    @NotBlank
    private String cardNo;

    @NotBlank
    private LocalDate cardDate;
}
