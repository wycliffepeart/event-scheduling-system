package com.ess.essserver.module.metadata;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder
@Accessors(chain = true)
public class Dropdown {

    public String key;
    public String value;
    public String type;
}
