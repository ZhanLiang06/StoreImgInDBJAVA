/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "TEST_IMG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestImg.findAll", query = "SELECT t FROM TestImg t"),
    @NamedQuery(name = "TestImg.findByImgId", query = "SELECT t FROM TestImg t WHERE t.imgId = :imgId")})
public class TestImg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "IMG_ID")
    private String imgId;
    @Lob
    @Column(name = "IMG_DATA")
    private Serializable imgData;

    public TestImg() {
    }

    public TestImg(String imgId) {
        this.imgId = imgId;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public Serializable getImgData() {
        return imgData;
    }

    public void setImgData(Serializable imgData) {
        this.imgData = imgData;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imgId != null ? imgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestImg)) {
            return false;
        }
        TestImg other = (TestImg) object;
        if ((this.imgId == null && other.imgId != null) || (this.imgId != null && !this.imgId.equals(other.imgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.TestImg[ imgId=" + imgId + " ]";
    }
    
}
