package pknu.otDB.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REGIONS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Region {

    @Id
    @Column(name = "region_id")
    private Long id;

    @Column(name = "region_name")
    private String name;

    @OneToMany(mappedBy = "region",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Country> countryList = new ArrayList<>();
}
