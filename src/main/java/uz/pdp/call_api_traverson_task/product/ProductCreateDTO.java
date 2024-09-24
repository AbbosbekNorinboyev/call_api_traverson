package uz.pdp.call_api_traverson_task.product;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductCreateDTO {
    private String name;
    private String description;
    private Double price;
    private Date date;
}
