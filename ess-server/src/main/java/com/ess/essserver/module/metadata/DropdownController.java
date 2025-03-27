package com.ess.essserver.module.metadata;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Metadata Management", description = "CRUD APIs for managing metadata")
@RestController
@RequestMapping("/api/dropdowns")
@RequiredArgsConstructor
public class DropdownController {

    private final DropdownRepository repository;

    @GetMapping
    List<Dropdown> getDropdowns(@RequestParam(required = false) String type) {
        return repository.getDropdowns(type);
    }
}
