package br.com.api.synclearn.Utils;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;


public class NullOrEmpty {

    public static boolean isNullOrEmpty (Object obj){
        if(obj instanceof Collection<?>) {
            return obj == null || ((Collection<?>) obj).isEmpty();
        }
        else if(obj instanceof String) {
            return ((String) obj).isBlank();
        }
        else if(obj instanceof InputStream) {
            return obj == null;
        }else {
            return false;
        }
    }

}
