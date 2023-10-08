package hinatazaka46.smash.dto;

public class CatDto {
    private long id;
    private String type;
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
// Getter、Setter、コンストラクタ、toString等は省略
}
