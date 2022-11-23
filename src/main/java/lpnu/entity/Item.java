package lpnu.entity;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Item  {
    private Long itemId;
    @NonNull
    private String name;
}
