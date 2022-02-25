package pknu.otDB.entity;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INVENTORIES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Inventory {

    @EmbeddedId
    private InventoryId id;

    private int quantity;

    public Inventory(InventoryId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
