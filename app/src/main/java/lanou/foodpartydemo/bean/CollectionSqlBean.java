package lanou.foodpartydemo.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;


public class CollectionSqlBean {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private String title;
    private String link;

    public CollectionSqlBean(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public CollectionSqlBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
