package utils;

import dto.PlayerDto;
import models.Player;
import org.modelmapper.ModelMapper;

public class MappingUtils {

    private static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
    }

    public static Player convertToEntity(PlayerDto playerDto){
        return MODEL_MAPPER.map(playerDto, Player.class);
    }

}
