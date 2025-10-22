package dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Tag;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private Integer id;
    private Category category;
    private String name;
    private String status;
    private List<String> photoUrls;
    private List<Tags> tags;
}
