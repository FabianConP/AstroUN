package modelo;

public class Element {
    private int id;
    private String name;
    private String wikiLink;
    private String mapsLink;

    public Element(int id, String info){
        String[] data = info.split("\\*");
        this.id = id;
        this.name = data[0];
        this.wikiLink = data[1];
        this.mapsLink = data[2];
    }

    public Element(int id, String name, String wikiLink, String mapsLink) {
        this.id = id;
        this.name = name;
        this.wikiLink = wikiLink;
        this.mapsLink = mapsLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getMapsLink() {
        return mapsLink;
    }

    public void setMapsLink(String mapsLink) {
        this.mapsLink = mapsLink;
    }

    @Override
    public String toString() {
        return "Element{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wikiLink='" + wikiLink + '\'' +
                ", mapsLink='" + mapsLink + '\'' +
                '}';
    }
}
