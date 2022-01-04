package com.mi.bookvillage.common.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.Map;

public class APIResponse extends MappingJacksonValue {

    @JsonIgnore
    private Map<String, Object> header;

    public APIResponse(Object value) {
        super(value);
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);
    }


}
