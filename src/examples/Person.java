package examples;

public class Person {

	private Integer id;
	private Integer age;
	private String name;
	private Boolean isMarried;
	private TypePersonne type;

	public Person(Integer id, String name, Boolean isMarried) {
		this.id = id;
		this.name = name;
		this.isMarried = isMarried;
	}

	public Person(Integer id,int age, String name, Boolean isMarried, TypePersonne type) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.isMarried = isMarried;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isMarried() {
		return isMarried;
	}

	public void setMarried(Boolean isMarried) {
		this.isMarried = isMarried;
	}

	public TypePersonne getType() {
		return type;
	}

	public void setType(TypePersonne type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}

	public AgePersonne groupByAge() {
		if (this.age <18) {
			return AgePersonne.Mineur;
		} else if (this.age<40) {
			return AgePersonne.Jeune;
		} else {
			return AgePersonne.Vieux;
		}
	}

}

enum TypePersonne {
	Monsieur, Madame
}

enum AgePersonne {
	Mineur,Jeune,Vieux
}
