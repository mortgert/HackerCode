package com.mortgert.data.conversion;

import com.mortgert.data.dtos.UserDTO;
import com.mortgert.data.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class EntityConversion {

    private static ModelMapper modelMapper;

    private  ModelMapper mapper;

    @Autowired
    public void setMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    @PostConstruct
    private void initStaticMapper(){
        modelMapper = this.mapper;
    }




    public static User convertToEvent(UserDTO dto){
        return modelMapper.map(dto,User.class);
    }
}
