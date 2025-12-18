package com.example.caritas.Controller;

import com.example.caritas.Dto.*;
import com.example.caritas.Entity.Role;
import com.example.caritas.Security.CostumUserDetails;
import com.example.caritas.Service.AuthenticationService;
import com.example.caritas.Service.MagazzinoService;
import com.example.caritas.Service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class UserControllerImpIntegrationTest {
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private AuthenticationService authenticationService;
    private UserService userService;
    private MagazzinoService magazzinoService;
    private static String token;
    @Autowired
    public UserControllerImpIntegrationTest(MockMvc mockMvc, ObjectMapper mapper, AuthenticationService authenticationService, UserService userService, MagazzinoService magazzinoService) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.authenticationService  = authenticationService;
        this.userService = userService;
        this.magazzinoService = magazzinoService;
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
    void testThatCreateUserSuccessfullyReturns201() throws Exception {
        UserRequestDto dto = new UserRequestDto();
        dto.setUsername("testUser");
        dto.setPassword("admin123");
        dto.setRole(Role.ROLE_VOLONTARIO);

        String json = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .header("Authorization", "Bearer " + token)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("ROLE_VOLONTARIO"));

    }
    @Test
    void testThatDeleteUserSuccessfullyReturns200() throws Exception {
        UserRequestDto dto = new UserRequestDto();
        dto.setUsername("testUser");
        dto.setPassword("admin123");
        dto.setRole(Role.ROLE_VOLONTARIO);
        UserResponseDto responseDto = userService.createUser(dto);


        mockMvc.perform(MockMvcRequestBuilders.delete("/user/"+responseDto.getId())
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("ROLE_VOLONTARIO"));

    }
    @Test
    void testThatUserAddMagazineSuccessfullyReturns200andThenDeleteUser() throws Exception {
        UserRequestDto userDto = new UserRequestDto();
        userDto.setUsername("testUser");
        userDto.setPassword("admin123");
        userDto.setRole(Role.ROLE_VOLONTARIO);
        UserResponseDto userResponseDto = userService.createUser(userDto);
        MagazzinoRequestDto magazzinoRequestDto = new MagazzinoRequestDto();
        magazzinoRequestDto.setNome("Magazzino");
        MagazzinoResponseDto magazzinoResponseDto = magazzinoService.createMagazzino(magazzinoRequestDto);
        IdDto idDto = new IdDto();
        idDto.setId(UUID.fromString(magazzinoResponseDto.getId()));
        String json = mapper.writeValueAsString(idDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/"+userResponseDto.getId()+"/magazzino")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(json)
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.magazzini").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.magazzini",hasItem(magazzinoResponseDto.getId())));

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/"+userResponseDto.getId())
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("ROLE_VOLONTARIO"));

    }
    @Test
    void testThatUserRemoveMagazineSuccessfullyReturns200() throws Exception {
        UserRequestDto userDto = new UserRequestDto();
        userDto.setUsername("testUser");
        userDto.setPassword("admin123");
        userDto.setRole(Role.ROLE_VOLONTARIO);
        UserResponseDto userResponseDto = userService.createUser(userDto);
        MagazzinoRequestDto magazzinoRequestDto = new MagazzinoRequestDto();
        magazzinoRequestDto.setNome("Magazzino");
        MagazzinoResponseDto magazzinoResponseDto = magazzinoService.createMagazzino(magazzinoRequestDto);
        IdDto idDto = new IdDto();
        idDto.setId(UUID.fromString(magazzinoResponseDto.getId()));
        String json = mapper.writeValueAsString(idDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/"+userResponseDto.getId()+"/magazzino")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(json)
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.magazzini").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.magazzini",hasItem(magazzinoResponseDto.getId())));

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/"+userResponseDto.getId()+"/magazzino")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(json)
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.magazzini").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.magazzini",not(hasItem(magazzinoResponseDto.getId()))));
    }

}
