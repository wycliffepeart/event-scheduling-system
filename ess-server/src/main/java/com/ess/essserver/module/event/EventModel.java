package com.ess.essserver.module.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import jakarta.persistence.*;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name="event")
public class EventModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String location;
    private String status;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String createdBy;
    private String updatedAt;
    private String createdAt;
}
