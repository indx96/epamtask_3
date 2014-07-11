package com.epam.airline.parsing.stax;

import com.epam.airline.planes.Fighter;
import com.epam.airline.planes.Plane;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

/**
 * Created by ivan on 7/11/14.
 */
class ProcessFighterState extends DefaultHandler implements ParsingContext.ContextState {
    private ParsingContext context;
    private ProcessPlaneState parentState = new ProcessPlaneState(context);

    public ProcessFighterState(ParsingContext context) {
        this.context = context;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("weapons".equals(qName)) {
            Plane basePlane = parentState.createPlaneByValues();
            int bombsCount = Integer.parseInt((String) parentState.values.get(6));
            int gunsCount = Integer.parseInt((String) parentState.values.get(7));
            HashMap<Fighter.WeaponType, Integer> weapons = new HashMap<>();
            weapons.put(Fighter.WeaponType.BOMBS, bombsCount);
            weapons.put(Fighter.WeaponType.GUNS, gunsCount);
            Fighter fighter = new Fighter.Builder(basePlane, weapons).build();
            context.addPlane(fighter);
            context.setState(context.PROCESS_PLANES);
            parentState.values.clear();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        parentState.characters(ch, start, length);
    }
}
