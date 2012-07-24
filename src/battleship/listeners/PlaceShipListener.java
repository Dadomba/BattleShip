package battleship.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import battleship.frames.CreateGridFrame;

public class PlaceShipListener implements ActionListener {

	private int idShip;
	private CreateGridFrame createGridFrame = null;
	
	public PlaceShipListener(CreateGridFrame createGridFrame, int idShip)
	{
		this.createGridFrame = createGridFrame;
		this.idShip = idShip;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(createGridFrame != null)
			createGridFrame.getJpGrid().tryPlaceShip(idShip);
	}

}
