
package com.restorant_springdemo.restorant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChefDto {

    private Long id;

    @NotBlank(message = "chef.name.required")
    @Size(min = 2, max = 100, message = "chef.name.size")
    private String name;

    @NotBlank(message = "chef.specialization.required")
    @Size(min = 2, max = 100, message = "chef.specialization.size")
    private String specialization;

    private String logoPath;

    @Pattern(
            regexp = "^(https?://.+)?$",
            message = "chef.facebook.invalid"
    )
    private String faceLink;

    @Pattern(
            regexp = "^(https?://.+)?$",
            message = "chef.twitter.invalid"
    )
    private String twitterLink;

    @Pattern(
            regexp = "^(https?://.+)?$",
            message = "chef.instagram.invalid"
    )
    private String instagramLink;
}
