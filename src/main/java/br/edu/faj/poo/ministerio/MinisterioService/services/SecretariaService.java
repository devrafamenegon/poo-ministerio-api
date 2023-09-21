package br.edu.faj.poo.ministerio.MinisterioService.services;

import br.edu.faj.poo.ministerio.MinisterioService.dtos.secretaria.CreateSecretariaDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.secretaria.GetSecretariaDto;
import br.edu.faj.poo.ministerio.MinisterioService.dtos.secretaria.UpdateSecretariaDto;
import br.edu.faj.poo.ministerio.MinisterioService.entities.Secretaria;
import br.edu.faj.poo.ministerio.MinisterioService.repositories.MinisterioRepository;
import br.edu.faj.poo.ministerio.MinisterioService.repositories.SecretariaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecretariaService {
    private final SecretariaRepository repository;
    private final MinisterioRepository ministerioRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SecretariaService(
            SecretariaRepository repository,
            MinisterioRepository ministerioRepository,
            ModelMapper modelMapper
    ) {
        this.repository = repository;
        this.ministerioRepository = ministerioRepository;
        this.modelMapper = modelMapper;
    }

    public GetSecretariaDto create(CreateSecretariaDto createSecretariaDto) throws Exception {
        if (createSecretariaDto.getNome() == null || createSecretariaDto.getNome().trim().isEmpty()) {
            throw new Exception("Field nome is required.");
        }

        if (createSecretariaDto.getNumFuncionarios() == null) {
            throw new Exception("Field numFuncionarios is required.");
        }

        if (createSecretariaDto.getVerba() == null) {
            throw new Exception("Field verba is required.");
        }

        if (createSecretariaDto.getMinisterioId() == null) {
            throw new Exception("Field ministerioId is required.");
        }

        if (ministerioRepository.getById(createSecretariaDto.getMinisterioId()) == null) {
            throw new Exception("Ministerio with this id not exists.");
        }

        Secretaria secretaria = new Secretaria();
        secretaria.setNome(createSecretariaDto.getNome());
        secretaria.setNumFuncionarios(createSecretariaDto.getNumFuncionarios());
        secretaria.setVerba(createSecretariaDto.getVerba());
        secretaria.setMinisterioId(createSecretariaDto.getMinisterioId());

        secretaria = repository.create(secretaria);

        return modelMapper.map(secretaria, GetSecretariaDto.class);
    }

    public List<GetSecretariaDto> getAll(Integer ministerioId) throws Exception {
        List<Secretaria> secretarias = repository.getAll(ministerioId);

        if (secretarias.isEmpty()) {
            throw new Exception("Secretarias not found");
        }

        return secretarias.stream().map(
            secretaria -> modelMapper.map(secretaria, GetSecretariaDto.class)
        ).collect(Collectors.toList());
    }

    public GetSecretariaDto getById(Integer id) throws Exception {
        Secretaria secretaria = repository.getById(id);
        if (secretaria == null) {
            throw new Exception("Secretaria not found");
        }
        return modelMapper.map(secretaria, GetSecretariaDto.class);
    }

    public GetSecretariaDto update(Integer id, UpdateSecretariaDto updateSecretariaDto) throws Exception {
        if (updateSecretariaDto.getNome() == null || updateSecretariaDto.getNome().trim().isEmpty()) {
            throw new Exception("Field nome is required.");
        }

        if (updateSecretariaDto.getNumFuncionarios() == null) {
            throw new Exception("Field numFuncionarios is required.");
        }

        if (updateSecretariaDto.getVerba() == null) {
            throw new Exception("Field verba is required.");
        }

        if (updateSecretariaDto.getMinisterioId() == null) {
            throw new Exception("Field ministerioId is required.");
        }
        if (ministerioRepository.getById(updateSecretariaDto.getMinisterioId()) == null) {
            throw new Exception("Ministerio with this id not exists.");
        }

        if (repository.getById(id) == null) {
            throw new Exception("Secretaria not found.");
        }

        Secretaria secretaria = new Secretaria();
        secretaria.setId(id);
        secretaria.setNome(updateSecretariaDto.getNome());
        secretaria.setNumFuncionarios(updateSecretariaDto.getNumFuncionarios());
        secretaria.setVerba(updateSecretariaDto.getVerba());
        secretaria.setMinisterioId(updateSecretariaDto.getMinisterioId());

        secretaria = repository.update(secretaria);

        return modelMapper.map(secretaria, GetSecretariaDto.class);
    }

    public boolean delete(Integer id) throws Exception {
        if (repository.getById(id) == null) {
            throw new Exception("Secretaria not found.");
        }

        repository.delete(id);

        return true;
    }
}
