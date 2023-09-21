package br.edu.faj.poo.ministerio.MinisterioService.services;

import br.edu.faj.poo.ministerio.MinisterioService.dtos.presidente.CreatePresidenteDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.presidente.GetPresidenteDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.presidente.UpdatePresidenteDto;
import br.edu.faj.poo.ministerio.MinisterioService.entities.Presidente;
import br.edu.faj.poo.ministerio.MinisterioService.repositories.MinisterioRepository;
import br.edu.faj.poo.ministerio.MinisterioService.repositories.PresidenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PresidenteService {
    private final PresidenteRepository repository;
    private final MinisterioRepository ministerioRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PresidenteService(
            PresidenteRepository repository,
            MinisterioRepository ministerioRepository,
            ModelMapper modelMapper
    ) {
        this.repository = repository;
        this.ministerioRepository = ministerioRepository;
        this.modelMapper = modelMapper;
    }

    public GetPresidenteDto create(CreatePresidenteDto createPresidenteDto) throws Exception {
        if (createPresidenteDto.getNome() == null || createPresidenteDto.getNome().trim().isEmpty()) {
            throw new Exception("Field nome is required.");
        }

        if (createPresidenteDto.getSalario() == null) {
            throw new Exception("Field salario is required.");
        }

        if (createPresidenteDto.getPartido() == null) {
            throw new Exception("Field partido is required.");
        }

        if (createPresidenteDto.getDataEntrada() == null) {
            throw new Exception("Field dataEntrada is required.");
        }

        if (createPresidenteDto.getVerba() == null) {
            throw new Exception("Field verba is required.");
        }

        Presidente presidente = new Presidente();
        presidente.setNome(createPresidenteDto.getNome());
        presidente.setSalario(createPresidenteDto.getSalario());
        presidente.setPartido(createPresidenteDto.getPartido());
        presidente.setDataEntrada(createPresidenteDto.getDataEntrada());
        presidente.setDataSaida(createPresidenteDto.getDataSaida());
        presidente.setVerba(createPresidenteDto.getVerba());

        presidente = repository.create(presidente);

        return modelMapper.map(presidente, GetPresidenteDto.class);
    }

    public List<GetPresidenteDto> getAll() throws Exception {
        List<Presidente> presidentes = repository.getAll();

        if (presidentes.isEmpty()) {
            throw new Exception("Presidentes not found");
        }

        return presidentes.stream().map(
            presidente -> modelMapper.map(presidente, GetPresidenteDto.class)
        ).collect(Collectors.toList());
    }

    public GetPresidenteDto getById(Integer id) throws Exception {
        Presidente presidente = repository.getById(id);
        if (presidente == null) {
            throw new Exception("Presidente not found");
        }
        return modelMapper.map(presidente, GetPresidenteDto.class);
    }

    public GetPresidenteDto update(Integer id, UpdatePresidenteDto updatePresidenteDto) throws Exception {
        if (updatePresidenteDto.getNome() == null || updatePresidenteDto.getNome().trim().isEmpty()) {
            throw new Exception("Field nome is required.");
        }

        if (updatePresidenteDto.getSalario() == null) {
            throw new Exception("Field salario is required.");
        }

        if (updatePresidenteDto.getPartido() == null) {
            throw new Exception("Field partido is required.");
        }

        if (updatePresidenteDto.getDataEntrada() == null) {
            throw new Exception("Field dataEntrada is required.");
        }

        if (updatePresidenteDto.getVerba() == null) {
            throw new Exception("Field verba is required.");
        }

        Presidente presidente = new Presidente();
        presidente.setNome(updatePresidenteDto.getNome());
        presidente.setSalario(updatePresidenteDto.getSalario());
        presidente.setPartido(updatePresidenteDto.getPartido());
        presidente.setDataEntrada(updatePresidenteDto.getDataEntrada());
        presidente.setDataSaida(updatePresidenteDto.getDataSaida());
        presidente.setVerba(updatePresidenteDto.getVerba());

        presidente = repository.update(id, presidente);

        return modelMapper.map(presidente, GetPresidenteDto.class);
    }

    public boolean delete(Integer id) throws Exception {
        if (repository.getById(id) == null) {
            throw new Exception("Presidente not found.");
        }

        repository.delete(id);

        return true;
    }
}
