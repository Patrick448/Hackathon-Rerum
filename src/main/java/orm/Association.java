package orm;

public class Association {
    private String type;
    private Long id;

    public Association() {}

    public Association(String type, Long id) {
        this.type = type;
        this.id = id;
    }

    public Association(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
