package main.java.pt.ipp.estg.ed.exceptions;

public class UnknownPathException extends Exception {
    public UnknownPathException(){
        super();
    }

    public UnknownPathException(String message){
        super(message);
    }
}
