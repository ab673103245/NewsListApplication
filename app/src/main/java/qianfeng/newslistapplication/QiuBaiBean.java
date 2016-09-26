package qianfeng.newslistapplication;

/**
 * Created by Administrator on 2016/9/1 0001.
 */
public class QiuBaiBean {

    private String id;
    private String content;

    public QiuBaiBean(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public QiuBaiBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
