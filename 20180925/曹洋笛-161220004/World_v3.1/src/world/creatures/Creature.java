package world.creatures;

import world.util.CreatureType;

/**	
 *	���������
 *	
 *	@author Mirror
 *	
 *	@see type
 *	@see name
 *	@see symbol
 */
public class Creature {

	/**	�������� */
	protected CreatureType type;

	/**	�������� */
    protected String name; 

	/**	������� */
    protected char symbol;

    protected Creature() {}
    private Creature(CreatureType t, String n, char s) {
    	type = t;
    	name = n;
    	symbol = s;
    }

	/**	�õ��������� */
    public CreatureType getType() {
    	return type;
    }

	/**	�õ��������� */
    public String getName() {
        return name;
    }

	/**	�õ�������� */
    public char getSymbol() {
        return symbol;
    }
    
    public Creature copy() {
    	return new Creature(type, name, symbol);
    }
}
