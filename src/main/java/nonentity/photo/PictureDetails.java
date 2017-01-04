package nonentity.photo;

/**
 * Created by mi on 10/7/15.
 */
public class PictureDetails {

    private String path;
    private String type;
    private PictureSize size;

    public PictureDetails() {
        this.path = "";
        this.type = "";
        this.size = new PictureSize();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PictureSize getSize() {
        return size;
    }

    public void setSize(PictureSize size) {
        this.size = size;
    }
}
