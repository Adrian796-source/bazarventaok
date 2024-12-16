package com.todocode.bazarventa.bazarventaapplicationtest;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test-local")// relativo a : application-test-local.properties
class BazarventaApplicationTests {

    @Test
    void contextLoads() {
        // Si el contexto de Spring Boot carga correctamente, el test pasa
        assertTrue(true, "El contexto debería cargarse correctamente.");
    }
}

