package com.example.caritas.Controller;

import com.example.caritas.Dto.CategoriaRequestDto;
import com.example.caritas.Dto.CategoriaResponseDto;
import com.example.caritas.Dto.ProdottoRequestDto;
import com.example.caritas.Dto.ProdottoResponseDto;
import com.example.caritas.Entity.Categoria;
import com.example.caritas.Entity.Prodotto;
import com.example.caritas.Entity.Role;
import com.example.caritas.Security.CostumUserDetails;
import com.example.caritas.Service.AuthenticationService;
import com.example.caritas.Service.CategoriaService;
import com.example.caritas.Service.ProdottoService;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
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
public class CategoriaControllerImpIntegrationTest {
    @Autowired
    private CategoriaService  categoriaService;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;
    private static String token;

    @Autowired
    public CategoriaControllerImpIntegrationTest(CategoriaService categoriaService, ObjectMapper mapper,MockMvc mockMvc) {
        this.categoriaService = categoriaService;
        this.mapper = mapper;
        this.mockMvc = mockMvc;
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
    void testThatCreateCategorySuccessfullyReturns201AndThenUpdateCategory() throws Exception {
        CategoriaRequestDto  categoriaRequestDto = new CategoriaRequestDto();
        categoriaRequestDto.setNome("categoriaTest");
        categoriaRequestDto.setDescrizione("descrizioneTest");
        String json = mapper.writeValueAsString(categoriaRequestDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/categorie")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .header("Authorization", "Bearer " + token)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("categoriaTest"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descrizione").value("descrizioneTest"))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        JsonNode jsonNode = mapper.readTree(responseBody);
        String categoriaId = jsonNode.get("id").asString();

        categoriaRequestDto.setNome("categoriaUpdate");
        categoriaRequestDto.setDescrizione("");
        String json2 = mapper.writeValueAsString(categoriaRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/categorie/"+categoriaId)
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .header("Authorization", "Bearer " + token)
                        .content(json2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("categoriaUpdate"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descrizione").isEmpty());

    }
    @Test
    void testThatDeleteCategorySuccessfullyReturns200() throws Exception {
        CategoriaRequestDto  categoriaRequestDto = new CategoriaRequestDto();
        categoriaRequestDto.setNome("categoriaTest");
        CategoriaResponseDto categoriaResponseDto = categoriaService.creaCategoria(categoriaRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/categorie/"+categoriaResponseDto.getId())
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }
    @Test
    void testThatDeleteCategoryAssociateWithProductSuccessfullyReturns409(@Autowired ProdottoService prodottoService) throws Exception {
        CategoriaRequestDto  categoriaRequestDto = new CategoriaRequestDto();
        categoriaRequestDto.setNome("categoriaTest");
        CategoriaResponseDto categoriaResponseDto = categoriaService.creaCategoria(categoriaRequestDto);

        ProdottoRequestDto prodottoRequestDto = new ProdottoRequestDto();
        prodottoRequestDto.setNome("prodottoTest");
        prodottoRequestDto.setDescrizione("descrizioneTest");
        prodottoRequestDto.setCategoriaId(UUID.fromString(categoriaResponseDto.getId()));
        ProdottoResponseDto prodottoResponseDto = prodottoService.creaProdotto(prodottoRequestDto);

        Prodotto prodotto = prodottoService.getProdottoById(UUID.fromString(prodottoResponseDto.getId()));
        Categoria categoria = categoriaService.getCategoriaById(UUID.fromString(categoriaResponseDto.getId()));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/categorie/"+categoriaResponseDto.getId())
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }
    @Test
    void testThatFindCategorieRetunAllCategoriesSuccessfully() throws Exception {
        CategoriaRequestDto  categoriaRequestDto = new CategoriaRequestDto();
        categoriaRequestDto.setNome("categoriaTest");
        categoriaService.creaCategoria(categoriaRequestDto);

        categoriaRequestDto.setNome("categoriaTest2");
        categoriaService.creaCategoria(categoriaRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/categorie")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].nome", Matchers.containsInAnyOrder("categoriaTest", "categoriaTest2")));

    }
}
