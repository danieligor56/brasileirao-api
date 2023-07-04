package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;


@Tag (name = "API de Equipes")
@RestController
@RequestMapping("/api/v1/equipes")
public class EquipeController {

}
