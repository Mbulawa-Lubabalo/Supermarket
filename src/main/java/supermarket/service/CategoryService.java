package supermarket.service;

public class CategoryService {

    private Long id;
    private String name;

    public CategoryService() {}
    public CategoryService(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {return this.id;}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
