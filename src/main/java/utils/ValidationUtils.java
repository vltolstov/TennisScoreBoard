package utils;

import dto.NewMatchRequestDto;
import exceptions.PageNumberException;
import exceptions.PaginationException;
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

        if (!firstPlayerName.matches("[a-zA-Z0-9а-яА-ЯёЁ]+") || !secondPlayerName.matches("[a-zA-Z0-9а-яА-ЯёЁ]+")) {
            throw new PlayerNameException("Player name must contain only letters and digits");
        }

    }

    public static void validate(String page){
        if(page != null && Integer.parseInt(page) < 0) {
            throw new PageNumberException("Page number can not be less than 1");
        }
    }

    public static void validate(int page, int totalPages){
        if(page > totalPages && totalPages > 0) {
            throw new PaginationException("Page number can not be greater than total pages");
        }
    }

}
