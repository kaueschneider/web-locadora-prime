package br.com.foursys.locadora.util;

import java.util.Date;

/**
 * Classe responsavel por armazenar os métodos de validação de dados
 *
 * @author Kaue Schneider Nobrega
 * @since 27/04/2021
 * @version 1.0
 */
public class Valida {
    
    
    /*
    * método para verificar se o campo é diferente de vazio ou nulo
    */
    
	public static boolean isEmptyOrNull(String args) {
        return (args.trim().equals("") || args == null);
    }

    public static boolean isInteger(String args){
        try {
            Integer.parseInt(args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isDouble(String args){
    	try {
    		Double.parseDouble(args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isDoubleZero(double num){
    	if(num <= 0) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean isIntZero(int num){
    	if(num <= 0) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean isDateNull(Date args) {
        return (args == null);
    }
    
}
