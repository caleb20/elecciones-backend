package com.caleb.elecciones.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenericResponse<T> {
    private boolean success = true;
//    private int status;
    private T data;
    private String message;

    public GenericResponse(T data) {this.data = data;}

}
