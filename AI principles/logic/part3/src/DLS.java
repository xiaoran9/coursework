import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import aima.core.logic.propositional.inference.DPLLSatisfiable;
import aima.core.logic.propositional.parsing.PLParser;
import aima.core.logic.propositional.parsing.ast.Sentence;

public class DLS {
	private Queue<Grid> frontier;
	private Queue<Grid> uncovered;
	private KnowledgeBase kb;
	private String kbu;
	ArrayList<String> option;
	ArrayList<String> options;
	ArrayList<String> sentences;
	Agent agent;
	private DPLLSatisfiable dpll = new DPLLSatisfiable();
	public DLS(KnowledgeBase kb, Agent agent) {
		this.kb = kb;
		this.agent =agent;
	}
	
	public KnowledgeBase dpllStrategy(){
		getKBU();
		while(!uncovered.isEmpty()){
			Grid current = uncovered.poll();
			mark(current);
			probe(current);
		}
		return kb;
	}
	public void mark(Grid current){
		String prove = kbu +" & ~"+ current.getString();
		System.out.println(prove);
		boolean ans=displayDPLLSatisfiableStatus(prove);
		if(!ans){
			kb.setflag(current.getX(), current.getY());
			Game3.update = true;
			kb.print();
			}
	}
	public void probe(Grid current){
		String prove = kbu +" & "+ current.getString();
		System.out.println(prove);
		boolean ans=displayDPLLSatisfiableStatus(prove);
		if(!ans){
				agent.probe(current.getX(), current.getY());
				Game3.update = true;
				kb.print();
			}
	}
	/**
	 * 
	 */
	public void getKBU(){
		getFrontier();
		kbu="";
		sentences = new ArrayList<String>();
		while(!frontier.isEmpty()){
			Grid current = frontier.poll();
			getQuery(current);
			
		}
		setOptions(sentences,false);
	}
	/**
	 * get the frontier that have all the grid have covered neighbour
	 */
	public void getFrontier() {
		frontier = new LinkedList<Grid>();
		uncovered  = new LinkedList<Grid>();
		for(int i = 0;i < kb.getSize() ;i++){
			for(int j=0; j<kb.getSize() ; j++ ){//scan all the map
				if(kb.getGrid(i, j).getValue() > 0 && kb.getGrid(i, j).getValue() <= 8){ //if the grid's nearby have some nettles
					if(checkFrontier(kb.getGrid(i, j))){ //if the grid neighbour is covered but not flag
					frontier.add(kb.getGrid(i, j)); 
					}
					addCovered(kb.getGrid(i, j)); //save neighbour into the list uncovered
				}
			}
		}
	}
	
	public void addCovered(Grid current){ //
		for(int n=1;n <= 8; n++) {
			Grid neighbour = current.neighbour(n);
			if(neighbour.getX() < kb.getSize() && neighbour.getY() < kb.getSize() && neighbour.getX() >= 0 && neighbour.getY() >= 0){
				neighbour = kb.getGrid(neighbour.getX(), neighbour.getY());
				if(!neighbour.getFlag() && !neighbour.getVisit()){
					if(!uncovered.contains(neighbour)){
						uncovered.add(neighbour);
					}
				}
			}
		}
	}
	/**
	 * if the current neighbour have any covered but not flag neighbour
	 * @param current -the neighbour need to check
	 * @return -true means there have appropriate neighbour, flase mean the current grid surrounding is safety
	 */
	public boolean checkFrontier(Grid current){
		for(int n=1;n <= 8; n++) {
			Grid neighbour = current.neighbour(n);
			if(neighbour.getX() < kb.getSize() && neighbour.getY() < kb.getSize() && neighbour.getX() >= 0 && neighbour.getY() >= 0){ //Inside the board
				neighbour = kb.getGrid(neighbour.getX(), neighbour.getY());
				if(!neighbour.getFlag() && !neighbour.getVisit()){ //covered and not marked
					
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * get neighbours' string into the option list 
	 * @param current
	 */
	public void getQuery(Grid current){
		int marked = 0;
		option = new ArrayList<String>();
		marked = 0;
		for(int n=1;n <= 8; n++) {
			Grid neighbour = current.neighbour(n);
			if(neighbour.getX() < kb.getSize() && neighbour.getY() < kb.getSize() && neighbour.getX() >= 0 && neighbour.getY() >= 0){
				neighbour = kb.getGrid(neighbour.getX(), neighbour.getY());
				if(!neighbour.getFlag() && !neighbour.getVisit()){
					option.add(neighbour.getString());
					}
				if(neighbour.getFlag()){
					marked++;
				}
				}
		}
		current.setMarked(marked);
		setOption(option,current.getValue()-current.getMarked());
		setOptions(options,true);
	}
	/**
	 * the number of differences’ grid will be set as Nxy and other grid are safety.
	 * add all situation as a sentence
	 * @param option
	 * @param value
	 */
	public void setOption(ArrayList<String> option,int value){
		options = new ArrayList<String>();
		for(int i = 0; i<option.size();i++){
			String sentence = "(";
			for(int j = 0; j<option.size();j++){
				if(i+value<=option.size()){
				if(j< i || j>= i+value) {
					if(j==0){
						sentence = sentence+ "~" + option.get(j);
					}else{
					sentence = sentence+ " & ~" + option.get(j);
					}
				}else{
					if(j==0){
						sentence += option.get(j);
						}else{
							sentence = sentence+ " & " + option.get(j);
						}
					}
				}else{
					if(j>=i+value-option.size() && j<i) {
						if(j==0){
							sentence = sentence+ "~" + option.get(j);
						}else{
						sentence = sentence+ " & ~" + option.get(j);
						}
					}else{
						if(j==0){
							sentence += option.get(j);
							}else{
								sentence = sentence+ " & " + option.get(j);
							}
						}
				}
			}
			sentence += ")";
			options.add(sentence);	
		}	
	}
	/**
	 * 
	 * @param option
	 * @param or
	 */
	public void setOptions(ArrayList<String> option,boolean or){
		String sentence ="";
		
		for(int i = 0; i<option.size();i++){
			if(!or){
				if(i!=0){
					sentence = sentence+ " & (" + option.get(i);	
				}else{
					sentence = sentence+ "(" +option.get(i);	
				}
					
			}else{
				if(i!=0){
					sentence = sentence+ " | " + option.get(i);	
				}else{
					sentence += option.get(i);	
				}
			}
			if(!or){sentence += ")";}
		}
		
		if(or){
			sentences.add(sentence);
		}else{
			kbu =sentence;
		}
	}
	public boolean displayDPLLSatisfiableStatus(String query) {
		PLParser parser = new PLParser();
		Sentence sent = (Sentence) parser.parse(query);
		boolean a = dpll.dpllSatisfiable(sent);
		System.out.println(a);
		if (a) {
			System.out.println( "  satisfiable");
		return true;
		} else {
			System.out.println(" NOT satisfiable");
		return false;
		}
	}
}

