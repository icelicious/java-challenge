package jp.co.axa.apidemo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RECIPES")
public class Recipes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "MAKING_TIME")
	private String making_time;

	@Column(name = "SERVES")
	private String serves;

	@Column(name = "INGREDIENTS")
	private String ingredients;
	
	@Column(name = "COST")
	private Integer cost;
	
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updated_at;

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public java.util.Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(java.util.Date created_at) {
		this.created_at = created_at;
	}

	public java.util.Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(java.util.Date updated_at) {
		this.updated_at = updated_at;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMaking_time() {
		return making_time;
	}

	public void setMaking_time(String making_time) {
		this.making_time = making_time;
	}

	public String getServes() {
		return serves;
	}

	public void setServes(String serves) {
		this.serves = serves;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

}
