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
    private String name;
    private String status;
    private List<String> photoUrls;
    private List<Tag> tags;


    public static Pet createPet(Integer id, String name) {
        return builder()
            .id(id)
            .name(name)
            .status("available")
            .build();
    }

    public static Pet getPet(Integer id, String name, String status, List<String> photoUrls, List<Tag> tags) {
        return builder()
            .id(id)
            .name(name)
            .status(status)
            .photoUrls(photoUrls)
            .tags(tags)
            .build();
    }

    public static Pet createPetWithoutId(String name) {
        return builder()
            .name(name)
            .build();
    }
}
