package org.example.web.init;

import org.example.web.DTO.OfferDTO;
import org.example.web.mappers.*;
import org.example.web.models.*;
import org.example.web.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BrandService brandService;
    private final BrandMapper brandMapper;
    private final ModelService modelService;
    private final ModelsMapper modelsMapper;
    private final UserEntityService userEntityService;
    private final OfferService offerService;
    private final OfferMapper offerMapper;
    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;
    private final UserEntityMapper userEntityMapper;

    private final PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class.getName());

    @Autowired
    public DataInitializer(BrandService brandService, BrandMapper brandMapper, ModelService modelService, ModelsMapper modelsMapper, UserEntityService userEntityService, OfferService offerService, OfferMapper offerMapper, UserRoleService userRoleService, UserRoleMapper userRoleMapper, UserEntityMapper userEntityMapper, PasswordEncoder passwordEncoder) {
        this.brandService = brandService;
        this.brandMapper = brandMapper;
        this.modelService = modelService;
        this.modelsMapper = modelsMapper;
        this.userEntityService = userEntityService;
        this.offerService = offerService;
        this.offerMapper = offerMapper;
        this.userRoleService = userRoleService;
        this.userRoleMapper = userRoleMapper;
        this.userEntityMapper = userEntityMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        UserRole userRole = new UserRole();
        userRole.setName("ADMIN");
        userRole.setRoleType(UserRole.RoleType.ADMIN);

        UserRole userRole1 = new UserRole();
        userRole1.setName("USER");
        userRole1.setRoleType(UserRole.RoleType.USER);
        //UserRoleDTO userRoleDTO = userRoleService.saveUserRole(userRoleMapper.toDTO(userRole));

        UserEntity userEntity = new UserEntity();
        userEntity.setModified(LocalDate.now());
        userEntity.setCreated(LocalDate.now());
        userEntity.setUserRole(userRole1);
        userEntity.setUsername("user");
        userEntity.getEmail();
        userEntity.setLastName("Userovich");
        userEntity.setImageURL("URL....");
        userEntity.setFirstName("User");
        userEntity.setAge(22);
        userEntity.setPassword(passwordEncoder.encode("56789"));
        userEntity.setActive(true);
        //UserEntityDTO userEntityDTO = userEntityService.saveUserEntity(userEntityMapper.toDTO(userEntity));
        // LOGGER.warn(userEntityDTO.toString());

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setModified(LocalDate.now());

        userEntity1.setCreated(LocalDate.now());
        userEntity1.setUserRole(userRole);
        userEntity1.setUsername("admin");
        userEntity1.setLastName("Adminovich");
        userEntity1.setImageURL("URL123");
        userEntity1.setFirstName("Admin");
        userEntity1.setAge(30);
        userEntity1.setPassword(passwordEncoder.encode("12345"));
        userEntity1.setActive(true);

        Brand brand = new Brand();
        brand.setName("Tesla");
        brand.setCreated(LocalDate.now());
        brand.setModified(LocalDate.now());

        Brand brand2 = new Brand();
        brand2.setName("BMW");
        brand2.setCreated(LocalDate.now());
        brand2.setModified(LocalDate.now());
//        BrandDTO brandDTO = brandService.saveBrand(brandMapper.toDTO(brand));

        Model model = new Model();
        model.setName("X");
        model.setCategoryType(Model.CategoryType.CAR);
        model.setImageURL("https://www.example.com/images/model123.jpg");
        model.setStartYear(2015);
        model.setEndYear(2035);
        model.setCreated(LocalDate.now());
        model.setModified(LocalDate.now());
        model.setBrand(brand);
//        ModelDTO modelDTO = modelService.saveModel(modelsMapper.toDTO(model));
//        LOGGER.warn(modelDTO.toString());

        Model model1 = new Model();
        model1.setName("M4");
        model1.setCategoryType(Model.CategoryType.CAR);
        model1.setImageURL("https://www.example.com/images/model32243.jpg");
        model.setStartYear(2013);
        model.setEndYear(2030);
        model1.setCreated(LocalDate.now());
        model1.setModified(LocalDate.now());
        model1.setBrand(brand2);

        Offer offer = new Offer();
        offer.setDescription("Right");
        offer.setEngineType(Offer.EngineType.HYBRID);
        offer.setImageURL("https://www.example.com/images/offer1.jpg");
        offer.setMileage(5000);
        offer.setPrice(13_500_000.0);
        offer.setTransmissionType(Offer.TransmissionType.AUTOMATIC);
        offer.setYear(LocalDate.now());
        offer.setCreated(LocalDate.now());
        offer.setModified(LocalDate.now());
        offer.setModel(model);
        offer.setSeller(userEntity);
        //OfferDTO offerDTO = offerService.saveOffer(offerMapper.toDTO(offer));
        //LOGGER.warn(offerDTO.toString());

        Offer offer1 = new Offer();
        offer1.setDescription("-");
        offer1.setEngineType(Offer.EngineType.HYBRID);
        offer1.setImageURL("https://www.example.com/images/offer2.jpg");
        offer1.setMileage(6000);
        offer1.setPrice(13_000_000.0);
        offer1.setTransmissionType(Offer.TransmissionType.AUTOMATIC);
        offer1.setYear(LocalDate.now());
        offer1.setCreated(LocalDate.now());
        offer1.setModified(LocalDate.now());
        offer1.setModel(model1);
        offer1.setSeller(userEntity1);
        OfferDTO offerDTO = offerService.saveOffer(offerMapper.toDTO(offer));
        OfferDTO offerDTO1 = offerService.saveOffer(offerMapper.toDTO(offer1));
        //LOGGER.warn(offerDTO.toString());

    }
}