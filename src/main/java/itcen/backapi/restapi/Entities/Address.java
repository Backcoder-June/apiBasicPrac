package itcen.backapi.restapi.Entities;

import javax.validation.constraints.NotBlank;

public class Address {

    @NotBlank
    private String street;

    @NotBlank
    private String postCode;

}
