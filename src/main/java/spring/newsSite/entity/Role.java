package spring.newsSite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.newsSite.entity.enums.Permisson;
import spring.newsSite.entity.template.AbsEntity;


import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbsEntity {

    @Column(unique = true,nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permisson> permissons;

    @Column(length = 500)
    private String description;
}
