package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;

public class Dictionary {
	private List <String> dizionario;
	private int numeroParoleErrate;
	private long tempoImpiegato;
	
	public Dictionary () {
		super();
		this.dizionario= new ArrayList <String>();
		//this.dizionario= new LinkedList <String>();
	}
	
	public void loadDictionary(String language) {
		//eclipse2-workspace - spellchecker
		String filename="src/main/resources/"+language+".txt";
		
		try {
			FileReader fr= new FileReader (filename);
			BufferedReader br= new BufferedReader(fr);
			String word;
			while ((word=br.readLine())!=null) {
				word.toLowerCase();
				dizionario.add(word);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
			//e.printStackTrace();
		}
		
	}
	
	public List <RichWord> spellCheckTest(List <String> inputTextList) {
		this.numeroParoleErrate=0;
		List<RichWord> listaControllo= new ArrayList<>();
		for (String s: inputTextList) {
			if(this.check(s)==false) {
				throw new IllegalStateException("Inserire una parola valida\n");
			}
	
			int posizione= dizionario.indexOf(s);
			if (posizione!=-1) {
				RichWord pTemp= new RichWord(s,true);
				listaControllo.add(pTemp);
			}
			else {
			
		//if(posizione==-1) {
				this.numeroParoleErrate++;
				RichWord pTemp= new RichWord(s,false);
				listaControllo.add(pTemp);
				
			}
		}
		return listaControllo;
	}
	
	/*public String dammiIlTempo() {
		String tempo;
	   	this.tempoImpiegato= System.nanoTime();
	   	tempo= Long.toString(tempoImpiegato);
	   	return tempo;
	}*/
	
	public List <RichWord> spellCheckTestLinear (List <String> inputTextList) {
		this.numeroParoleErrate=0;
		List<RichWord> listaControllo= new ArrayList<>();
		for (String s: inputTextList) {
			if(this.check(s)==false) {
				throw new IllegalStateException("Inserire una parola valida\n");
			}
			if (dizionario.contains(s)) {
				RichWord pTemp= new RichWord(s,true);
				listaControllo.add(pTemp);
			}
			else {
				this.numeroParoleErrate++;
				RichWord pTemp= new RichWord(s,false);
				listaControllo.add(pTemp);	
			}
	
			}
		return listaControllo;
		
	}
	
	
	public List <RichWord> spellCheckTestDichotomic (List <String> inputTextList) {
		this.numeroParoleErrate=0;
	    List<RichWord> listaControllo= new ArrayList<>();
		//List<RichWord> listaControllo= new LinkedList<>();

	    int meta=(dizionario.size())/2;
		String parolaMeta=dizionario.get(meta);
	   // List<String> listaPrimaMeta= dizionario.subList(0, meta-1);
	   // List<String> listaSecondaMeta=dizionario.subList(meta+1, dizionario.size());
		
		for (String s: inputTextList) {
			if(this.check(s)==false) {
				throw new IllegalStateException("Inserire una parola valida\n");
			}
			if (s.compareTo(parolaMeta)==0) {
				RichWord pTemp= new RichWord(s,true);
				listaControllo.add(pTemp);
			}
			else if (s.compareTo(parolaMeta)>0) {
				int posizione= dizionario.subList(meta+1, dizionario.size()).indexOf(s);
				if(posizione!=-1) {
					RichWord pTemp= new RichWord(s,true);
					listaControllo.add(pTemp);	
				}
				else {
					this.numeroParoleErrate++;
					RichWord pTemp= new RichWord(s,false);
					listaControllo.add(pTemp);	
				}
			}
			else {
				int posizione= dizionario.subList(0, meta-1).indexOf(s);
				if(posizione!=-1) {
					RichWord pTemp= new RichWord(s,true);
					listaControllo.add(pTemp);	
				}
				else {
					this.numeroParoleErrate++;
					RichWord pTemp= new RichWord(s,false);
					listaControllo.add(pTemp);
				}
			}
			
		}
		return listaControllo;
	}
	
	
	
	   public int getNumeroParoleErrate() {
		return this.numeroParoleErrate;
	}

	public boolean check (String p) {
			boolean flag=true;
			for (int i=0; i<p.length();i++) {
				if (!Character.isLetter(p.charAt(i))) {
					flag=false;
				}
			}
			return flag;
		}
	   
	   
	   public String stampaLista (List<RichWord> lista) {
		   
		   String sTemp="";
		   for(RichWord r: lista) {
			   if(r.isCorrect()==false) {
			   if (sTemp=="") {
			   sTemp= r.toString();
		   }
			   else {
				   sTemp+="\n"+r.toString();
			   }
			   }
	   }
			   
		   return sTemp;
	   }
	   
	   
}
