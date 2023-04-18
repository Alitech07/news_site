package spring.newsSite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    @NotNull
    private String title;

    @NotNull
    private String body;

    @NotNull
    private String url;
}
