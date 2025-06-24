package utils;

import dto.NewMatchRequestDto;
import exceptions.PlayerNameException;

public class ValidationUtils {

    public static void validate (NewMatchRequestDto newMatchRequestDto) {

        String firstPlayerName = newMatchRequestDto.getFirstPlayerName();
        String secondPlayerName = newMatchRequestDto.getSecondPlayerName();

        if(firstPlayerName == null || firstPlayerName.isBlank()){
            throw new PlayerNameException("Player №1 name can not be blank");
        }

        if(secondPlayerName == null || secondPlayerName.isBlank()){
            throw new PlayerNameException("Player №2 name can not be blank");
        }

    }
}
