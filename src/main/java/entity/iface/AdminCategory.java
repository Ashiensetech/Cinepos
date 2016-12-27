package entity.iface;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by mi on 12/22/16.
 */
public interface AdminCategory extends AppCategory {
    @JsonView(AdminCategory.class)
    int getSortedOrder();
}
