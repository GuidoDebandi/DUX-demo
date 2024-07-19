package com.dux.teams.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
@ToString
@Entity
@Table(name = "TEAMS")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Team implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teams_generator")
    @SequenceGenerator(name = "teams_generator", sequenceName = "teams_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "NAME")
    @JsonProperty("nombre")
    private String name;

    @Column(name = "LEAGUE")
    @JsonProperty("liga")
    private String league;

    @Column(name = "COUNTRY")
    @JsonProperty("pais")
    private String country;




    @Override
    public boolean equals(Object o) {
        if( this == o )
            return true;
        if( o == null || Hibernate.getClass(this) != Hibernate.getClass(o) )
            return false;
        Team team = (Team) o;
        return id != null && Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
