package org.sonatype.mavenbook.web;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"id",
"first_name",
"last_name",
"avatar"
})
public class Example {

@JsonProperty("id")
private Integer id;
@JsonProperty("first_name")
private String firstName;
@JsonProperty("last_name")
private String lastName;
@JsonProperty("avatar")
private String avatar;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The id
*/
@JsonProperty("id")
public Integer getId() {
return id;
}

/**
* 
* @param id
* The id
*/
@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

/**
* 
* @return
* The firstName
*/
@JsonProperty("first_name")
public String getFirstName() {
return firstName;
}

/**
* 
* @param firstName
* The first_name
*/
@JsonProperty("first_name")
public void setFirstName(String firstName) {
this.firstName = firstName;
}

/**
* 
* @return
* The lastName
*/
@JsonProperty("last_name")
public String getLastName() {
return lastName;
}

/**
* 
* @param lastName
* The last_name
*/
@JsonProperty("last_name")
public void setLastName(String lastName) {
this.lastName = lastName;
}

/**
* 
* @return
* The avatar
*/
@JsonProperty("avatar")
public String getAvatar() {
return avatar;
}

/**
* 
* @param avatar
* The avatar
*/
@JsonProperty("avatar")
public void setAvatar(String avatar) {
this.avatar = avatar;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}