package com.senai.reservei.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatarData {
    public Date formatar(String data){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return formato.parse(data);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }

    }
}
