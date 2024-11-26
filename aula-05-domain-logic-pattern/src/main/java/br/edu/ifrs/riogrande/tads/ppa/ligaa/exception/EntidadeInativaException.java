package br.edu.ifrs.riogrande.tads.ppa.ligaa.exception;

public class EntidadeInativaException extends RuntimeException {

    public EntidadeInativaException() {
        super("A entidade não pode ser operacionalizada porque está inativa");
    }

}
