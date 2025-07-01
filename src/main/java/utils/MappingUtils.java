package utils;

import dto.MatchRequestDto;
import dto.PlayerDto;
import models.Match;
import models.Player;
import org.modelmapper.ModelMapper;

public class MappingUtils {

    private static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();

        MODEL_MAPPER.typeMap(Match.class, MatchRequestDto.class).addMappings(mapper -> {
            mapper.map(Match::getId, MatchRequestDto::setId);
            mapper.map(src -> src.getFirstPlayer().getName(), MatchRequestDto::setFirstPlayerName);
            mapper.map(src -> src.getSecondPlayer().getName(), MatchRequestDto::setSecondPlayerName);
            mapper.map(src -> src.getScore().getSetsScore(), MatchRequestDto::setScore);
            mapper.map(src-> src.getWinner().getName(), MatchRequestDto::setWinnerName);
        });
    }

    public static Player convertToEntity(PlayerDto playerDto){
        return MODEL_MAPPER.map(playerDto, Player.class);
    }

    public static PlayerDto convertToDto(Player player){
        return MODEL_MAPPER.map(player, PlayerDto.class);
    }

    public static MatchRequestDto convertToDto(Match match){
        return MODEL_MAPPER.map(match, MatchRequestDto.class);
    }

}
