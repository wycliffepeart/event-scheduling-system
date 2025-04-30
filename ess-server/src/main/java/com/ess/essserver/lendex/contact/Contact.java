package com.ess.essserver.lendex.contact;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {

    private String source;

    private ContactType type;

    private boolean isPrimary;

}
