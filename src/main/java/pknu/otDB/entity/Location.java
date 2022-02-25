package pknu.otDB.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LOCATIONS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Location {

    @Id
    @Column(name = "location_id")
    private Long id;

    private String address;
    private String postal_code;
    private String city;
    private String State;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @NotNull
    private Country country;

    @OneToMany(mappedBy = "location",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Warehouse> warehouseList = new ArrayList<>();


}
