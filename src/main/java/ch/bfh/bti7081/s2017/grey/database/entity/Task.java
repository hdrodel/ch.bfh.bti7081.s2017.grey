package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author Quentin
 */
@Entity
public class Task {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Timestamp created;
    private Timestamp changed;
    @OneToMany(mappedBy = "task")
    private List<DrugTaskAssociation> drugs;
    //private boolean finished;
    //private int duration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public List<DrugTaskAssociation> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<DrugTaskAssociation> drugs) {
        this.drugs = drugs;
    }
    
//    public int getDuration() {
//        return duration;
//    }
//
//    public void setDiration(int amount) {
//        this.duration = amount;
//    }
//    
//    public boolean getFinished() {
//        return finished;
//    }
//
//    public void setFinished(boolean finished) {
//        this.finished = finished;
//    }
    
    
}