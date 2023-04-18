package spring.newsSite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.newsSite.entity.enums.Permisson;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    @NotBlank
    private String name;

    private String description;

    @NotEmpty
    private List<Permisson> permissons;

}
