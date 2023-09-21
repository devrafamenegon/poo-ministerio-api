package br.edu.faj.poo.ministerio.MinisterioService.controllers;

import br.edu.faj.poo.ministerio.MinisterioService.dtos.response.ResponseDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.secretaria.CreateSecretariaDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.secretaria.GetSecretariaDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.secretaria.UpdateSecretariaDto;
import br.edu.faj.poo.ministerio.MinisterioService.services.SecretariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretarias")
public class SecretariaController {
    private final SecretariaService service;

    @Autowired
    public SecretariaController(SecretariaService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto> create(@RequestBody CreateSecretariaDto body) {
        try {
            return ResponseEntity.ok(
                new ResponseDto("Secretaria created successfully", true, service.create(body))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @GetMapping()
    public ResponseEntity<ResponseDto> getAll(@RequestParam(name = "ministerioId", required = false) Integer ministerioId) {
        try {
            List<GetSecretariaDto> secretariaEntities = service.getAll(ministerioId);
            return ResponseEntity.ok().body(
                new ResponseDto("Secretarias found", true, secretariaEntities)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable int id) {
        try {
            GetSecretariaDto secretaria = service.getById(id);
            return ResponseEntity.ok().body(
                new ResponseDto("Secretaria found", true, secretaria)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable int id, @RequestBody UpdateSecretariaDto body) {
        try {
            GetSecretariaDto secretaria = service.update(id, body);
            return ResponseEntity.ok().body(
                    new ResponseDto("Secretaria updated successfully", true, secretaria)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable int id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().body(
                new ResponseDto("Secretaria deleted successfully", true, null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }
}
