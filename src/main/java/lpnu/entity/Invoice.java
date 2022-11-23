package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.config.Convertable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invoice implements Convertable {
    private Long invoiceId;
    private Order order;
}
