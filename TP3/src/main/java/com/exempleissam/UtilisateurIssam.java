package com.exempleissam;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "issam_utilisateurs")
public class UtilisateurIssam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIssam;

    @NotBlank(message = "Le nom issam est obligatoire")
    @Column(nullable = false)
    private String nomIssam;

    @NotBlank(message = "Le prénom issam est obligatoire")
    @Column(nullable = false)
    private String prenomIssam;

    @NotBlank(message = "L'email issam est obligatoire")
    @Email(message = "Format d'email issam invalide")
    @Column(unique = true, nullable = false)
    private String emailIssam;

    @OneToMany(mappedBy = "utilisateurIssam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationIssam> listeReservationsIssam = new ArrayList<>();

    public UtilisateurIssam() {
    }

    public UtilisateurIssam(String nomIssam, String prenomIssam, String emailIssam) {
        this.nomIssam = nomIssam;
        this.prenomIssam = prenomIssam;
        this.emailIssam = emailIssam;
    }

    public void ajouterReservationIssam(ReservationIssam reservationIssam) {
        listeReservationsIssam.add(reservationIssam);
        reservationIssam.setUtilisateurIssam(this);
    }

    public void supprimerReservationIssam(ReservationIssam reservationIssam) {
        listeReservationsIssam.remove(reservationIssam);
        reservationIssam.setUtilisateurIssam(null);
    }

    // Getters et Setters
    public Long getIdIssam() {
        return idIssam;
    }

    public void setIdIssam(Long idIssam) {
        this.idIssam = idIssam;
    }

    public String getNomIssam() {
        return nomIssam;
    }

    public void setNomIssam(String nomIssam) {
        this.nomIssam = nomIssam;
    }

    public String getPrenomIssam() {
        return prenomIssam;
    }

    public void setPrenomIssam(String prenomIssam) {
        this.prenomIssam = prenomIssam;
    }

    public String getEmailIssam() {
        return emailIssam;
    }

    public void setEmailIssam(String emailIssam) {
        this.emailIssam = emailIssam;
    }

    public List<ReservationIssam> getListeReservationsIssam() {
        return listeReservationsIssam;
    }

    public void setListeReservationsIssam(List<ReservationIssam> listeReservationsIssam) {
        this.listeReservationsIssam = listeReservationsIssam;
    }

    @Override
    public String toString() {
        return "UtilisateurIssam{" +
                "idIssam=" + idIssam +
                ", nomIssam='" + nomIssam + '\'' +
                ", prenomIssam='" + prenomIssam + '\'' +
                ", emailIssam='" + emailIssam + '\'' +
                '}';
    }
}