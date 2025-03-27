package org.ess.app.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class DropdownDTO {

    public String key;
    public String value;
    public String type;
}
