package com.example.caritas.Controller;

import com.example.caritas.Dto.CategoriaRequestDto;
import com.example.caritas.Dto.CategoriaResponseDto;
import com.example.caritas.Dto.ProdottoRequestDto;
import com.example.caritas.Entity.Role;
import com.example.caritas.Security.CostumUserDetails;
import com.example.caritas.Service.AuthenticationService;
import com.example.caritas.Service.CategoriaService;
import com.example.caritas.Service.MagazzinoService;
import com.example.caritas.Service.ProdottoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ProdottoControllerImpIntegrationTest {

    @Autowired
    private ProdottoService prodottoService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    private static String token;

    @Autowired
    public ProdottoControllerImpIntegrationTest(CategoriaService categoriaService, ObjectMapper mapper,MockMvc mockMvc, ProdottoService prodottoService) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.categoriaService = categoriaService;
        this.prodottoService = prodottoService;
    }
    @BeforeAll
    static void setup(@Autowired AuthenticationService authenticationService) throws Exception {
        CostumUserDetails costumUserDetails = new CostumUserDetails(
                "testAdmin",
                "testPassword",
                Role.ROLE_ADMIN.toString(),
                null
        );
        token = authenticationService.generateToken(costumUserDetails);
    }
    @Test
    void testThatCreateProductSuccessfullyReturns201AndThenUpdateProduct() throws Exception {
        CategoriaRequestDto categoriaRequestDto = new CategoriaRequestDto();
        categoriaRequestDto.setNome("categoriaTest");
        categoriaRequestDto.setDescrizione("descrizioneTest");
        CategoriaResponseDto categoriaResponseDto = categoriaService.creaCategoria(categoriaRequestDto);

        ProdottoRequestDto prodottoRequestDto = new ProdottoRequestDto();
        prodottoRequestDto.setNome("nomeTest");
        prodottoRequestDto.setDescrizione("descrizioneTest");
        prodottoRequestDto.setCategoriaId(UUID.fromString(categoriaResponseDto.getId()));
        String json = mapper.writeValueAsString(prodottoRequestDto);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/prodotti")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .header("Authorization", "Bearer " + token)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("nomeTest"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descrizione").value("descrizioneTest"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoria.id").value(categoriaResponseDto.getId()))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        JsonNode jsonNode = mapper.readTree(responseBody);
        String prodottoId = jsonNode.get("id").asString();

        prodottoRequestDto.setNome("prodottoTestUpdate");
        prodottoRequestDto.setDescrizione("");
        String json2 = mapper.writeValueAsString(prodottoRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/prodotti/"+prodottoId)
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .header("Authorization", "Bearer " + token)
                        .content(json2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("prodottoTestUpdate"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descrizione").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoria.id").value(categoriaResponseDto.getId()));

    }
}
