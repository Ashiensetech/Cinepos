package nonentity.photo;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mi on 10/7/15.
 */
public class Picture {
    private PictureDetails original;
    private ArrayList<PictureDetails> thumb;


    public Picture() {
        this.original = new PictureDetails();
        this.thumb = new ArrayList<PictureDetails>();
    }

    public PictureDetails getOriginal() {
        return original;
    }

    public void setOriginal(PictureDetails original) {
        this.original = original;
    }

    public ArrayList<PictureDetails> getThumb() {
        return thumb;
    }

    public void setThumb(ArrayList<PictureDetails> thumb) {
        this.thumb = thumb;
    }
}