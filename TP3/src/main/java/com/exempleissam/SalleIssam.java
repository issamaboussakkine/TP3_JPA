package com.exempleissam;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "issam_salles")
public class SalleIssam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalleIssam;

    @NotBlank(message = "Le nom de la salle issam est obligatoire")
    @Column(nullable = false)
    private String nomSalleIssam;

    @NotNull(message = "La capacite issam est obligatoire")
    @Min(value = 1, message = "La capacite minimum issam est de 1 personne")
    @Column(nullable = false)
    private Integer capaciteSalleIssam;

    @Size(max = 500, message = "La description issam ne peut pas depasser 500 caracteres")
    @Column(length = 500)
    private String descriptionSalleIssam;

    @OneToMany(mappedBy = "salleIssam", cascade = CascadeType.ALL)
    private List<ReservationIssam> listeReservationsIssam = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "issam_salle_equipement",
            joinColumns = @JoinColumn(name = "salle_issam_id"),
            inverseJoinColumns = @JoinColumn(name = "equipement_issam_id")
    )
    private Set<EquipementIssam> equipementsIssam = new HashSet<>();

    public SalleIssam() {
    }

    public SalleIssam(String nomSalleIssam, Integer capaciteSalleIssam) {
        this.nomSalleIssam = nomSalleIssam;
        this.capaciteSalleIssam = capaciteSalleIssam;
    }

    public void ajouterReservationIssam(ReservationIssam reservationIssam) {
        listeReservationsIssam.add(reservationIssam);
        reservationIssam.setSalleIssam(this);
    }

    public void supprimerReservationIssam(ReservationIssam reservationIssam) {
        listeReservationsIssam.remove(reservationIssam);
        reservationIssam.setSalleIssam(null);
    }

    public void ajouterEquipementIssam(EquipementIssam equipementIssam) {
        equipementsIssam.add(equipementIssam);
        equipementIssam.getSallesEquipeesIssam().add(this);
    }

    public void supprimerEquipementIssam(EquipementIssam equipementIssam) {
        equipementsIssam.remove(equipementIssam);
        equipementIssam.getSallesEquipeesIssam().remove(this);
    }

    public Long getIdSalleIssam() {
        return idSalleIssam;
    }

    public void setIdSalleIssam(Long idSalleIssam) {
        this.idSalleIssam = idSalleIssam;
    }

    public String getNomSalleIssam() {
        return nomSalleIssam;
    }

    public void setNomSalleIssam(String nomSalleIssam) {
        this.nomSalleIssam = nomSalleIssam;
    }

    public Integer getCapaciteSalleIssam() {
        return capaciteSalleIssam;
    }

    public void setCapaciteSalleIssam(Integer capaciteSalleIssam) {
        this.capaciteSalleIssam = capaciteSalleIssam;
    }

    public String getDescriptionSalleIssam() {
        return descriptionSalleIssam;
    }

    public void setDescriptionSalleIssam(String descriptionSalleIssam) {
        this.descriptionSalleIssam = descriptionSalleIssam;
    }

    public List<ReservationIssam> getListeReservationsIssam() {
        return listeReservationsIssam;
    }

    public void setListeReservationsIssam(List<ReservationIssam> listeReservationsIssam) {
        this.listeReservationsIssam = listeReservationsIssam;
    }

    public Set<EquipementIssam> getEquipementsIssam() {
        return equipementsIssam;
    }

    public void setEquipementsIssam(Set<EquipementIssam> equipementsIssam) {
        this.equipementsIssam = equipementsIssam;
    }

    @Override
    public String toString() {
        return "SalleIssam{" +
                "idSalleIssam=" + idSalleIssam +
                ", nomSalleIssam='" + nomSalleIssam + '\'' +
                ", capaciteSalleIssam=" + capaciteSalleIssam +
                ", descriptionSalleIssam='" + descriptionSalleIssam + '\'' +
                '}';
    }
}