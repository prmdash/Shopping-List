package primo.shoppinglist.data.entities;

import primo.shoppinglist.data.entities.enums.CategoryName;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    private CategoryName name;
    private String description;

    public CategoryEntity() {
    }

    @Enumerated(EnumType.STRING)
    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
