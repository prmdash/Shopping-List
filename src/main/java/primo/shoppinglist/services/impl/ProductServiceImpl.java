package primo.shoppinglist.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import primo.shoppinglist.data.entities.ProductEntity;
import primo.shoppinglist.data.services.ProductServiceModel;
import primo.shoppinglist.repositories.ProductRepository;
import primo.shoppinglist.services.CategoryService;
import primo.shoppinglist.services.ProductService;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }


    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        ProductEntity productEntity = modelMapper.map(productServiceModel, ProductEntity.class);

        productEntity.setCategory(categoryService.getCategoryByName(productServiceModel.getCategory()));

        productRepository.save(productEntity);
    }

    @Override
    public BigDecimal getTotalSum() {
        return productRepository.findTotalPriceOfAllProducts();
    }
}
