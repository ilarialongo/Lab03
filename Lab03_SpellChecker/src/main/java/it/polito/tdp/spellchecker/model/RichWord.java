package it.polito.tdp.spellchecker.model;
//import java.io.Serializable;

public class RichWord {
private String parola;
private boolean isCorrect;


public RichWord(String parola, boolean isCorrect) {
	super();
	this.parola = parola;
	this.isCorrect = isCorrect;
}

public String getParola() {
	return parola;
}
public void setParola(String parola) {
	this.parola = parola;
}
public boolean isCorrect() {
	return isCorrect;
}
public void setCorrect(boolean isCorrect) {
	this.isCorrect = isCorrect;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (isCorrect ? 1231 : 1237);
	result = prime * result + ((parola == null) ? 0 : parola.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	RichWord other = (RichWord) obj;
	if (isCorrect != other.isCorrect)
		return false;
	if (parola == null) {
		if (other.parola != null)
			return false;
	} else if (!parola.equals(other.parola))
		return false;
	return true;
}

@Override
public String toString() {
	return parola;
}

}
