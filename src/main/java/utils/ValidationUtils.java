package utils;

import dto.NewMatchRequestDto;
import exceptions.PlayerNameException;

public class ValidationUtils {

    public static void validate (NewMatchRequestDto newMatchRequestDto) {

        String playerOneName = newMatchRequestDto.getPlayerOneName();
        String playerTwoName = newMatchRequestDto.getPlayerTwoName();

        if(playerOneName == null || playerOneName.isBlank()){
            throw new PlayerNameException("Player №1 name can not be blank");
        }

        if(playerTwoName == null || playerTwoName.isBlank()){
            throw new PlayerNameException("Player №2 name can not be blank");
        }

    }
}
