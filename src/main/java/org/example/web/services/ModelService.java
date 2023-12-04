package org.example.web.services;

import org.example.web.DTO.ModelDTO;
import org.example.web.mappers.ModelsMapper;
import org.example.web.models.Model;
import org.example.web.repositories.BrandRepository;
import org.example.web.repositories.ModelRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    private final ModelsMapper modelsMapper;

    private final BrandRepository brandRepository;

    public ModelService(ModelRepository modelRepository, ModelsMapper modelsMapper, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.modelsMapper = modelsMapper;
        this.brandRepository = brandRepository;
    }

    public List<ModelDTO> getAllModels() {
        return modelRepository.findAll()
                .stream()
                .map(modelsMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ModelDTO getModelById(UUID id) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        if (modelOptional.isPresent()) {
            return modelsMapper.toDTO(modelOptional.get());
        } else {
            throw new NoSuchElementException("Model with id " + id + " not found");
        }
    }

    public ModelDTO updateModel(ModelDTO updatedModel, UUID id) {
        Model model = modelRepository.findById(id).orElseThrow(NoSuchElementException::new);
        model.setCategoryType(Model.CategoryType.valueOf(updatedModel.getCategoryType()));
        Model updateModel = modelRepository.save(model);
        return modelsMapper.toDTO(updateModel);
//        return modelRepository.findById(id)
//                .map(model -> {
//                    if (updatedModel.getImageUrl() != null) {
//                        model.setImageURL(updatedModel.getImageUrl());
//                    }
//                    if (updatedModel.getStartYear() != null) {
//                        model.setStartYear(updatedModel.getStartYear());
//                    }
//                    if (updatedModel.getCreated() != null) {
//                        model.setCreated(updatedModel.getCreated());
//                    }
//                    if (updatedModel.getModified() != null) {
//                        model.setModified(updatedModel.getModified());
//                    }
//                    return  modelsMapper.toDTO(modelRepository.save(model));
//                })
//                .orElseThrow(() -> new EntityNotFoundException("Model with id " + id + " not found"));
    }

    public ModelDTO saveModel(ModelDTO model) {
        //Model temp = modelsMapper.toEntity(model);
        //Brand brand = brandRepository.findById(model.getBrandId())
        //      .orElseThrow(() -> new EntityNotFoundException("Brand with id " + model.getBrandId() + " not found"));
        //temp.setBrand(brand);
        Model save = modelRepository.save(modelsMapper.toEntity(model));
        return modelsMapper.toDTO(save);
    }

    public void deleteModel(UUID id) {
        try {
            modelRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Error: there is no element with " + id + " id");
        }
    }
}