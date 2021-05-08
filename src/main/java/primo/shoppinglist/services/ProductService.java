package primo.shoppinglist.services;

import primo.shoppinglist.data.services.ProductServiceModel;

import java.math.BigDecimal;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    BigDecimal getTotalSum();
}
