package utils;

import dto.NewMatchRequestDto;
import exceptions.PlayerNameException;

public class ValidationUtils {

    public static void validate (NewMatchRequestDto newMatchRequestDto) {

        String playerOneName = newMatchRequestDto.getPlayerOneName();
        String playerTwoName = newMatchRequestDto.getPlayerTwoName();

        if(playerOneName == null || playerOneName.isBlank()){
            throw new PlayerNameException("Имя игрока №1 не может быть пустым");
        }

        if(playerTwoName == null || playerTwoName.isBlank()){
            throw new PlayerNameException("Имя игрока №2 не может быть пустым");
        }

    }
}
