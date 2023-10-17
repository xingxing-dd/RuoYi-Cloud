package com.ruoyi.common.websocket.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Data
@ToString
@NoArgsConstructor
public class RegisterMessage implements Serializable {

    private String type;

    private String token;

    private Map<String, Object> params;

    @JsonCreator
    public static RegisterMessage create(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, RegisterMessage.class);
    }

}
