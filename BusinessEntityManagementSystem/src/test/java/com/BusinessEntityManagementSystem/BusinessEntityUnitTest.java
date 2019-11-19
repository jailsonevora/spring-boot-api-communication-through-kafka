package com.BusinessEntityManagementSystem;

        import com.BusinessEntityManagementSystem.dataAccessObject.IBusinessEntityRepository;
        import com.BusinessEntityManagementSystem.dataTransferObject.BusinessEntity;
        import com.BusinessEntityManagementSystem.interfaces.models.IBusinessEntityModel;
        import com.BusinessEntityManagementSystem.models.*;
        import com.BusinessEntityManagementSystem.v1.controllers.BusinessEntityController;
        import com.fasterxml.jackson.databind.DeserializationFeature;
        import com.fasterxml.jackson.databind.JsonNode;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.fasterxml.jackson.databind.node.TreeTraversingParser;
        import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
        import javafx.application.Application;
        import net.joshka.junit.json.params.JsonFileSource;
        import org.junit.Before;
        import org.junit.ClassRule;
        import org.junit.jupiter.api.DisplayName;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.junit.jupiter.params.ParameterizedTest;
        import org.junit.runner.RunWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;
        import org.modelmapper.ModelMapper;
        import org.modelmapper.PropertyMap;
        import org.modelmapper.convention.MatchingStrategies;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.web.client.TestRestTemplate;
        import org.springframework.boot.web.server.LocalServerPort;
        import org.springframework.http.*;
        import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
        import org.springframework.mock.web.MockHttpServletRequest;
        import org.springframework.test.context.junit4.SpringRunner;
        import org.springframework.web.context.request.RequestContextHolder;
        import org.springframework.web.context.request.ServletRequestAttributes;

        import static org.assertj.core.api.Assertions.assertThat;
        import static org.junit.jupiter.api.Assertions.assertAll;
        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.Mockito.when;
        import javax.json.JsonObject;
        import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class BusinessEntityUnitTest {

    @InjectMocks
    BusinessEntityController businessEntityController;

    @Mock
    IBusinessEntityRepository businessEntityRepository;

    @Before
    public void setUp() throws Exception {

    }

    @ParameterizedTest
    @JsonFileSource(resources = "/business-entity-test-param.json")
    @DisplayName("provides a single object")
    void sendNewBusinessEntity(JsonObject object) throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        BusinessEntityModel entity = new BusinessEntityModel();

        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonOrgModule());
        BusinessEntity entityDTO = mapper.convertValue(object, BusinessEntity.class);

        BusinessEntityModel entity2 = Binding(entity, entityDTO);

        ResponseEntity<String> responseEntity = businessEntityController.create(entity2);

        assertAll(
                () -> assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED),
                () -> assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1")
        );
    }

    // cast class A to B with reflection
    private static void copyObject(Object src, Object dest) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        for (Field field : src.getClass().getFields()) {
            dest.getClass().getField(field.getName()).set(dest, field.get(src));
        }
    }

    // cast class DTO to MODEL manually
    private static BusinessEntityModel Binding(BusinessEntityModel<AddressModel, PartnerModel, StoreModel, AffiliatedCompanyModel, EconomicActivityCodeModel> businessEntityModel,
                                               BusinessEntity<AddressModel, PartnerModel, StoreModel, AffiliatedCompanyModel, EconomicActivityCodeModel> businessEntityDTO){

        ObjectMapper mapper = new ObjectMapper();

        //businessEntityModel.setTaxId(businessEntityDTO.getTaxId());
        //businessEntityModel.setUpdatedAt(businessEntityDTO.getUpdatedAt());
        //businessEntityModel.setNaturalId(businessEntityDTO.getNaturalId());
        //businessEntityModel.setAddress(mapper.convertValue(businessEntityDTO.getAddress(), AddressModel.class));
        businessEntityModel.setAffiliatedCompany(businessEntityDTO.getAffiliatedCompany());
        businessEntityModel.setEconomicActivityCode(businessEntityDTO.getEconomicActivityCode());
        businessEntityModel.setPartner(businessEntityDTO.getPartner());
        //businessEntityModel.setStatus(businessEntityDTO.getStatus());
        businessEntityModel.setStore(businessEntityDTO.getStore());
        //businessEntityModel.setCreatedAt(businessEntityDTO.getCreatedAt());
        //businessEntityModel.setCreatedBy(businessEntityDTO.getCreatedBy());
        //businessEntityModel.setLastModifiedBy(businessEntityDTO.getLastModifiedBy());
        //businessEntityModel.setRenewedBy(businessEntityDTO.getRenewedBy());
        //businessEntityModel.setApprovedBy(businessEntityDTO.getApprovedBy());

        return businessEntityModel;

    }

    /*ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.addMappings(new PropertyMap<BusinessEntity, BusinessEntityModel>() {
            protected void configure() {
                skip().setId(0);
                skip().setNaturalId(0);
                skip().setTaxId("");
                skip().setStatus(0);
                skip().getAddress().setId(0);
            }
        });
        BusinessEntityModel orderDTO = modelMapper.map(entityDTO, BusinessEntityModel.class);*/
}

