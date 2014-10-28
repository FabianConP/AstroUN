package modelo;

public class Detail {
    String title;
    String desc;

    public Detail(String info) {
        String[] data = info.split(":");
        title = data[0];
        desc = data[1];
    }

    public Detail(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
