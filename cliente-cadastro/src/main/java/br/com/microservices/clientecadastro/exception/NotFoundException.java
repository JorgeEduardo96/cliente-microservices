package br.com.microservices.clientecadastro.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String mensagem) {
        super(mensagem);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
