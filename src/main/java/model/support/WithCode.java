package model.support;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class WithCode extends PanacheEntity {
    @Column(nullable = false, length = 100) // Uniqueness set in each table to have explicit index name
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
