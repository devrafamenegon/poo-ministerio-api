package br.edu.faj.poo.ministerio.MinisterioService.controllers;

import br.edu.faj.poo.ministerio.MinisterioService.dtos.response.ResponseDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministro.CreateMinistroDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministro.GetMinistroDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministro.UpdateMinistroDto;
import br.edu.faj.poo.ministerio.MinisterioService.services.MinistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ministros")
@CrossOrigin(origins = "*")
public class MinistroController {
    private final MinistroService service;

    @Autowired
    public MinistroController(MinistroService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto> create(@RequestBody CreateMinistroDto body) {
        try {
            return ResponseEntity.ok(
                new ResponseDto("Ministro created successfully", true, service.create(body))
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @GetMapping()
    public ResponseEntity<ResponseDto> getAll() {
        try {
            List<GetMinistroDto> ministroEntities = service.getAll();
            return ResponseEntity.ok().body(
                new ResponseDto("Ministros found", true, ministroEntities)
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
            GetMinistroDto ministro = service.getById(id);
            return ResponseEntity.ok().body(
                new ResponseDto("Ministro found", true, ministro)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable int id, @RequestBody UpdateMinistroDto body) {
        try {
            GetMinistroDto ministro = service.update(id, body);
            return ResponseEntity.ok().body(
                    new ResponseDto("Ministro updated successfully", true, ministro)
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
                new ResponseDto("Ministro deleted successfully", true, null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(e.getMessage(), false, null)
            );
        }
    }
}
