package com.pitang.desafiopitangapi.dto;

import com.pitang.desafiopitangapi.model.User;

public record ResponseDTO (User user, String token){
}
