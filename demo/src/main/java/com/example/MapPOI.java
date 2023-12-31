package com.example;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This class represents a MAP poi label, which is UI that can be clicked for the user.
 */
public class MapPOI extends JLabel {
    POI poiData;
    //Imges and POI declarations
    final static ImageIcon normalPin = new ImageIcon("./images/locationPinNormal.png");
    final static ImageIcon highlightPin = new ImageIcon("./images/locationPinHighlight.png");

    public MapPOI(POI poiObject) {
        // Set image 
        //Sets the location of the POI
        setIcon(normalPin);
        setSize(this.getPreferredSize());
        setLocation(poiObject.getX() - normalPin.getIconWidth()/2, poiObject.getY() - normalPin.getIconHeight());

        poiData = poiObject;
        //Adds a mouse listener for the POI to be clickable
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                setIcon(highlightPin);
                POIInfo infoPanel = new POIInfo(poiData);
                infoPanel.setVisible(true);

                infoPanel.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        setIcon(normalPin);
                        mapExplorePanel.updateFavouritePOI();
                        mapExplorePanel.updateUserPOI();
                    }
                });
            }
        });
    }

    /**
     * This method highlights the pin for the POI
     */
    public void highlight() {
        setIcon(highlightPin);
    }

    /**
     * This method unhighlights the pin for the POI
     */
    public void unhighlight() {
        setIcon(normalPin);
    }

    /** 
     * This method retrieves the POI
     */
    public POI getPoi() {
        return poiData;
    }
    
}
