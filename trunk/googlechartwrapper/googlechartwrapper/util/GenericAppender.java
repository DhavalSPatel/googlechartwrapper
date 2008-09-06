package googlechartwrapper.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import googlechartwrapper.ChartTypeFeature;

public class GenericAppender< T extends IFeatureAppender> implements IExtendedFeatureAppender{
	
	protected List<T> list;
	protected String prefix;
	protected String separator;
	
	public GenericAppender(ChartTypeFeature m){
		this(m,"|");
	}
	
	public GenericAppender(String stm){
		this(stm,"|");
	}
	
	public GenericAppender (ChartTypeFeature m, String separator){
		if (separator == null){
			throw new IllegalArgumentException("sep cannot be null");
		}
		list = new ArrayList<T>();
		prefix = m.getPrefix();
		this.separator = separator;
	}
	
	public GenericAppender(String stm, String separator){
		if (separator == null){
			throw new IllegalArgumentException("sep cannot be null");
		}
		list = new ArrayList<T>();
		prefix = stm;
		this.separator = separator;
	}
	
	public void add (T m){
		list.add(m);
	}
	
	public boolean remove (T m){
		return list.remove(m);
	}
	
	public T remove (int index){
		return list.remove(index);
	}
	
	public void removeAll (){
		for (int i = 0; i < list.size();){
			list.remove(i);
		}
	}
	
	/**
	 * Returns the list of all T elements added to this appender. 
	 * It returns an unmodifiable view of the value list.
	 * Consequently "read-only" access is possible
	 * @return unmodifiable view of the values
	 */
	public List<T> getList (){
		return Collections.unmodifiableList(list);
	}
	
	/**
	 * Returns the list of all T elements added to this appender. 
	 * It returns an unmodifiable view of the value list.
	 * Consequently "read-only" access is possible. The value list is filtered
	 * for elements which are instance of the parameter class.
	 * @param c optional parameter: filters the returned list to classes of this type
	 * @return unmodifiable view of the values
	 */
	public List<? extends T> getList (Class<? extends T> c){
		//TODO mva: filter!
		return Collections.unmodifiableList(list);
	}

	public String getAppendableString(List<? extends IFeatureAppender> otherAppenders) {
		
		if (list.size() > 0){
			StringBuilder bf = new StringBuilder(list.size()*10);
			//bf.append("chm=");
			for (IFeatureAppender m:list){
				String app = m.getAppendableString(otherAppenders);
				if (app.length()>0){
					bf.append(app);
					bf.append(separator);		
				}
				//bf.append(m.getAppendableString(otherAppenders));
						
			}
			if (bf.length()>0){
				return bf.substring(0, bf.length()-1);
			}
			else {
				return "";
			}
			
		}
		else return "";
	}
	
	public String getFeaturePrefix (){
		return prefix;
	}
	
	@Override
	public String toString() {
		return super.toString()+" "+list;
	}

}
