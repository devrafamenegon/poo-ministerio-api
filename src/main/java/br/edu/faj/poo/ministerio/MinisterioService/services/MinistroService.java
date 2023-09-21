package br.edu.faj.poo.ministerio.MinisterioService.services;

import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministro.CreateMinistroDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministro.GetMinistroDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.ministro.UpdateMinistroDto;
import br.edu.faj.poo.ministerio.MinisterioService.entities.Ministro;
import br.edu.faj.poo.ministerio.MinisterioService.repositories.MinisterioRepository;
import br.edu.faj.poo.ministerio.MinisterioService.repositories.MinistroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MinistroService {
    private final MinistroRepository repository;
    private final MinisterioRepository ministerioRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MinistroService(
            MinistroRepository repository,
            MinisterioRepository ministerioRepository,
            ModelMapper modelMapper
    ) {
        this.repository = repository;
        this.ministerioRepository = ministerioRepository;
        this.modelMapper = modelMapper;
    }

    public GetMinistroDto create(CreateMinistroDto createMinistroDto) throws Exception {
        if (createMinistroDto.getNome() == null || createMinistroDto.getNome().trim().isEmpty()) {
            throw new Exception("Field nome is required.");
        }

        if (createMinistroDto.getSalario() == null) {
            throw new Exception("Field salario is required.");
        }

        if (createMinistroDto.getPartido() == null) {
            throw new Exception("Field partido is required.");
        }

        if (createMinistroDto.getDataEntrada() == null) {
            throw new Exception("Field dataEntrada is required.");
        }

        if (createMinistroDto.getMinisterioId() == null) {
            throw new Exception("Field ministerioId is required.");
        }

        if (ministerioRepository.getById(createMinistroDto.getMinisterioId()) == null) {
            throw new Exception("Ministerio with this id not exists.");
        }

        Ministro ministro = new Ministro();
        ministro.setNome(createMinistroDto.getNome());
        ministro.setSalario(createMinistroDto.getSalario());
        ministro.setPartido(createMinistroDto.getPartido());
        ministro.setDataEntrada(createMinistroDto.getDataEntrada());
        ministro.setDataSaida(createMinistroDto.getDataSaida());
        ministro.setMinisterioId(createMinistroDto.getMinisterioId());

        ministro = repository.create(ministro);

        return modelMapper.map(ministro, GetMinistroDto.class);
    }

    public List<GetMinistroDto> getAll() throws Exception {
        List<Ministro> ministros = repository.getAll();

        if (ministros.isEmpty()) {
            throw new Exception("Ministros not found");
        }

        return ministros.stream().map(
            ministro -> modelMapper.map(ministro, GetMinistroDto.class)
        ).collect(Collectors.toList());
    }

    public GetMinistroDto getById(Integer id) throws Exception {
        Ministro ministro = repository.getById(id);
        if (ministro == null) {
            throw new Exception("Ministro not found");
        }
        return modelMapper.map(ministro, GetMinistroDto.class);
    }

    public GetMinistroDto update(Integer id, UpdateMinistroDto updateMinistroDto) throws Exception {
        if (updateMinistroDto.getNome() == null || updateMinistroDto.getNome().trim().isEmpty()) {
            throw new Exception("Field nome is required.");
        }

        if (updateMinistroDto.getSalario() == null) {
            throw new Exception("Field salario is required.");
        }

        if (updateMinistroDto.getPartido() == null) {
            throw new Exception("Field partido is required.");
        }

        if (updateMinistroDto.getDataEntrada() == null) {
            throw new Exception("Field dataEntrada is required.");
        }

        if (updateMinistroDto.getMinisterioId() == null) {
            throw new Exception("Field ministerioId is required.");
        }

        if (ministerioRepository.getById(updateMinistroDto.getMinisterioId()) == null) {
            throw new Exception("Ministerio with this id not exists.");
        }

        Ministro ministro = new Ministro();
        ministro.setNome(updateMinistroDto.getNome());
        ministro.setSalario(updateMinistroDto.getSalario());
        ministro.setPartido(updateMinistroDto.getPartido());
        ministro.setDataEntrada(updateMinistroDto.getDataEntrada());
        ministro.setDataSaida(updateMinistroDto.getDataSaida());
        ministro.setMinisterioId(updateMinistroDto.getMinisterioId());

        ministro = repository.update(id, ministro);

        return modelMapper.map(ministro, GetMinistroDto.class);
    }

    public boolean delete(Integer id) throws Exception {
        if (repository.getById(id) == null) {
            throw new Exception("Ministro not found.");
        }

        repository.delete(id);

        return true;
    }
}
