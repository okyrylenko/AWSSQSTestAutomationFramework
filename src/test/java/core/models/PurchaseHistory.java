package core.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PurchaseHistory {
    private List<Product> products;
    private String email;
}
