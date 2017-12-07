package utilerias;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public final static String REGEX_CURP = 
        "[A-Z][A,E,I,O,U,X][A-Z]{2}[0-9]{2}[0-1][0-9][0-3][0-9][M,H][A-Z]{2}[B,C,D,F,G,H,J,K,L,M,N,Ñ,P,Q,R,S,T,V,W,X,Y,Z]{3}[0-9,A-Z][0-9]";

    public static boolean validarCurp(String textoCurp){
        boolean curpValido = false;

        Pattern pattern = Pattern.compile(REGEX_CURP);
        Matcher matcher = pattern.matcher(textoCurp);
        curpValido = matcher.find();

        matcher = null;
        pattern = null;

        return curpValido;
    }
}