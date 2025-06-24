package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMatchRequestDto {

    private String firstPlayerName;

    private String secondPlayerName;

}
