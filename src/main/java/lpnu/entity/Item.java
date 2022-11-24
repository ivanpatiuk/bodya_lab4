package lpnu.entity;

import lombok.*;
import lpnu.config.Convertable;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Item implements Convertable {
    private Long itemId;
    @NonNull
    private String name;
}
