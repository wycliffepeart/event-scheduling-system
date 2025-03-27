package com.ess.essserver.module.metadata;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DropdownRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Dropdown> getDropdowns(String dropdownType) {
        String sql = "SELECT * FROM dropdowns WHERE type = ?";
        List<Map<String, Object>> dropdowns = jdbcTemplate.queryForList(sql, dropdownType);

        return dropdowns.stream().map(dropdown -> Dropdown.builder()
                        .key((String) dropdown.get("name"))
                        .value((String) dropdown.get("value"))
                        .type((String) dropdown.get("type"))
                        .build())
                .collect(Collectors.toList());
    }


}
