package entity.iface;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Basic;
import javax.persistence.Column;

/**
 * Created by mi on 12/22/16.
 */
public interface AppCategory {
    @JsonView(AppCategory.class)
    public int getId();
    @JsonView(AppCategory.class)
    public void setId(int id);

    @JsonView(AppCategory.class)
    public String getName();
    @JsonView(AppCategory.class)
    public void setName(String name);
}
