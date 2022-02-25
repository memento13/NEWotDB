package pknu.otDB.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COUNTRIES")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Country {

    @Id
    @Column(name = "country_id")
    private String id;

    @Column(name = "country_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @NotNull
    private Region region;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Location> locationList = new ArrayList<>();
}
