package com.exempleissam;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mon_equipement_issam")
public class EquipementIssam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reference_issam")
    private Long referenceIssam;

    @NotBlank(message = "Veuillez saisir le nom de l'équipement")
    @Column(name = "designation_issam", nullable = false)
    private String designationIssam;

    @Size(max = 500, message = "Trop long ! 500 caractères maximum")
    @Column(name = "details_issam", length = 500)
    private String detailsIssam;

    @ManyToMany(mappedBy = "equipementsIssam")
    private Set<SalleIssam> sallesEquipeesIssam = new HashSet<>();  // Nom plus descriptif

    public EquipementIssam() {
    }

    public EquipementIssam(String designationIssam) {
        this.designationIssam = designationIssam;
    }

    public EquipementIssam(String designationIssam, String detailsIssam) {
        this.designationIssam = designationIssam;
        this.detailsIssam = detailsIssam;
    }

    public Long getReferenceIssam() {
        return referenceIssam;
    }

    public void setReferenceIssam(Long referenceIssam) {
        this.referenceIssam = referenceIssam;
    }

    public String getDesignationIssam() {
        return designationIssam;
    }

    public void setDesignationIssam(String designationIssam) {
        this.designationIssam = designationIssam;
    }

    public String getDetailsIssam() {
        return detailsIssam;
    }

    public void setDetailsIssam(String detailsIssam) {
        this.detailsIssam = detailsIssam;
    }

    public Set<SalleIssam> getSallesEquipeesIssam() {
        return sallesEquipeesIssam;
    }

    public void setSallesEquipeesIssam(Set<SalleIssam> sallesEquipeesIssam) {
        this.sallesEquipeesIssam = sallesEquipeesIssam;
    }

    @Override
    public String toString() {
        return "EquipementIssam{" +
                "referenceIssam=" + referenceIssam +
                ", designationIssam='" + designationIssam + '\'' +
                ", detailsIssam='" + detailsIssam + '\'' +
                '}';
    }
}
