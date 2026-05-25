package br.com.serratec.trabalhofinalapi.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroResposta> tratarResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        ErroResposta erro = new ErroResposta(
                status.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroResposta> tratarRegraNegocio(
            RegraNegocioException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErroResposta erro = new ErroResposta(
                status.value(),
                "Erro de regra de negócio",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarErroValidacao(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<CampoErroResposta> campos = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::converterCampoErro)
                .toList();

        ErroResposta erro = new ErroResposta(
                status.value(),
                "Erro de validação",
                "Existem campos inválidos na requisição",
                request.getRequestURI(),
                campos);

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> tratarErroGenerico(
            Exception ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErroResposta erro = new ErroResposta(
                status.value(),
                "Erro interno do servidor",
                "Ocorreu um erro inesperado",
                request.getRequestURI());

        return ResponseEntity.status(status).body(erro);
    }

    private CampoErroResposta converterCampoErro(FieldError fieldError) {
        return new CampoErroResposta(
                fieldError.getField(),
                fieldError.getDefaultMessage());
    }
}