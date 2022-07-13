package br.com.dbc.vemser.pessoaapi.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public class PropertyReader {

    private String ambiente;

    public String getAmbiente() {
        return ambiente;
    }
}

