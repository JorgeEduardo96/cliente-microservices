package br.com.microservices.clientecadastro.exception;

import br.com.microservices.clientecadastro.validaton.LevelSenha;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("#{'${cliente-cadastro.senha-level}'.split(',')}")
    private List<LevelSenha.RequisitosSenha> requisitosSenha;

    private final MessageSource messageSource;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(NotFoundException ex,
                                                                  WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();
        Problem problem = createProblemBuilder(status, "Recurso não encontrado", detail).build();

        log.error("ApiExceptionHandler - handleEntidadeNaoEncontradaException -> {}", ex.getMessage());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult,
                                                            HttpHeaders headers, HttpStatusCode status,
                                                            WebRequest request) {
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    if (ex instanceof MethodArgumentNotValidException &&
                            message.contains("senha") && message.contains(":requisitos")) {
                        String requisitos = requisitosSenha.stream().map(
                                LevelSenha.RequisitosSenha::getDescricao).collect(Collectors.joining(", "));

                        message = message.replace(":requisitos", requisitos);
                    }

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) name = ((FieldError) objectError).getField();

                    return Problem.Object.builder()
                            .name(WordUtils.capitalize(name))
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        Problem problem = createProblemBuilder(status, "Dados inválidos", detail)
                .objects(problemObjects)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status, String title, String detail) {
        return Problem.builder()
                .status(status.value())
                .title(title)
                .timestamp(OffsetDateTime.now())
                .detail(detail);
    }

}
