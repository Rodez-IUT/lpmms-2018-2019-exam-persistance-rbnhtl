package ourbusinessproject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1)
    private String title;

    private String description;


    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Enterprise enterprise;

    public Project() {}

    public Project(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
    	
    	/* 
    	 * Lors de la modification d'une entreprise, on vérifie si le projet avait 
    	 * déjà une entreprise, si oui, on enlève le projet de l'ancienne entreprise
    	 * puis on l'ajoute dans la nouvelle
    	 */
    	
    	if (this.enterprise != null) {
    		this.enterprise.removeProject(this);
    	}
    	
        this.enterprise = enterprise;
        
        this.enterprise.addProject(this);
    }
}
