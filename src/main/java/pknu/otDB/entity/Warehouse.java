package pknu.otDB.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "WAREHOUSES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Warehouse {

    @Id
    @Column(name = "warehouse_id")
    private Long id;

    @Column(name = "warehouse_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @NotNull
    private Location location;

    @OneToMany(mappedBy = "id.warehouse",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Inventory> inventoryList = new ArrayList<>();
}
