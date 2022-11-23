package lpnu.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

public class DTOConvertor {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T, D extends Convertable> D convertToEntity(T dto, D entity) {
        return modelMapper.map(dto, (Type) entity.getClass());
    }


    public static <T, D extends Convertable> T convertToDto(D entity, Type dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }


    public static <T, D extends Convertable> D convertFromDtoToDto(T dtoConverted, D dtoConverting) {
        return modelMapper.map(dtoConverted, (Type) dtoConverting.getClass());
    }
}